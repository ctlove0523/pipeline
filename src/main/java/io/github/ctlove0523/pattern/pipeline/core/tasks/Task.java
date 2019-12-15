package io.github.ctlove0523.pattern.pipeline.core.tasks;

/**
 * @author chentong
 */
public interface Task<T,R> {

    R process(TaskExecutionContext context, T input);
}
