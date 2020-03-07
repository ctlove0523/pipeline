package io.github.ctlove0523.pattern.pipeline.core.state;

/**
 * pipeline,pipe和task状态持久化接口
 *
 * @author chentong
 */
public interface StateStore {
    /**
     * 存储pipeline状态
     *
     * @param pipelineId pipeline 唯一标识符
     * @param input      输入
     * @param output     输出
     * @param state      状态
     */
    void savePipelineState(String pipelineId, Object input, Object output, StateEnum state);

    /**
     * 更新pipeline状态
     *
     * @param pipelineId pipeline 唯一标识符
     * @param input      输入
     * @param output     输出
     * @param state      状态
     */
    void updatePipelineState(String pipelineId, Object input, Object output, StateEnum state);

    /**
     * 获取pipeline状态
     *
     * @param pipelineId pipeline唯一标识符
     * @return pipeline的状态
     */
    Object getPipelineState(String pipelineId);

    /**
     * 存储pipe状态
     *
     * @param pipelineId pipeline唯一标识符
     * @param pipeName   pipe名字
     * @param input      输入
     * @param output     输出
     * @param state      状态
     */
    void savePipeState(String pipelineId, String pipeName, Object input, Object output, StateEnum state);

    /**
     * 更新pipe状态
     *
     * @param pipelineId pipeline唯一标识符
     * @param pipeName   pipe名字
     * @param input      输入
     * @param output     输出
     * @param state      状态
     */
    void updatePipeState(String pipelineId, String pipeName, Object input, Object output, StateEnum state);

    /**
     * 获取pipe状态
     *
     * @param pipelineId pipeline唯一标识符
     * @param pipeName   pipe名字
     * @return pipe状态
     */
    Object getPipeState(String pipelineId, String pipeName);

    /**
     * 存储task状态
     *
     * @param pipelineId pipeline唯一标识符
     * @param taskName   task名
     * @param input      输入
     * @param output     输出
     * @param state      状态
     */
    void saveTaskState(String pipelineId, String taskName, Object input, Object output, StateEnum state);

    /**
     * 更新task状态
     *
     * @param pipelineId pipeline唯一标识符
     * @param taskName   task名
     * @param input      输入
     * @param output     输出
     * @param state      状态
     */
    void updateTaskState(String pipelineId, String taskName, Object input, Object output, StateEnum state);

    /**
     * 获取task状态
     *
     * @param pipelineId pipeline 唯一标识符
     * @param taskName   task名
     * @return task状态
     */
    Object getTaskState(String pipelineId, String taskName);
}