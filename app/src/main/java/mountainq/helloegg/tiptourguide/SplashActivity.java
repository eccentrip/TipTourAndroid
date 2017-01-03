package mountainq.helloegg.tiptourguide;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import mountainq.helloegg.tiptourguide.activities.SActivity;
import mountainq.helloegg.tiptourguide.manager.LOG;
import mountainq.helloegg.tiptourguide.manager.PropertyManager;

/**
 * Created by dnay2 on 2016-11-21.
 */

public class SplashActivity extends SActivity {

    private PropertyManager sharedPreferences = PropertyManager.getInstance();
    private ApplicationController app = ApplicationController.getInstance();
    private static final int APP_PERMISSIONS = 100;
    private static final int SAMPLE_PERMISSIONS = 200;
    private boolean arePermissionsOk = false;
    private SplashTask splashTask = new SplashTask();

    /**
     * 퍼미션 체크 함수
     */
    private void checkPermissions() {
        Toast.makeText(SplashActivity.this, "권한 사용이 설정되어야 사용이 가능합니다.", Toast.LENGTH_SHORT).show();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission_group.LOCATION) != PackageManager.PERMISSION_GRANTED/* ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED*/) {
            LOG.DEBUG("권한이 없네");

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)/* ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)*/) {
//                Toast.makeText(SplashActivity.this, "효율적인 서비스 이용을 위해 GPS 등의 정보에 대한 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
                LOG.DEBUG("권한 취소 했니");
                ActivityCompat.requestPermissions(SplashActivity.this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        /*Manifest.permission.READ_PHONE_STATE,*/
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, APP_PERMISSIONS);

            } else {
                LOG.DEBUG("권한 좀 해줄래?");
                ActivityCompat.requestPermissions(SplashActivity.this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        /*Manifest.permission.READ_PHONE_STATE,*/
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, APP_PERMISSIONS);
            }
        } else {
            //권한이 있음을 확인한 경우
            LOG.DEBUG("permissions : OK");
        }
    }

    private void sampleCheckPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //권한이 없는 경우
            LOG.DEBUG("권한이 없네");
            //최초권한요청인지 사용자에 의한 재요청인지 확인
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //사용자가 임의로 권한을 취소시킨 경우
                LOG.DEBUG("권한 취소 했니");
                //권한 요청
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, SAMPLE_PERMISSIONS);
            } else {
                LOG.DEBUG("권한 좀 해줄래?");
                //최초로 권한을 요청하는 경우(첫실행)
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, SAMPLE_PERMISSIONS);

            }
        } else {
            //권한이 있음을 확인한 경우
            LOG.DEBUG("permissions : OK");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case APP_PERMISSIONS:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        /*grantResults[1] == PackageManager.PERMISSION_GRANTED &&*/
                         grantResults[1] != PackageManager.PERMISSION_DENIED
                        ) {
                    LOG.DEBUG("\npermission name : " + permissions[0] + "   value : " + grantResults[0] +
                            "\npermission name ; " + permissions[1] + "  value : " + grantResults[1]
                    );
                    splashTask.execute(true);
                    LOG.DEBUG("permissions : OK 동의 선택");
//                   onResume();
                } else {
                    LOG.DEBUG("permission name : " + permissions[0] + "   value : " + grantResults[0] +
                            "\npermission name ; " + permissions[1] + "  value : " + grantResults[1]
                    );
                    splashTask.execute(false);
                    LOG.DEBUG("permissions : NO 선택 안함");
                }
                break;
            case SAMPLE_PERMISSIONS:
                //사용권한한에 대한 콜백 받음
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //권한 동의 선택
                    LOG.DEBUG("permissions : OK 동의 선택");
                } else {
                    //권한 동의 선택안함
                    LOG.DEBUG("permissions : NO 선택 안함");
                }
                //예외케이스
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_splash);
        Log.d("Test", "SplashAcitivity : start");
        /**
         * 어플이 생성되면 먼저 권한 체크를 하도록 하자 그래야 마시멜로 이상 버젼에서도 충분히 돌아가니까
         */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions();
//            sampleCheckPermissions();
        } else {
            splashTask.execute(true);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(!splashTask.isRunning) splashTask.execute();
    }

    class SplashTask extends AsyncTask<Boolean, Void, Void> {
        long startTime = 0;
        boolean isRunning = false;

        @Override
        protected Void doInBackground(Boolean... params) {
            Log.d("Test", "시작함");
            startTime = System.currentTimeMillis();
            isRunning = true;
            while (System.currentTimeMillis() < startTime + 1800) {
                int cnt = 0;
            }

            Log.d("Test", "소가 넘어간다앙");
            if (params.length > 0 && params[0]) {
                if (sharedPreferences.getAutoLogin() > 0) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    app.user_id = sharedPreferences.getUserIdx();
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            } else {
                publishProgress();
                finish();
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
//            finish();
        }
    }
}
