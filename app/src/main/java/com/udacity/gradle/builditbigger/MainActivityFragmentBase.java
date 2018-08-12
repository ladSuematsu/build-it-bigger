package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.task.JokeProviderTask;
import com.udacity.gradle.builditbigger.task.TaskFactory;

import ladsoft.com.jokedisplay.activity.JokeDisplayActivity;


/**
 * A placeholder fragment containing a simple view.
 */
abstract class MainActivityFragmentBase extends Fragment {

    public JokeProviderTask jokeTask;
    private ProgressBar progressBar;
    private View rootView;

    JokeProviderTask.Callback jokeTaskCallback = new JokeProviderTask.Callback() {
        @Override
        public void onRequestResponse(String responseContent) {
            progressBar.setVisibility(View.GONE);

            startActivity(new Intent(getContext(), JokeDisplayActivity.class)
                    .putExtra(JokeDisplayActivity.EXTRA_PROVIDED_JOKE, responseContent));
        }

        @Override
        public void onError() {
            progressBar.setVisibility(View.GONE);

            Snackbar.make(rootView, R.string.joke_request_error, Snackbar.LENGTH_LONG)
                    .show();
        }
    };

    View.OnClickListener tellJokeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            fetchJoke();
        }
    };

    public MainActivityFragmentBase() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);


        progressBar = rootView.findViewById(R.id.progress);
        Button tellJoke = rootView.findViewById(R.id.button_tel_joke);
        tellJoke.setOnClickListener(tellJokeListener);

        return rootView;
    }

    @Override
    public void onPause() {
        if (jokeTask != null) {
            progressBar.setVisibility(View.GONE);
            jokeTask.cancel(true);
            jokeTask = null;
        }

        super.onPause();
    }

    protected void fetchJoke() {
        if (jokeTask == null
                || jokeTask.getStatus() == AsyncTask.Status.FINISHED
                || jokeTask.isCancelled()) {
            progressBar.setVisibility(View.VISIBLE);
            jokeTask = TaskFactory.provideJokeProviderTask();
            jokeTask.attach(jokeTaskCallback);
            jokeTask.execute();
        }
    }
}
