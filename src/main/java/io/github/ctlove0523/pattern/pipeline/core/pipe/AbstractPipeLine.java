package io.github.ctlove0523.pattern.pipeline.core.pipe;

import io.github.ctlove0523.pattern.pipeline.core.Lifecycle;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author chentong
 */
@Getter
@Setter
public abstract class AbstractPipeLine<IN, OUT> implements Lifecycle<IN,OUT> {
    private String pipeLineName;
    private List<AbstractPipe<IN, OUT>> pipes = new LinkedList<>();


    @Override
    public void prepare() {
        Collections.sort(pipes);
    }

    @Override
    public OUT start(IN input) {
        IN initInput = input;
        OUT result = null;
        for (AbstractPipe<IN, OUT> pipe : pipes) {
            result = pipe.start(initInput);
            initInput = transform(result);
        }
        return result;
    }

    @Override
    public void stop() {
        pipes.forEach(Lifecycle::stop);
    }

    @Override
    public OUT retry(IN input) {
        return start(input);
    }

    @Override
    public void rollback(IN input) {
        pipes.forEach(pipe -> pipe.rollback(input));
    }

    /**
     * 输出到输入的转换
     * @param output 输出
     * @return 输入
     */
    public abstract IN transform(OUT output);

    public void addPipe(AbstractPipe pipe) {
        pipes.add(pipe);
    }

    public void deletePipe(AbstractPipe pipe) {
        pipes.remove(pipe);
    }

    public int numberOfPipes() {
        return pipes.size();
    }

    public List<AbstractPipe> getPipes() {
        return Collections.unmodifiableList(pipes);
    }
}
