package io.github.ctlove0523.pattern.pipeline.core.tasks;

import lombok.Getter;
import lombok.Setter;

/**
 * task基类，仅保持task最基本的信息
 */
@Getter
@Setter
public class BaseTask {
    private String taskName;
    private String taskId;
    private String taskClass;
}
