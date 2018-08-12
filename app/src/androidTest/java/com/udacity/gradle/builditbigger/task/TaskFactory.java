package com.udacity.gradle.builditbigger.task;

public abstract class TaskFactory {
    public static JokeProviderTask provideJokeProviderTask() {
        return new TestJokeProviderTask();
    }
}
