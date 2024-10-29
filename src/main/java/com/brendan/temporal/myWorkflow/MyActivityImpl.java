package com.brendan.temporal.myWorkflow;

import org.springframework.stereotype.Component;

import io.temporal.activity.Activity;
import io.temporal.spring.boot.ActivityImpl;

@Component
@ActivityImpl(taskQueues = "example-queue")
public class MyActivityImpl implements MyActivity {

    @Override
    public int getRandomValue() {
        int rnd = (int) (Math.random() * 100);

        if (rnd > 50) {
            throw Activity.wrap(new RandomException("Random activity failure"));
        }

        return rnd;
    }
}
