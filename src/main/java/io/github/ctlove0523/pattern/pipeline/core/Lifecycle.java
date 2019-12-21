package io.github.ctlove0523.pattern.pipeline.core;

/**
 * @author chentong
 */
public interface Lifecycle<IN,OUT> {

    /**
     * 准备
     */
    void prepare();

    /**
     * 启动
     * @param input 输入
     * @return 输出
     */
    OUT start(IN input);

    /**
     * 停止
     */
    void stop();

    /**
     * 重试
     * @param input 输入
     * @return 输出
     */
    OUT retry(IN input);

    /**
     * 回滚
     * @param input 输入
     */
    void rollback(IN input);
}
