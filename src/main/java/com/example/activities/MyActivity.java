package com.example.activities;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface MyActivity {
    int getRandomValue();
}
