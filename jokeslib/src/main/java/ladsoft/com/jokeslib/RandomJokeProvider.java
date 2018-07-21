package ladsoft.com.jokeslib;

import ladsoft.com.jokeslib.entity.Joke;

public class RandomJokeProvider {

    public RandomJokeProvider() {}

    public Joke getRandomJoke() {
        return new Joke("This is a randomly provided joke.");
    }
}
