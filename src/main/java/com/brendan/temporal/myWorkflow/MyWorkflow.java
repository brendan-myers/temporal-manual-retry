package com.brendan.temporal.myWorkflow;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface MyWorkflow  {
    @WorkflowMethod
    void run();

    @SignalMethod
    void retry();

    @SignalMethod
    void fail();
}
