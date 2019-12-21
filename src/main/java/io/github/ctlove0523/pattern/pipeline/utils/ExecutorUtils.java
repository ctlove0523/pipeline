package io.github.ctlove0523.pattern.pipeline.utils;

import java.util.concurrent.*;

/**
 * @author chentong
 */
public class ExecutorUtils {

    public static Executor newFixedThreadPool(int coreThreads) {
        return new ThreadPoolExecutor(coreThreads, coreThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }
}
