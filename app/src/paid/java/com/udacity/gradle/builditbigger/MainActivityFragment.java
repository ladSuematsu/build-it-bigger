package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.task.JokeProviderTask;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends MainActivityFragmentBase {

    public JokeProviderTask jokeTask;
    private ProgressBar progressBar;

    public MainActivityFragment() {
    }
}
