package io.github.ctlove0523.pattern.pipeline.core.state;

/**
 * @author chentong
 */
public interface State {

    /**
     * 获取状态
     *
     * @return 当前task，pipe或pipeline的状态
     */
    StateEnum getState();
}
