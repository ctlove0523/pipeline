package io.github.ctlove0523.pattern.pipeline.core.pipeline;

import io.github.ctlove0523.pattern.pipeline.core.Lifecycle;
import io.github.ctlove0523.pattern.pipeline.core.pipe.Pipe;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chentong
 */
@Getter
@Setter
public abstract class AbstractPipeLine<IN, OUT> implements Pipeline<IN, OUT> {
    private String pipeLineName;
    private List<Pipe<IN, OUT>> pipes = new LinkedList<>();

    @Override
    public void prepare() {
        pipes.sort(Comparator.comparing(Pipe::getOrder));
        pipes.forEach(Lifecycle::prepare);
    }

    @Override
    public OUT start(IN input) {
        IN initInput = input;
        OUT result = null;
        for (Pipe<IN, OUT> pipe : pipes) {
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
        IN initInput = input;
        OUT result = null;
        for (Pipe<IN, OUT> pipe : pipes) {
            result = pipe.retry(initInput);
            initInput = transform(result);
        }
        return result;
    }

    @Override
    public void rollback(IN input) {
        pipes.forEach(pipe -> pipe.rollback(input));
    }

    /**
     * 输出到输入的转换
     *
     * @param output 输出
     * @return 输入
     */
    public abstract IN transform(OUT output);

    public void addPipe(Pipe<IN, OUT> pipe) {
        pipes.add(pipe);
    }

    public void deletePipe(Pipe<IN, OUT> pipe) {
        pipes.remove(pipe);
    }

    public int numberOfPipes() {
        return pipes.size();
    }

    public List<Pipe<IN, OUT>> getPipes() {
        return Collections.unmodifiableList(pipes);
    }
}
