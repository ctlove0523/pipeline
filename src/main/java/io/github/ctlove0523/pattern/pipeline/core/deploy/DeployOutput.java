package io.github.ctlove0523.pattern.pipeline.core.deploy;

/**
 * @author chentong
 */
public class DeployOutput extends BaseDeployData {

    public void merge(DeployOutput output) {
        this.setData(output.getData());
    }
}
