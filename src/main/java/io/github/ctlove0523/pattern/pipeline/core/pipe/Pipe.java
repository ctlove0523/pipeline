package io.github.ctlove0523.pattern.pipeline.core.pipe;

import io.github.ctlove0523.pattern.pipeline.core.Lifecycle;
import io.github.ctlove0523.pattern.pipeline.core.state.State;

/**
 * @author chentong
 */
public interface Pipe<IN, OUT> extends Lifecycle<IN, OUT>, State {
}
