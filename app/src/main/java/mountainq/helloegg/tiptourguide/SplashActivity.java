package mountainq.helloegg.tiptourguide;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dnay2 on 2016-11-21.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_splash);

        new SplashTask().execute();
    }

    class SplashTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(1800);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);

        }
    }
}
