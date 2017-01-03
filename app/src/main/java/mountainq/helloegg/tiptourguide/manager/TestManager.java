package mountainq.helloegg.tiptourguide.manager;

import com.skp.Tmap.TMapPoint;

import mountainq.helloegg.tiptourguide.data.StaticData;

/**
 * Created by dnay2 on 2016-11-29.
 */

public class TestManager {

    private static final double START_X = StaticData.TEST_START_X;
    private static final double START_Y = StaticData.TEST_START_Y;
    private static final double END_X = StaticData.TEST_END_X;
    private static final double END_Y = StaticData.TEST_END_Y;
//    public static final double MY_LOCATION_X = 37.476300;
//    public static final double MY_LOCATION_Y = 126.959187;
    public static final double MY_LOCATION_X = StaticData.TEST_START_X;
    public static final double MY_LOCATION_Y = StaticData.TEST_START_Y;

    public static final TMapPoint START_POINT = new TMapPoint(START_Y, START_X);
    public static final TMapPoint END_POINT = new TMapPoint(END_Y, END_X);



}
