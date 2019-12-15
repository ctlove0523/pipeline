package io.github.ctlove0523.pattern.pipeline.core.pipe;

import io.github.ctlove0523.pattern.pipeline.core.tasks.TaskExecutionContext;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chentong
 */
@Getter
@Setter
public class PipeLine<T, R> {
    private String pipeLineName;
    private List<Pipe<T, R>> pipes = new LinkedList<>();

    public R process(TaskExecutionContext context, T input) {
        T initInput = input;
        R result = null;
        for (Pipe<T, R> pipe : pipes) {
            result = pipe.process(context, initInput);
            initInput = (T) result;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public void init() {
        Collections.sort(pipes);
        pipes.forEach(Pipe::init);
    }


    public void addPipe(Pipe pipe) {
        pipes.add(pipe);
    }

    public void deletePipe(Pipe pipe) {
        pipes.remove(pipe);
    }

    public int numberOfPipes() {
        return pipes.size();
    }

    public List<Pipe> getPipes() {
        return Collections.unmodifiableList(pipes);
    }
}
