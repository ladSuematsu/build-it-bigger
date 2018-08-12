package ladsoft.com.jokeslib;

import java.util.Random;

import ladsoft.com.jokeslib.entity.Joke;

public class RandomJokeProvider {

    private final Random random = new Random();
    private final String[] jokes = {
            "This is a randomly provided joke.",
            "This is a randomly provided joke 2.0",
            "This is a randomly provided Hyper Golden Edition Joke"
    };

    public RandomJokeProvider() {}

    public Joke getRandomJoke() {
        int index = random.nextInt(jokes.length);

        return new Joke(jokes[index]);
    }
}
