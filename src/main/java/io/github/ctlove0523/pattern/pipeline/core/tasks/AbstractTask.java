package io.github.ctlove0523.pattern.pipeline.core.tasks;

import io.github.ctlove0523.pattern.pipeline.core.Lifecycle;

/**
 * 任务的抽象基类
 *
 * @author chentong
 */
public abstract class AbstractTask<IN, OUT> extends BaseTaskInfo implements Lifecycle<IN,OUT> {
}
