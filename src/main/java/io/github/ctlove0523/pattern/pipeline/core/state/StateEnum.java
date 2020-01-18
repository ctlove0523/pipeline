package io.github.ctlove0523.pattern.pipeline.core.state;

/**
 * @author chentong
 */

public enum StateEnum {
    STARTED(0),
    RUNNING(1),
    FINISHED(2),
    FAILED(4);
    private int order;

    public int getOrder() {
        return order;
    }
    StateEnum(int order) {
        this.order = order;
    }
}
