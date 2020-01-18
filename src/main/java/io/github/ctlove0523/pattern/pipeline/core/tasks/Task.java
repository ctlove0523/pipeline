package io.github.ctlove0523.pattern.pipeline.core.tasks;

import io.github.ctlove0523.pattern.pipeline.core.Lifecycle;
import io.github.ctlove0523.pattern.pipeline.core.state.State;

/**
 * @author chentong
 */
public interface Task<IN, OUT> extends Lifecycle<IN, OUT>, State {
}
