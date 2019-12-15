package io.github.ctlove0523.pattern.pipeline.pipe;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PipeLine {
    private ExecutorService executor;
    private List<Pipe> pipes = new LinkedList<>();

    public void init() {
        Collections.sort(pipes);
        int maxThreads = pipes.stream()
                .mapToInt(Pipe::numberOfTasks).summaryStatistics().getMax();
        this.executor = Executors.newFixedThreadPool(maxThreads);
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
