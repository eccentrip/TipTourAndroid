package mountainq.helloegg.tiptourguide;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by dnay2 on 2016-11-21.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_splash);
        Log.d("Test", "SplashAcitivity : start");
        new SplashThread().run();
    }

    class SplashThread extends Thread{
        @Override
        public void run() {
            SystemClock.sleep(1800);
            Log.d("Test", "소가 넘어간다앙");
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }


    class SplashTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(1800);
            Log.d("Test", "소가 넘어간다앙");
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            return null;
        }
//cJrOFChnwX0:APA91bFoEMEureqfjE5c6o0149bhgD_PLrDXxqo45EjW_vNj9QuYanJVT41WcSXF5H2hMdpQk9x5QmKJH7BAL4lg4s5iVQi41DTvxuZxLxXPqEgOqfm6d_rpgu_X4vF1p7KvjcNZRJlW
        @Override
        protected void onPostExecute(Void aVoid) {
        }
    }
}
