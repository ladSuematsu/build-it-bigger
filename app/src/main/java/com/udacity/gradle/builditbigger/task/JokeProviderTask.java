package com.udacity.gradle.builditbigger.task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;
import com.udacity.gradle.builditbigger.backend.jokeApi.model.MyBean;

public class JokeProviderTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = "JOKE_TASK";
    private static JokeApi apiService = null;

    public Callback callback;

    public interface Callback {
        void onRequestResponse(String responseContent);
        void onError();
    }

    public void attach(Callback listener) {
        this.callback = listener;
    }

    public void detach() {
        this.callback = null;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String response = "";

        try {
            if (apiService == null) {
                JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> request) {
                                request.setDisableGZipContent(true);
                            }
                        });

                apiService = builder.build();
            }

            MyBean requestResponse = apiService.tellJoke().execute();

            response = requestResponse.getJoke();
        } catch (Exception e) {
            Log.e(TAG, "Request gone wrong", e);
        }

        return response != null ? response : "";
    }

    @Override
    protected void onPostExecute(String responseContent) {
        if (callback != null) {
            if (!responseContent.isEmpty()) {
                callback.onRequestResponse(responseContent);
            } else {
                callback.onError();
            }
        }
    }

    @Override
    protected void onCancelled() {
        detach();
    }
}
