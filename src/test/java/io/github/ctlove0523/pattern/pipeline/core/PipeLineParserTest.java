package io.github.ctlove0523.pattern.pipeline.core;

import io.github.ctlove0523.pattern.pipeline.core.deploy.DeployInput;
import io.github.ctlove0523.pattern.pipeline.core.deploy.DeployOutput;
import io.github.ctlove0523.pattern.pipeline.core.pipeline.AbstractPipeLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class PipeLineParserTest {

    @Test
    public void test_parse_returnSuccess() throws Exception {
        long begin = System.nanoTime();
        TimeUnit.SECONDS.sleep(1);
        System.out.println((System.nanoTime() - begin)/1000/1000);
    }
}
