package io.github.ctlove0523.pattern.pipeline.core.state;

import java.util.Comparator;
import java.util.List;

/**
 * @author chentong
 */

public enum StateEnum {
    /**成功完成*/
    FINISHED(0),
    /**运行中*/
    RUNNING(1),
    /**未启动*/
    UNPROCESSED(2),
    /**失败*/
    FAILED(4);
    private int order;

    public static StateEnum getState(List<StateEnum> states) {
        if (states == null || states.isEmpty()) {
            return FAILED;
        }
        return states.stream().max(Comparator.comparingInt(StateEnum::getOrder)).get();
    }

    public int getOrder() {
        return order;
    }

    StateEnum(int order) {
        this.order = order;
    }
}
