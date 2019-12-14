package io.github.ctlove0523.pattern.pipeline.pipe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Optional;

@SpringBootTest
public class PipeLineParserTest {

    @Test
    public void test() throws Exception {
        File file = ResourceUtils.getFile("classpath:pipeline.xml");
        Optional<PipeLine> pipeLine = PipeLineParser.parse(file);
        Assertions.assertTrue(pipeLine.isPresent());
    }
 }
