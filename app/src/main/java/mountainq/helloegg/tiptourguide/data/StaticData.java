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



    private static final String SERVICE_KEY = "kKxwGFgF3Nl%2FYmNPy7fLIoEbzMhhtrH1THy1IvcHm6yxia1I%2BhtyivenbGNWHGYanSNrtSXioCZpBdUlZkNW4Q%3D%3D";
    private static final String MOBILE_OS = "AND";
    private static final String CONTENT_TYPE_ID = "12";
    private static final String APP_NAME = "tiptour_guide";

    public static final String TOUR_API_SEARCH_URL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?ServiceKey="+SERVICE_KEY+"&MobileOS="+MOBILE_OS+"&MobileApp="+APP_NAME+"&ContentTypeId="+CONTENT_TYPE_ID+"&keyword=";
    public static final String TMAP_API_KEY = "52141bc0-08fa-3309-af84-0cfc120c0101";
    public Typeface font1;
    public Typeface font2;

    public static final int MAIN_COLOR = 0xff8c9b34;
    public static final int WHITE_TEXT_COLOR = 0xffffffff;


    /**
     * 푸시 구분 데이터
     */
    public static final String GUIDE = "200";
    public static final String CUSTOMER_YES = "300";
    public static final String CUSTOMER_NO = "400";


    /**
     * DummyData
     */
    public static final double DUMMY_LON = 126.981611;
    public static final double DUMMY_LAT = 37.568477;

    public static final double DUMMY_START_X = 126.9926031738;
    public static final double DUMMY_START_Y = 37.5628438177;

    public static final double DUMMY_END_X = 126.9997414083;
    public static final double DUMMY_END_Y = 37.5629828186;

    public static final double TEST_START_X = 126.960358;
    public static final double TEST_START_Y = 37.466963;

    public static final double TEST_END_X = 126.948138;
    public static final double TEST_END_Y = 37.461643;


    /**
     * 보내야 될 데이터 잠시 저장
     */
    private PushDatas tempPushDatas = null;
        public PushDatas getTempPushDatas() {
            return tempPushDatas;
        }
        public void setTempPushDatas(PushDatas tempPushDatas) {
            this.tempPushDatas = tempPushDatas;
        }
}
