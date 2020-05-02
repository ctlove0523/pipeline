package io.github.ctlove0523.pattern.pipeline.core.tasks;

import io.github.ctlove0523.pattern.pipeline.core.state.State;
import io.github.ctlove0523.pattern.pipeline.core.state.StateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对task的任何统计都封装在此类
 *
 * @author chentong
 */
public class TaskAdaptor<IN, OUT> implements Task<IN, OUT>, State {
    private static final Logger log = LoggerFactory.getLogger(TaskAdaptor.class);
    private static final long MILLISECONDS_PER_NANOSECONDS = 1000 * 1000;

    private AbstractTask<IN, OUT> task;

    public TaskAdaptor(AbstractTask<IN, OUT> task) {
        this.task = task;
    }

    @Override
    public void prepare() {
        long begin = System.nanoTime();
        task.prepare();
        long end = System.nanoTime();
        log.info("{} task prepare cost {} milliseconds", task.getTaskName(), milliseconds(end - begin));
    }

    @Override
    public OUT start(IN input) {
        long begin = System.nanoTime();
        OUT out = task.start(input);
        long end = System.nanoTime();
        log.info("{} task prepare cost {} milliseconds", task.getTaskName(), milliseconds(end - begin));
        return out;
    }

    @Override
    public void stop() {
        long begin = System.nanoTime();
        task.stop();
        long end = System.nanoTime();
        log.info("{} task prepare cost {} milliseconds", task.getTaskName(), milliseconds(end - begin));
    }

    @Override
    public OUT retry(IN input) {
        long begin = System.nanoTime();
        OUT out = task.retry(input);
        long end = System.nanoTime();
        log.info("{} task prepare cost {} milliseconds", task.getTaskName(), milliseconds(end - begin));
        return out;
    }

    @Override
    public void rollback(IN input) {
        long begin = System.nanoTime();
        task.rollback(input);
        long end = System.nanoTime();
        log.info("{} task prepare cost {} milliseconds", task.getTaskName(), milliseconds(end - begin));

    }

    @Override
    public StateEnum getState() {
        return task.getState();
    }

    private long milliseconds(long nanoseconds) {
        if (nanoseconds < 0) {
            return 0L;
        }
        return nanoseconds / MILLISECONDS_PER_NANOSECONDS;
    }
}
