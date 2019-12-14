package io.github.ctlove0523.pattern.pipeline.pipe;

/**
 * 任务的抽象基类
 */
public abstract class AbstractTask extends BaseTask {

    public abstract TaskResult process(TaskExecuteContext context,TaskInput taskInput);
}
