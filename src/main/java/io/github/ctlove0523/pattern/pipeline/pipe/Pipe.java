package io.github.ctlove0523.pattern.pipeline.pipe;

import io.github.ctlove0523.pattern.pipeline.utils.SpringContextUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.*;

@Getter
@Setter
public class Pipe implements Comparable<Pipe> {
    private String pipeName;
    private String priority;
    private List<AbstractTask> tasks = new LinkedList<>();

    public void addTask(BaseTask task) {
        Assert.notNull(task, "task must not be null");
        String className = task.getTaskClass();
        try {
            Class<AbstractTask> clazz = (Class<AbstractTask>) Class.forName(className);
            AbstractTask abstractTask = SpringContextUtils.getBean(clazz);
            tasks.add(abstractTask);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int numberOfTasks() {
        return tasks.size();
    }

    public void deleteTask(AbstractTask task) {
        tasks.remove(task);
    }

    public String getPipeName() {
        return pipeName;
    }

    public void setPipeName(String pipeName) {
        this.pipeName = pipeName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public List<AbstractTask> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public void setTasks(List<AbstractTask> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int compareTo(Pipe pipe) {
        return this.priority.compareTo(pipe.getPriority());
    }
}
