package io.github.ctlove0523.pattern.pipeline.pipe.tasks;

import io.github.ctlove0523.pattern.pipeline.pipe.AbstractTask;
import io.github.ctlove0523.pattern.pipeline.pipe.TaskExecuteContext;
import io.github.ctlove0523.pattern.pipeline.pipe.TaskInput;
import io.github.ctlove0523.pattern.pipeline.pipe.TaskResult;
import org.springframework.stereotype.Component;

@Component
public class RdsTask extends AbstractTask {
    @Override
    public TaskResult process(TaskExecuteContext context, TaskInput taskInput) {
        return null;
    }
}
