package io.github.ctlove0523.pattern.pipeline.core;

import io.github.ctlove0523.pattern.pipeline.core.deploy.DeployInput;
import io.github.ctlove0523.pattern.pipeline.core.deploy.DeployOutput;
import io.github.ctlove0523.pattern.pipeline.core.pipe.AbstractPipeLine;
import io.github.ctlove0523.pattern.pipeline.core.pipe.PipeLineParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Optional;

@SpringBootTest
public class PipeLineParserTest {

    @Test
    public void test_parse_returnSuccess() throws Exception {
        File file = ResourceUtils.getFile("classpath:pipeline.xml");
        Optional<AbstractPipeLine<?,?>> deployPipeline = PipeLineParser.parse(file);
        Assertions.assertTrue(deployPipeline.isPresent());
        Assertions.assertEquals(deployPipeline.get().getPipes().size(),2);
        AbstractPipeLine<DeployInput, DeployOutput> pipeLine = (AbstractPipeLine<DeployInput, DeployOutput>)deployPipeline.get();
        DeployInput input = new DeployInput();
        input.setProperty("pwd","123456");
        pipeLine.prepare();
        pipeLine.start(input);
    }
}
