package io.github.ctlove0523.pattern.pipeline.core.tasks;

import io.github.ctlove0523.pattern.pipeline.core.deploy.DeployInput;
import io.github.ctlove0523.pattern.pipeline.core.deploy.DeployOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateUserTask extends AbstractTask<DeployInput, DeployOutput> {

    @Override
    public void prepare() {
        log.info("{} prepared success", getTaskName());
    }

    @Override
    public DeployOutput start(DeployInput input) {
        String pwd = input.getProperty("pwd");
        log.info("pwd is {}", pwd);

        DeployOutput output = new DeployOutput();
        output.setProperty("user_name", "op_svc_container");
        output.setProperty("user_pwd", "Pipeline@123");
        return output;
    }

    @Override
    public void stop() {
        log.info("{} task stop", getTaskName());
    }

    @Override
    public DeployOutput retry(DeployInput input) {
        String pwd = input.getProperty("pwe");
        log.info("pwd is {}", pwd);

        DeployOutput output = new DeployOutput();
        output.setProperty("user_name", "op_svc_container");
        output.setProperty("user_pwd", "Pipeline@123");
        return output;
    }

    @Override
    public void rollback(DeployInput input) {
        log.info("begin to rollback");
    }
}
