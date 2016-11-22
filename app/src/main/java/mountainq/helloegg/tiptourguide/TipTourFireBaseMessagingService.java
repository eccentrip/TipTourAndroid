package mountainq.helloegg.tiptourguide;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dnay2 on 2016-11-21.
 */

public class TipTourFireBaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMsgService";
    private Bitmap icon = null;
    Intent mainIntent = null;
    Uri defaultSoundUri = null;
    PendingIntent pendingIntent = null;
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
    private void sendNotification(Map<String, String> data){
        HashMap<String, String> newData = new HashMap<>(data);
        String title;
        if(newData.get("title") != null && !newData.equals("")) title = newData.get("title");
        else title = "sample";
        String message = newData.get("message");
        icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);


        mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0 /* Request Code*/,mainIntent, PendingIntent.FLAG_ONE_SHOT);

        //알람소리
        defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);


        //Notification
        NotificationCompat.Builder notificationBuilder = buildCustomNotification(0, title, message);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification*/, notificationBuilder.build());
    }


    private NotificationCompat.Builder buildSimpleNotification(int code, String title, String message){
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(icon)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(false)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        return notificationBuilder;
    }

    private NotificationCompat.Builder buildCustomNotification(int code, String title, String message){
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notificationbar);
        remoteViews.setImageViewResource(R.id.logoimg, R.mipmap.ic_launcher);
        remoteViews.setTextViewText(R.id.title, title);
        remoteViews.setTextViewText(R.id.message, message);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(false)
                .setContent(remoteViews)
                .setTicker("sample ticker")
                .setContentIntent(pendingIntent);


        return notificationBuilder;
    }
}
