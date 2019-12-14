package io.github.ctlove0523.pattern.pipeline.pipe;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class PipeLineParser {
    public static Optional<PipeLine> parse(File file) {
        Digester digester = createDigester();
        PipeLine pipeLine = null;
        try {
            pipeLine = digester.parse(file);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(pipeLine);
    }

    public static Optional<PipeLine> parse(InputStream is) {
        Digester digester = createDigester();
        PipeLine pipeLine = null;
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
        String pipeLinePattern = "pipeline";
        digester.addObjectCreate(pipeLinePattern, PipeLine.class.getName());
        digester.addSetProperties(pipeLinePattern);

        String pipePattern = pipeLinePattern + "/pipe";
        digester.addObjectCreate(pipePattern, Pipe.class.getName());
        digester.addSetProperties(pipePattern);
        digester.addSetProperties(pipePattern, "name", "pipeName");
        digester.addSetNext(pipePattern, "addPipe");

        String taskPatter = pipePattern + "/task";
        digester.addObjectCreate(taskPatter, BaseTask.class.getName());
        digester.addSetProperties(taskPatter);
        digester.addSetProperties(taskPatter, "name", "taskName");
        digester.addSetProperties(taskPatter, "id", "taskId");
        digester.addSetProperties(taskPatter, "class", "taskClass");
        digester.addSetNext(taskPatter, "addTask");

        return digester;
    }
}
