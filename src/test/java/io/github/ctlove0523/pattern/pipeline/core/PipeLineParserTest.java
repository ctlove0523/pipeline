package io.github.ctlove0523.pattern.pipeline.core;

import io.github.ctlove0523.pattern.pipeline.core.pipe.Pipe;
import io.github.ctlove0523.pattern.pipeline.core.pipe.PipeLine;
import io.github.ctlove0523.pattern.pipeline.core.pipe.PipeLineParser;
import io.github.ctlove0523.pattern.pipeline.core.tasks.AbstractTask;
import io.github.ctlove0523.pattern.pipeline.core.tasks.TaskInput;
import io.github.ctlove0523.pattern.pipeline.core.tasks.TaskResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@SpringBootTest
public class PipeLineParserTest {

    @Test
    public void test() throws Exception {
        File file = ResourceUtils.getFile("classpath:pipeline.xml");
        Optional<PipeLine> optionalPipeLine = PipeLineParser.parse(file);
        Assertions.assertTrue(optionalPipeLine.isPresent());
        PipeLine<TaskInput, TaskResult> pipeLine = optionalPipeLine.get();
        Assertions.assertEquals("deploy pipeline", pipeLine.getPipeLineName());
        List<Pipe> pipes = pipeLine.getPipes();
        Assertions.assertEquals(2, pipes.size());
        for (Pipe pipe : pipes) {
            List<AbstractTask> tasks = pipe.getTasks();
            Assertions.assertEquals(2, tasks.size());
            tasks.forEach(new Consumer<AbstractTask>() {
                @Override
                public void accept(AbstractTask task) {
                    System.out.println(task.getTaskName());
                }
            });
        }

        TaskInput input = new TaskInput();
        input.setProperty("start", LocalDate.now().toString());
        pipeLine.init();
        TaskResult result = pipeLine.process(null,input);
        System.out.println(result);

    }
}
