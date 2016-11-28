package mountainq.helloegg.tiptourguide.manager;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import mountainq.helloegg.tiptourguide.ApplicationController;

/**
 * Created by oneno on 2016-03-16.
 */
public class PropertyManager {
    private static PropertyManager instance;
    public static PropertyManager getInstance() {
        if(instance==null){
            instance =new PropertyManager();
        }
        return instance;
    }
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    private PropertyManager(){
        mPrefs =
                PreferenceManager.getDefaultSharedPreferences(ApplicationController.getInstance().getApplicationContext());
        mEditor = mPrefs.edit();
        mEditor.apply();
    }

    private static final String USER_IDX = "user_idx";
        public int getUserIdx(){
            return mPrefs.getInt(USER_IDX, -1);
        }
        public void setUserIdx(int userIdx){
            mEditor.putInt(USER_IDX,userIdx);
            mEditor.commit();
        }

    private static final String AUTO_LOGIN ="autologin";
        public int getAutoLogin(){
            return mPrefs.getInt(AUTO_LOGIN,-1);
        }
        public void setAutoLogin(int autoLogin){
            mEditor.putInt(AUTO_LOGIN,autoLogin);
            mEditor.commit();
        }
    private static final String PUSH_OK = "push";
        public int getPushOk(){
            return mPrefs.getInt(PUSH_OK, -1);
        }
        public void setPushOk(int pushOk){
            mEditor.putInt(PUSH_OK, pushOk);
            mEditor.commit();
        }
    private static final String PUSH_TOKEN = "token";
        public String getPushToken() {
            return mPrefs.getString(PUSH_TOKEN, "");
        }
        public void setPushToken(String token){
            mEditor.putString(PUSH_TOKEN, token);
            mEditor.commit();
        }

        public void clear(){
            mEditor.clear();
            mEditor.commit();
        }
}
