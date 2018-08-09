package ladsoft.com.jokedisplay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import ladsoft.com.jokedisplay.R;

public class JokeDisplayActivity extends AppCompatActivity {
    public static final String EXTRA_PROVIDED_JOKE = "extra_provided_joke";

    private TextView jokeView;
    String providedJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);


        FrameLayout container = findViewById(R.id.content);
        getLayoutInflater().inflate(R.layout.joke_container, container, true);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        jokeView = findViewById(R.id.joke);

        loadProvidedJoke();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadProvidedJoke() {
        Intent providedIntent = getIntent();
        if (!providedIntent.hasExtra(EXTRA_PROVIDED_JOKE)) {
            onProvidedJokeLoadError();
            return;
        }

        String providedJoke = providedIntent.getStringExtra(EXTRA_PROVIDED_JOKE);
        if (providedJoke.isEmpty()) {
            onProvidedJokeLoadError();
            return;
        }

        onLoadProvidedJoke(providedJoke);
    }

    private void onProvidedJokeLoadError() {
        Toast.makeText(this, R.string.provided_joke_load_error, Toast.LENGTH_LONG)
                .show();

        finish();
    }

    private void onLoadProvidedJoke(String joke) {
        jokeView.setText(joke);
    }
}
