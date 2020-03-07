package io.github.ctlove0523.pattern.pipeline.core.pipeline;

import io.github.ctlove0523.pattern.pipeline.core.Lifecycle;
import io.github.ctlove0523.pattern.pipeline.core.state.State;

/**
 * @author chentong
 */
public interface Pipeline<IN, OUT> extends Lifecycle<IN, OUT>, State {
}
