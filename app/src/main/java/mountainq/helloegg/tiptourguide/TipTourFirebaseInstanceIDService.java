package mountainq.helloegg.tiptourguide;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by dnay2 on 2016-11-21.
 */

public class TipTourFirebaseInstanceIDService extends FirebaseInstanceIdService {

    static final String TAG = "MyFirebaseIDService";
    ApplicationController app;

    @Override
    public void onTokenRefresh() {
        Log.d("test", "tokenrefresh");
        //Get updated InstanceID Token.
        // only First time can get token another you can't see token,
        // If you didn't see your token, i recommend delete and reinstall your app

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token : " + refreshedToken);
        app = (ApplicationController) getApplicationContext();
        app.setToken(refreshedToken);

        //TODO: Implement this method to send any registration to your app's servers

//        sendRegistrationToServer(refreshedToken); // upload token to your server
    }

    /**
     * 디바이스에 토큰을 바로 올리는 거라 우리는 필요 ㄴㄴ 어플에 저장해 두었다가 한버에 올립시다
     * @param token
     */
    private void sendRegistrationToServer(String token) {

        //Need Okhttp library in Gradle(level app)
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token", token)
                .build();

        //request
        Log.d("Test", token);
        Request request = new Request.Builder()
                .url(""/* your server address*/)
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
