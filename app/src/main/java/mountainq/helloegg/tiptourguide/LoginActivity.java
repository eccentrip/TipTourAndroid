package mountainq.helloegg.tiptourguide;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import mountainq.helloegg.tiptourguide.activities.SActivity;
import mountainq.helloegg.tiptourguide.data.StaticData;
import mountainq.helloegg.tiptourguide.data.User;
import mountainq.helloegg.tiptourguide.interfaces.NetworkService;
import mountainq.helloegg.tiptourguide.manager.PropertyManager;
import mountainq.helloegg.tiptourguide.register.RegisterActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by sukyami on 16. 1. 12..
 */
public class LoginActivity extends SActivity {

    StaticData mData = StaticData.getInstance();
    PropertyManager sharedPreferences = PropertyManager.getInstance();

    public void btnHandler(View v) {
        switch (v.getId()) {
            case R.id.login_join_member_btn:
                sendData();
                break;
            case R.id.register:
                moveRegister();
                break;
        }
    }

    private EditText inputName;
    private Button btnLogin;
    private Button btnLinkToRegisterScreen;
    private EditText inputPassword;
    String name;
    String password;
    private NetworkService networkService;

    ApplicationController app;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        networkService = ApplicationController.getInstance().getNetworkService();
        app = (ApplicationController) getApplicationContext();

        Intent intent = getIntent();
        String code = intent.getStringExtra("code");
        if(code != null && code.equals("300")) showRegisterNotification();

        inputName = (EditText) findViewById(R.id.name_input);
        inputPassword = (EditText) findViewById(R.id.password_input);
        btnLogin = (Button) findViewById(R.id.login_join_member_btn);
        btnLinkToRegisterScreen = (Button) findViewById(R.id.register);

        if(sharedPreferences.getPushToken() == null || sharedPreferences.getPushToken().equals("")){
            FirebaseMessaging.getInstance().subscribeToTopic("tiptour");
            String msg = "msg_subscribe";
            Log.d("Test", msg);
            String token = FirebaseInstanceId.getInstance().getToken();
            Log.d("Test", "token = " + token);
            sharedPreferences.setPushToken(token);
            sharedPreferences.setAutoLogin(1);
            sharedPreferences.setPushOk(1);
            app.setToken(token);
        }

    }


    /**
     * function to verify login details in mysql db
     */


    private void sendData() {

        name = inputName.getText().toString();
        password = inputPassword.getText().toString().trim();

        User loggingin = new User();
        Log.e("sangik", name + "  " + password);
        loggingin.setName(name);
        loggingin.setPassword(password);
        loggingin.setDeviceid(app.getDeviceid());
        loggingin.setToken(sharedPreferences.getPushToken());

        Call<User> loginCall = networkService.newThumbnail(loggingin);
        loginCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(LoginActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                    User tempContent = response.body();

                    app.user_id = tempContent.getId();
                    mData.setUserId(tempContent.getId());

                    Log.i("MyTag", "유저아이디 제데로 넘어왓나요: " + app.user_id + "  ");
                    Intent i = new Intent(getApplicationContext(),
                            MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "로그인 실패.. 아이디와 비밀번호를 다시 확인해 주세요", Toast.LENGTH_SHORT).show();
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void moveRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public void setNotification(View v) {
        //Notification
        NotificationCompat.Builder notificationBuilder = buildSimpleNotification(0, "환영합니다.", "팁투어에 오신것을 환영합니다.");

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1 /* ID of notification*/, notificationBuilder.build());
    }

    private void showRegisterNotification(){
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("[알림]")
                .setContentText("축하드립니다.")
                .setAutoCancel(true)
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis())
                .setSound(defaultSoundUri)
                .setColor(StaticData.MAIN_COLOR)
                .setDefaults(Notification.DEFAULT_ALL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setCategory(Notification.CATEGORY_MESSAGE)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
                    .setColor(StaticData.MAIN_COLOR)
                    .setSmallIcon(R.drawable.ic_stat_name);

        }

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1 /* ID of notification*/, builder.build());
    }


    private NotificationCompat.Builder buildCustomNotification(int code, String title, String message) {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notificationbar);
        remoteViews.setImageViewResource(R.id.logoimg, R.drawable.ic_stat_name);
        remoteViews.setTextViewText(R.id.title, title);
        remoteViews.setTextViewText(R.id.message, message);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setContent(remoteViews)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setShowWhen(true)
                .setContentIntent(getPendingIntent(1))
                .setDefaults(Notification.DEFAULT_ALL);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationBuilder.setCategory(Notification.CATEGORY_MESSAGE)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setVisibility(Notification.VISIBILITY_PUBLIC);
        }
        return notificationBuilder;
    }

    private NotificationCompat.Builder buildSimpleNotification(int code, String title, String message) {
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis())
                .setSound(defaultSoundUri)
                .setColor(StaticData.MAIN_COLOR)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(getPendingIntent(1));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setCategory(Notification.CATEGORY_MESSAGE)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
                    .setColor(StaticData.MAIN_COLOR)
                    .setSmallIcon(R.drawable.ic_stat_name);

        }
        return builder;
    }

    private PendingIntent getPendingIntent(int code) {
        Intent intent = null;

        switch (code) {
            case 1:
                intent = new Intent(this, MainActivity.class);
                break;
        }
        app.user_id = 1;
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(LoginActivity.class);
        stackBuilder.addNextIntent(intent);

        return stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }


}
