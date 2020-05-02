package io.github.ctlove0523.pattern.pipeline.core.deploy;

import io.github.ctlove0523.pattern.pipeline.core.pipeline.AbstractPipeLine;
import io.github.ctlove0523.pattern.pipeline.core.state.StateEnum;

/**
 * @author chentong
 */
public class DeployPipelineImpl extends AbstractPipeLine<DeployInput, DeployOutput> {
    @Override
    public DeployInput transform(DeployOutput output) {
        DeployInput input = new DeployInput();
        input.setData(output.getData());
        return input;
    }

    @Override
    public StateEnum getState() {
        return null;
    }
}
