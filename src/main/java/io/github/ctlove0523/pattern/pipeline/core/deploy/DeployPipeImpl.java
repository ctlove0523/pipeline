package io.github.ctlove0523.pattern.pipeline.core.deploy;

import io.github.ctlove0523.pattern.pipeline.core.pipe.AbstractPipe;

import java.util.List;

/**
 * @author chentong
 */
public class DeployPipeImpl extends AbstractPipe<DeployInput, DeployOutput> {
    @Override
    protected DeployOutput mergeOutput(List<DeployOutput> results) {
        DeployOutput output = new DeployOutput();
        for (DeployOutput deployOutput : results) {
            output.merge(deployOutput);
        }
        return output;
    }
}
