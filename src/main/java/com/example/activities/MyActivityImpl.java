package com.example.activities;

import org.springframework.stereotype.Component;

import com.example.RandomException;

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