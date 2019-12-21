package io.github.ctlove0523.pattern.pipeline.core.tasks;

import io.github.ctlove0523.pattern.pipeline.core.deploy.DeployInput;
import io.github.ctlove0523.pattern.pipeline.core.deploy.DeployOutput;
import io.github.ctlove0523.pattern.pipeline.core.deploy.DeployPipeImpl;
import io.github.ctlove0523.pattern.pipeline.core.pipe.AbstractPipeLine;
import io.github.ctlove0523.pattern.pipeline.core.pipe.PipeLineParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Optional;

public class PipeLineParserTest {

    @Test
    public void test_parse_returnSuccess() throws Exception {
        File file = ResourceUtils.getFile("classpath:pipeline.xml");
        Optional<AbstractPipeLine> deployPipeline = PipeLineParser.parse(file);
        Assertions.assertTrue(deployPipeline.isPresent());
        Assertions.assertEquals(deployPipeline.get().getPipes().size(),2);
    }
}
