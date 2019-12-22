package io.github.ctlove0523.pattern.pipeline.core.pipe;

import io.github.ctlove0523.pattern.pipeline.core.deploy.DeployPipeImpl;
import io.github.ctlove0523.pattern.pipeline.core.deploy.DeployPipelineImpl;
import io.github.ctlove0523.pattern.pipeline.core.tasks.BaseTaskInfo;
import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author chentong
 */
public class PipeLineParser {
    public static Optional<AbstractPipeLine<?, ?>> parse(File file) {
        Digester digester = createDigester();
        AbstractPipeLine<?, ?> pipeLine = null;
        try {
            pipeLine = digester.parse(file);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(pipeLine);
    }

    public static Optional<AbstractPipeLine> parse(InputStream is) {
        Digester digester = createDigester();
        AbstractPipeLine pipeLine = null;
        try {
            pipeLine = digester.parse(is);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(pipeLine);
    }

    private static Digester createDigester() {
        Digester digester = new Digester();
        digester.setValidating(false);

        // Configure the actions we will be using
        String pipeLinePattern = "deploy-pipeline";
        digester.addObjectCreate(pipeLinePattern, DeployPipelineImpl.class.getName());
        digester.addSetProperties(pipeLinePattern);
        digester.addSetProperties(pipeLinePattern, "name", "pipeLineName");

        String pipePattern = pipeLinePattern + "/deploy-pipe";
        digester.addObjectCreate(pipePattern, DeployPipeImpl.class.getName());
        digester.addSetProperties(pipePattern);
        digester.addSetProperties(pipePattern, "name", "pipeName");
        digester.addSetProperties(pipePattern, "priority", "priority");
        digester.addSetNext(pipePattern, "addPipe");

        String taskPatter = pipePattern + "/task";
        digester.addObjectCreate(taskPatter, BaseTaskInfo.class.getName());
        digester.addSetProperties(taskPatter);
        digester.addSetProperties(taskPatter, "name", "taskName");
        digester.addSetProperties(taskPatter, "id", "taskId");
        digester.addSetProperties(taskPatter, "class", "taskClass");
        digester.addSetNext(taskPatter, "addTask");

        return digester;
    }
}
