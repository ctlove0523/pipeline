package io.github.ctlove0523.pattern.pipeline.core.tasks;

import org.springframework.stereotype.Component;

@Component
public class VpcTask extends AbstractTask<TaskInput,TaskResult> {
    @Override
    public TaskResult process(TaskExecutionContext context, TaskInput input) {
        System.out.println(input);
        TaskResult result = new TaskResult();
        result.setProperty("vpc ","192.0.14.56/16");
        return result;
    }
}
