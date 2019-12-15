package io.github.ctlove0523.pattern.pipeline.pipe.tasks;

import io.github.ctlove0523.pattern.pipeline.pipe.AbstractTask;
import io.github.ctlove0523.pattern.pipeline.pipe.TaskExecutionContext;
import io.github.ctlove0523.pattern.pipeline.pipe.TaskResult;
import org.springframework.stereotype.Component;

@Component
public class TenantTask extends AbstractTask {

    @Override
    public TaskResult process(TaskExecutionContext context) {
        return null;
    }
}
