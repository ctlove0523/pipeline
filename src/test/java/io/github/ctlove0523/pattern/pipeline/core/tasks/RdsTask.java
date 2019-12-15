package io.github.ctlove0523.pattern.pipeline.core.tasks;

import org.springframework.stereotype.Component;

@Component
public class RdsTask extends AbstractTask<TaskInput,TaskResult> {


    @Override
    public TaskResult process(TaskExecutionContext context, TaskInput input) {
        System.out.println(input);
        TaskResult result = new TaskResult();
        result.setProperty("rds address","198.8.9.4");
        return result;
    }
}
