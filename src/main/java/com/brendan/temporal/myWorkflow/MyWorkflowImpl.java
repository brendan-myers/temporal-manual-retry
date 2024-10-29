package com.brendan.temporal.myWorkflow;

import java.time.Duration;

import org.slf4j.Logger;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;

@WorkflowImpl(taskQueues = "example-queue")
public class MyWorkflowImpl implements MyWorkflow {
    Logger logger = Workflow.getLogger(MyWorkflow.class);

    RetryType retry = RetryType.NOT_SET;

    @Override
    public void retry() {
        this.retry = RetryType.SHOULD_RETRY;
    }

    @Override
    public void fail() {
        this.retry = RetryType.DONT_RETRY;
    }
    
    private MyActivity activity = Workflow.newActivityStub(
        MyActivity.class,
        ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(2))
            .setRetryOptions(
                RetryOptions.newBuilder()
                .setDoNotRetry(RandomException.class.getName())
                .build()
            ).build()
    );

    @Override
    public void run() {
        logger.info("Starting workflow");

        while(true) try {
            retry = RetryType.NOT_SET;
            activity.getRandomValue();
            break;
        } catch (Exception e) {
            logger.info("Activity failed. Waiting 30 seconds for retry signal.");

            boolean receivedRetrySignal = Workflow.await(
                Duration.ofSeconds(30), 
                () -> retry != RetryType.NOT_SET
            );

            if (!receivedRetrySignal) {
                logger.info("Retry signal not received.");
            }

            if (!receivedRetrySignal || retry == RetryType.DONT_RETRY) {
                throw Workflow.wrap(new Exception("Failed."));
            }

            logger.info("Retrying activity");
        }

        logger.info("Workflow complete");
    }
}
