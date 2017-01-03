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
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

import mountainq.helloegg.tiptourguide.data.StaticData;
import mountainq.helloegg.tiptourguide.data.User;
import mountainq.helloegg.tiptourguide.interfaces.NetworkService;
import mountainq.helloegg.tiptourguide.manager.LOG;
import mountainq.helloegg.tiptourguide.manager.PropertyManager;
import retrofit2.Call;

/**
 * Created by dnay2 on 2016-11-21.
 */

public class TipTourFireBaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMsgService";
    private Bitmap icon = null;
    Uri defaultSoundUri = null;

    // [START receive_message]

    @Override //메시지를 받으면 호출
    public void onMessageReceived(RemoteMessage remoteMessage) {
        sendNotification(remoteMessage.getData());
    }

    /**
     * 넘어오는 파라미터들을 모두 읽어들인다.
     *
     * @param data
     */
    private void sendNotification(Map<String, String> data) {
        HashMap<String, String> newData = new HashMap<>(data);

        LOG.DEBUG("test push : " + newData.toString());
        String code = String.valueOf(newData.get("code"));
        String idx = String.valueOf(newData.get("idx"));
        String title = "";
        String message = "";

        if(code == null || code.equals("")) return;
        switch (code) {
            case StaticData.GUIDE:
                title += "[가이드 알림]";
                message += "가이드 요청이 왔습니다.";
                break;
            case StaticData.CUSTOMER_YES:
                title += "[여행 수락 알림]";
                message += "가이드가 요청을 수락했습니다.";
                break;
            case StaticData.CUSTOMER_NO:
                title += "[여행 거절 알림]";
                message += "가이드가 요청을 거절했습니다.";
                break;
        }

        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_notice_alarm);

        //알람소리
        defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        /**
         * 푸시여부를 저장해둔 SharedPreferences 값을 가져와서 비교 -1이 아니면 알림창 뜨기
         */
        PropertyManager sharedPreferences = PropertyManager.getInstance();
        if (sharedPreferences.getPushOk() > 0) {
            //Notification
            NotificationCompat.Builder notificationBuilder = buildSimpleNotification(code, idx, title, message);
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0 /* ID of notification*/, notificationBuilder.build());
        }

    }

    private NotificationCompat.Builder buildSimpleNotification(String code, String idx, String title, String message) {
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notice_alarm)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis())
                .setSound(defaultSoundUri)
                .setDefaults(Notification.DEFAULT_ALL)
                .setColor(StaticData.MAIN_COLOR)
                .setContentIntent(getPendingIntent(code, idx));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setCategory(Notification.CATEGORY_MESSAGE)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
                    .setColor(StaticData.MAIN_COLOR)
                    .setSmallIcon(R.drawable.ic_notice_alarm);

        }
        return builder;
    }

    private NotificationCompat.Builder buildCustomNotification(String code, String id, String title, String message) {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notificationbar);
        remoteViews.setImageViewResource(R.id.logoimg, R.mipmap.ic_launcher);
        remoteViews.setTextViewText(R.id.title, title);
        remoteViews.setTextViewText(R.id.message, message);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setContent(remoteViews)
                .setSmallIcon(R.drawable.ic_notice_alarm)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setShowWhen(true)
                .setContentIntent(getPendingIntent(code, id))
                .setDefaults(Notification.DEFAULT_ALL);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationBuilder.setCategory(Notification.CATEGORY_MESSAGE)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setVisibility(Notification.VISIBILITY_PUBLIC);
        }


        return notificationBuilder;
    }

    private PendingIntent getPendingIntent(String code, String idx) {
        Intent intent = null;

        intent = new Intent(this, MainActivity.class);
        intent.putExtra("code", code);
        intent.putExtra("idx", idx);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(LoginActivity.class);
        stackBuilder.addNextIntent(intent);

        return stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }

    private void updateGPS() {
        ApplicationController app = (ApplicationController) getApplicationContext();
        NetworkService service = app.getNetworkService();
        User body = new User();
        body.setId(app.user_id);
        Call<User> response = service.newContent(body);
    }
}
