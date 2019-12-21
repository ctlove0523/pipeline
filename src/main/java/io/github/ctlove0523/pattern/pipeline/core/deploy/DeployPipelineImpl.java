package io.github.ctlove0523.pattern.pipeline.core.deploy;

import io.github.ctlove0523.pattern.pipeline.core.pipe.AbstractPipeLine;

/**
 * @author chentong
 */
public class DeployPipelineImpl extends AbstractPipeLine<DeployInput,DeployOutput> {
    @Override
    public DeployInput transform(DeployOutput output) {
        DeployInput input = new DeployInput();
        input.setData(output.getData());
        return input;
    }
}
