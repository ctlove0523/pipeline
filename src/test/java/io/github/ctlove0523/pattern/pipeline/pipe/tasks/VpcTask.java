package io.github.ctlove0523.pattern.pipeline.pipe.tasks;

import io.github.ctlove0523.pattern.pipeline.pipe.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class VpcTask extends AbstractTask {
    @Override
    public TaskResult process(TaskExecutionContext context) {
        return null;
    }

    @Override
    public CompletableFuture<TaskState> getTaskState() {
        return null;
    }

    @Override
    public CompletableFuture<Map<Object, Object>> getTaskResult() {
        return null;
    }

    @Override
    public void process(TaskExecutionContext context, TaskInput input) {

    }
}
