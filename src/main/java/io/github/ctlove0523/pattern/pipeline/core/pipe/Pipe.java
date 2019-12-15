package io.github.ctlove0523.pattern.pipeline.core.pipe;

import io.github.ctlove0523.pattern.pipeline.core.tasks.AbstractTask;
import io.github.ctlove0523.pattern.pipeline.core.tasks.BaseTask;
import io.github.ctlove0523.pattern.pipeline.core.tasks.TaskExecutionContext;
import io.github.ctlove0523.pattern.pipeline.utils.SpringContextUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author chentong
 */
@Getter
@Setter
public class Pipe<T, R> implements Comparable<Pipe<T,R>> {
    private String pipeName;
    private String priority;
    private List<AbstractTask<T,R>> tasks = new LinkedList<>();
    private Executor executor;

    public R process(TaskExecutionContext context,T input) {
        List<CompletableFuture<R>> futures = new ArrayList<>();
        tasks.forEach(new Consumer<AbstractTask<T,R>>() {
            @Override
            public void accept(AbstractTask<T,R> abstractTask) {
                futures.add(CompletableFuture.supplyAsync(new Supplier<R>() {
                    @Override
                    public R get() {
                        return abstractTask.process(context,input);
                    }
                },executor));
            }
        });
        CompletableFuture<List<R>> future = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v ->futures.stream()
                .map(CompletableFuture::join).collect(Collectors.toList()));
        try {
            List<R> nextInput = future.get();
            return mergeOutput(nextInput);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 多个task的output合并
     * @param results
     * @return
     */
    private R mergeOutput(List<R> results) {
        return null;
    }

    public void init() {
        this.executor = Executors.newFixedThreadPool(tasks.size());
    }
    public void addTask(BaseTask task) {
        Assert.notNull(task, "task must not be null");
        String className = task.getTaskClass();
        try {
            Class<AbstractTask> clazz = (Class<AbstractTask>) Class.forName(className);
            AbstractTask abstractTask = SpringContextUtils.getBean(clazz);
            abstractTask.setTaskName(task.getTaskName());
            abstractTask.setTaskClass(task.getTaskClass());
            abstractTask.setTaskId(task.getTaskId());
            tasks.add(abstractTask);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void deleteTask(AbstractTask task) {
        tasks.remove(task);
    }

    public List<AbstractTask> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public void setTasks(List<AbstractTask<T,R>> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public int compareTo(Pipe pipe) {
        return this.priority.compareTo(pipe.getPriority());
    }
}
