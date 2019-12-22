package io.github.ctlove0523.pattern.pipeline.core.tasks;

import io.github.ctlove0523.pattern.pipeline.core.deploy.DeployInput;
import io.github.ctlove0523.pattern.pipeline.core.deploy.DeployOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class OrderTask extends AbstractTask<DeployInput, DeployOutput> {
    @Override
    public void prepare() {
        log.info("{} task prepare success", getTaskName());
    }

    @Override
    public DeployOutput start(DeployInput input) {
        String userName = input.getProperty("user_name");
        String userPwd = input.getProperty("user_pwd");
        log.info("username = {},usepwd = {}", userName, userPwd);
        DeployOutput output = new DeployOutput();
        output.setProperty("order_id", UUID.randomUUID().toString());
        return output;
    }

    @Override
    public void stop() {
        log.info("{} task stop", getTaskName());
    }

    @Override
    public DeployOutput retry(DeployInput input) {
        String userName = input.getProperty("user_name");
        String userPwd = input.getProperty("user_pwd");
        log.info("username = {},usepwd = {}", userName, userPwd);
        DeployOutput output = new DeployOutput();
        output.setProperty("order_id", UUID.randomUUID().toString());
        return output;
    }

    @Override
    public void rollback(DeployInput input) {
        log.info("{} task begin rollback", getTaskName());
    }
}
