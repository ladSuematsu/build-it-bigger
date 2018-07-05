package ladsoft.com.jokedisplay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ladsoft.com.jokedisplay.R;

public class JokeDisplayActivity extends AppCompatActivity {
    public static final String EXTRA_PROVIDED_JOKE = "extra_provided_joke";

    String providedJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        loadProvidedJoke();
    }

    private void loadProvidedJoke() {
        Intent providedIntent = getIntent();
        if (!providedIntent.hasExtra(EXTRA_PROVIDED_JOKE)) {
            onProvidedJokeLoadError();
            return;
        }

        String providedJoke = providedIntent.getStringExtra(EXTRA_PROVIDED_JOKE);
        if (providedJoke.isEmpty()) {
            onProvidedJokeEmptyContent();
            return;
        }

        onLoadProvidedJoke(providedJoke);
    }

    private void onProvidedJokeLoadError() {
        Toast.makeText(this, R.string.provided_joke_load_error, Toast.LENGTH_LONG)
                .show();

        finish();
    }

    private void onProvidedJokeEmptyContent() {

    }

    private void onLoadProvidedJoke(String joke) {

    }
}
