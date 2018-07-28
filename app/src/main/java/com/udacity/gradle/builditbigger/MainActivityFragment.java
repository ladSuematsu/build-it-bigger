package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.task.JokeProviderTask;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    JokeProviderTask.Callback jokeTaskCallback = new JokeProviderTask.Callback() {
        @Override
        public void onRequestResponse(String responseContent) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(), responseContent, Toast.LENGTH_SHORT).show();
        }
    };

    public JokeProviderTask jokeTask;
    private ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);


        progressBar = root.findViewById(R.id.progress);
        Button tellJoke = root.findViewById(R.id.button_tel_joke);
        tellJoke.setOnClickListener(tellJokeListener);

        AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
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

    View.OnClickListener tellJokeListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jokeTask == null
                        || jokeTask.getStatus() == AsyncTask.Status.FINISHED
                        || jokeTask.isCancelled()) {
                    progressBar.setVisibility(View.VISIBLE);
                    jokeTask = new JokeProviderTask(getContext());
                    jokeTask.attach(jokeTaskCallback);
                    jokeTask.execute();
                }
            }
    };
}
