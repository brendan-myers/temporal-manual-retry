package com.brendan.temporal.myWorkflow;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface MyActivity {
    int getRandomValue();
}
