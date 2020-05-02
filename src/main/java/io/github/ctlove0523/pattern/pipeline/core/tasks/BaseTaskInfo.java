package io.github.ctlove0523.pattern.pipeline.core.tasks;

import io.github.ctlove0523.pattern.pipeline.core.state.State;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * task基类，仅保持task最基本的信息
 *
 * @author chentong
 */
@Getter
@Setter
public class BaseTaskInfo {
    private String taskName;
    private String taskId;
    private String taskClass;
    private Map<String, State> states = new ConcurrentHashMap<>();
}
