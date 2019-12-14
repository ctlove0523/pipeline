package io.github.ctlove0523.pattern.pipeline.pipe;

/**
 * 
 */
public abstract class AbstractTask extends BaseTask {

    public abstract TaskResult process(TaskExecuteContext context,TaskInput taskInput);
}
