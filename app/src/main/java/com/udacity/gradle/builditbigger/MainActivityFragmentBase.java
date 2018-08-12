package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.task.JokeProviderTask;
import com.udacity.gradle.builditbigger.task.TaskFactory;

import ladsoft.com.jokedisplay.activity.JokeDisplayActivity;


/**
 * A placeholder fragment containing a simple view.
 */
abstract class MainActivityFragmentBase extends Fragment {

    public JokeProviderTask jokeTask;
    private ProgressBar progressBar;

    JokeProviderTask.Callback jokeTaskCallback = new JokeProviderTask.Callback() {
        @Override
        public void onRequestResponse(String responseContent) {
            progressBar.setVisibility(View.GONE);

            startActivity(new Intent(getContext(), JokeDisplayActivity.class)
                    .putExtra(JokeDisplayActivity.EXTRA_PROVIDED_JOKE, responseContent));
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
        View root = inflater.inflate(R.layout.fragment_main, container, false);


        progressBar = root.findViewById(R.id.progress);
        Button tellJoke = root.findViewById(R.id.button_tel_joke);
        tellJoke.setOnClickListener(tellJokeListener);

        return root;
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
            jokeTask = TaskFactory.taskFactory(getContext());
            jokeTask.attach(jokeTaskCallback);
            jokeTask.execute();
        }
    }
}
