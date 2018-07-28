package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import ladsoft.com.jokeslib.RandomJokeProvider;
import ladsoft.com.jokeslib.entity.Joke;

/** An endpoint class we are exposing */
@Api(
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    RandomJokeProvider jokeProvider = new RandomJokeProvider();


    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "tellJoke")
    public MyBean tellJoke() {
        MyBean response = new MyBean();

        Joke randomJoke = jokeProvider.getRandomJoke();
        response.setJoke(randomJoke.getJokeContent());

        return response;
    }

}
