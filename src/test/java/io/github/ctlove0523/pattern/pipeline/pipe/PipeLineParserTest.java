package io.github.ctlove0523.pattern.pipeline.pipe;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class PipeLineParserTest {

    @Test
    public void test() throws Exception {
        File file = new File("D:\\codes\\pipeline\\src\\main\\resources\\pipeline.xml");
        PipeLineParser.parse(file);
    }
 }
