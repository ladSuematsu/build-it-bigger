package com.udacity.gradle.builditbigger.task;

import com.udacity.gradle.builditbigger.idlingresources.EspressoIdlingResource;

public class TestJokeProviderTask extends JokeProviderTask {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        EspressoIdlingResource.increment();
    }

    @Override
    protected void onPostExecute(String responseContent) {
        EspressoIdlingResource.decrement();
        super.onPostExecute(responseContent);
    }

}
