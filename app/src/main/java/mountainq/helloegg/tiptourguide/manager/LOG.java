package mountainq.helloegg.tiptourguide.manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by dnay2 on 2016-11-25.
 */

public class LOG {

    private static boolean DEBUG = true;

    static public void toast(Context context, String message){
        Toast.makeText(context, " 메시지 : " + message, Toast.LENGTH_SHORT).show();
    }

    static public void ERROR(String errorMessage){
        Log.e("TestError", errorMessage);
    }

    static public void DEBUG(String debugMessage){
        Log.d("Test", debugMessage);
    }
}
