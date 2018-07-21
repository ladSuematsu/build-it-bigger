package task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class JokeProviderTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = "JOKE_TASK";
    private static MyApi apiService = null;

    private final Context context;

    public Callback callback;

    public interface Callback {
        void onRequestResponse(String responseContent);
    }

    public JokeProviderTask(Context context) {
        this.context = context.getApplicationContext();
    }

    public void attach(Callback listener) {
        this.callback = listener;
    }

    public void detach() {
        this.callback = null;
    }


    @Override
    protected String doInBackground(Void... voids) {
        if (apiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
//                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setRootUrl("http://localhost/myApi/_ah/myApi")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {

                        }
                    });

            apiService = builder.build();
        }

        String name = "Anon";

        String response;
        try {
            response = apiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            Log.e(TAG, "Request gone wrong", e);
            response = e.getMessage();
        }

        return response;
    }

    @Override
    protected void onPostExecute(String responseContent) {
        if (callback != null) {
            callback.onRequestResponse(responseContent);
        }
    }

    @Override
    protected void onCancelled() {
        detach();
    }
}
