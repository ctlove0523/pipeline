package io.github.ctlove0523.pattern.pipeline.core.pipe;

import io.github.ctlove0523.pattern.pipeline.core.Lifecycle;
import io.github.ctlove0523.pattern.pipeline.core.state.StateEnum;
import io.github.ctlove0523.pattern.pipeline.core.tasks.AbstractTask;
import io.github.ctlove0523.pattern.pipeline.core.tasks.BaseTaskInfo;
import io.github.ctlove0523.pattern.pipeline.core.tasks.Task;
import io.github.ctlove0523.pattern.pipeline.core.tasks.TaskAdaptor;
import io.github.ctlove0523.pattern.pipeline.utils.ExecutorUtils;
import io.github.ctlove0523.pattern.pipeline.utils.SpringContextUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author chentong
 */
@Getter
@Setter
@Slf4j
public abstract class AbstractPipe<IN, OUT> implements Pipe<IN, OUT> {
    private String pipeName;
    private String priority;
    private List<Task<IN, OUT>> tasks = new LinkedList<>();
    private Executor executor;

    @Override
    public void prepare() {
        this.executor = ExecutorUtils.newFixedThreadPool(tasks.size());
        tasks.forEach(Lifecycle::prepare);
    }

    @Override
    public OUT start(IN input) {
        List<CompletableFuture<OUT>> futures = new ArrayList<>();
        tasks.forEach(abstractTask -> futures.add(CompletableFuture.supplyAsync((Supplier<OUT>) () -> abstractTask.start(input), executor)));
        CompletableFuture<List<OUT>> future = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join).collect(Collectors.toList()));
        List<OUT> outputs = new ArrayList<>();
        try {
            outputs.addAll(future.get());
        } catch (InterruptedException | ExecutionException e) {
            log.warn("{} pipe start encounter exception {}", getPipeName(), e);
        }
        return mergeOutput(outputs);
    }

    @Override
    public void stop() {
        tasks.forEach(Lifecycle::stop);
    }

    @Override
    public OUT retry(IN input) {
        List<CompletableFuture<OUT>> futures = new ArrayList<>();
        tasks.forEach(abstractTask -> futures.add(CompletableFuture.supplyAsync((Supplier<OUT>) () -> abstractTask.retry(input), executor)));
        CompletableFuture<List<OUT>> future = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join).collect(Collectors.toList()));
        List<OUT> outputs = new ArrayList<>();
        try {
            outputs.addAll(future.get());
        } catch (InterruptedException | ExecutionException e) {
            log.warn("{} pipe start encounter exception {}", getPipeName(), e);
        }
        return mergeOutput(outputs);
    }

    @Override
    public void rollback(IN input) {
        tasks.forEach(task -> task.rollback(input));
    }

    @Override
    public StateEnum getState() {
        List<StateEnum> states = new ArrayList<>(tasks.size());
        tasks.forEach(task -> states.add(task.getState()));
        return StateEnum.getState(states);
    }

    /**
     * 多个task的output合并
     *
     * @param results
     * @return
     */
    protected abstract OUT mergeOutput(List<OUT> results);


    public void addTask(BaseTaskInfo task) {
        Assert.notNull(task, "task must not be null");
        String className = task.getTaskClass();
        try {
            Class<AbstractTask> clazz = (Class<AbstractTask>) Class.forName(className);
            AbstractTask abstractTask = SpringContextUtils.getBean(clazz);
            abstractTask.setTaskName(task.getTaskName());
            abstractTask.setTaskClass(task.getTaskClass());
            abstractTask.setTaskId(task.getTaskId());
            TaskAdaptor adaptor = new TaskAdaptor(abstractTask);
            tasks.add(adaptor);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void deleteTask(AbstractTask task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public void setTasks(List<Task<IN, OUT>> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }
}
