package com.udacity.gradle.builditbigger.task;

import android.content.Context;

public abstract class TaskFactory {
    public static JokeProviderTask taskFactory(Context context) {
        return new TestJokeProviderTask(context);
    }
}
