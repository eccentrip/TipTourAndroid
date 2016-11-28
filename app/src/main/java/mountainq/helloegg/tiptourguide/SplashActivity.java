package mountainq.helloegg.tiptourguide;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import mountainq.helloegg.tiptourguide.activities.SActivity;
import mountainq.helloegg.tiptourguide.manager.LOG;

/**
 * Created by dnay2 on 2016-11-21.
 */

public class SplashActivity extends SActivity {

    private static final int APP_PERMISSIONS = 100;
    private boolean arePermissionsOk = true;
    /**
     * 퍼미션 체크 함수
     */
    private void checkPermissions() {
        Toast.makeText(SplashActivity.this, "권한 사용이 설정되어야 사용이 가능합니다.", Toast.LENGTH_SHORT).show();
        if (
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
                Toast.makeText(SplashActivity.this, "효율적인 서비스 이용을 위해 GPS 등의 정보에 대한 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_PHONE_STATE
            }, APP_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case APP_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[2] == PackageManager.PERMISSION_GRANTED
                        ){
                    LOG.DEBUG(permissions[0] + grantResults[0] + permissions[1] + grantResults[1] + permissions[2] + grantResults[2]);
                    arePermissionsOk = true;
                }else{
                    arePermissionsOk = false;
                }
                break;
        }
        new SplashTask().execute();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_splash);
        /**
         * 어플이 생성되면 먼저 권한 체크를 하도록 하자 그래야 마시멜로 이상 버젼에서도 충분히 돌아가니까
         */
        ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions();
        }

        Log.d("Test", "SplashAcitivity : start");
        new SplashThread().run();
    }


    class SplashThread extends Thread {
        @Override
        public void run() {
            SystemClock.sleep(1800);
            Log.d("Test", "소가 넘어간다앙");
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }


    class SplashTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(1800);
            Log.d("Test", "소가 넘어간다앙");
            if(arePermissionsOk){
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            } else{
                publishProgress();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Toast.makeText(SplashActivity.this, "권한이 충족되지 않습니다. 다시 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            finish();
        }
    }
}
