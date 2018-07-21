package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import task.JokeProviderTask;


public class MainActivity extends AppCompatActivity {

    JokeProviderTask.Callback callback = new JokeProviderTask.Callback() {
        @Override
        public void onRequestResponse(String responseContent) {
            Toast.makeText(MainActivity.this, responseContent, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        if (jokeTask != null) {
            jokeTask.cancel(true);
            jokeTask = null;
        }

        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public JokeProviderTask jokeTask;
    public void tellJoke(View view) {
//        Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();
        if (jokeTask == null || jokeTask.isCancelled()) {
            jokeTask = new JokeProviderTask(this);
            jokeTask.attach(callback);
            jokeTask.execute();
        }
    }


}
