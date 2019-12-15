//package io.github.ctlove0523.pattern.pipeline.pipe;
//
//import java.util.Map;
//import java.util.concurrent.CompletableFuture;
//
//public interface Task {
//    String getTaskName();
//
//    String getTaskId();
//
//    CompletableFuture<TaskState> getTaskState();
//
//    CompletableFuture<Map<Object,Object>> getTaskResult();
//
//    void process(TaskExecutionContext context, TaskInput input);
//}
