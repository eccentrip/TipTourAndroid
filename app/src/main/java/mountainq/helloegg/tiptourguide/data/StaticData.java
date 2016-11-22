package mountainq.helloegg.tiptourguide.data;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by dnay2 on 2016-11-18.
 */

public class StaticData {

    private static StaticData instance;
    public static StaticData getInstance(){
        return instance;
    }
    static {instance = new StaticData();}

    private int width, height;

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDisplayPixels(Context context){
        width = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public static final String SERVICE_KEY = "kKxwGFgF3Nl%2FYmNPy7fLIoEbzMhhtrH1THy1IvcHm6yxia1I%2BhtyivenbGNWHGYanSNrtSXioCZpBdUlZkNW4Q%3D%3D";
    public static final String MOBILE_OS = "AND";
    public static final String CONTENT_TYPE_ID = "12";
    public static final String APP_NAME = "tiptour_guide";

    public Typeface font1;
    public Typeface font2;

    public static final int MAIN_COLOR = 0xff8c9b34;
    public static final int WHITE_TEXT_COLOR = 0xffffffff;





}
