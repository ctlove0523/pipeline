package io.github.ctlove0523.pattern.pipeline.core.tasks;

import org.springframework.stereotype.Component;

@Component
public class CseTask extends AbstractTask<TaskInput,TaskResult> {

    @Override
    public TaskResult process(TaskExecutionContext context, TaskInput input) {
        System.out.println(input);
        TaskResult result = new TaskResult();
        result.setProperty("cse address","172.16.3.8");
        return result;
    }
}
