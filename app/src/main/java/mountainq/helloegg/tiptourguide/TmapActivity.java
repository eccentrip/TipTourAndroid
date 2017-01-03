package mountainq.helloegg.tiptourguide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapView;

import mountainq.helloegg.tiptourguide.activities.SActivity;
import mountainq.helloegg.tiptourguide.data.SearchKeyword;
import mountainq.helloegg.tiptourguide.data.StaticData;
import mountainq.helloegg.tiptourguide.data.TravelInfo;
import mountainq.helloegg.tiptourguide.manager.LOG;

/**
 * Created by dnay2 on 2016-11-26.
 */

public class TmapActivity extends SActivity  {

    TravelInfo travelInfo = new TravelInfo();
    double distanceshow;
    double timeshow;
    TMapView tMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tMapView = new TMapView(this);
        tMapView.setSKPMapApiKey("52141bc0-08fa-3309-af84-0cfc120c0101");
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setIconVisibility(true);
        tMapView.setZoomLevel(12);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setTrackingMode(true);
        RelativeLayout main=(RelativeLayout)findViewById(R.id.relative_map);
        main.addView(tMapView);

        final TMapData tMapData = new TMapData();
//        final TMapPoint startpoint = new TMapPoint(37.57084199999, 126.98530299999);
//        final TMapPoint endpoint = new TMapPoint(37.67084199999, 126.99530299999);
        final TMapPoint startpoint = new TMapPoint(StaticData.DUMMY_START_Y, StaticData.DUMMY_START_X);
        final TMapPoint endpoint = new TMapPoint(StaticData.DUMMY_END_Y, StaticData.DUMMY_END_X);
        tMapData.findPathDataWithType(TMapData.TMapPathType.PEDESTRIAN_PATH, startpoint, endpoint, new TMapData.FindPathDataListenerCallback() {
            @Override
            public void onFindPathData(TMapPolyLine tMapPolyLine) {
                tMapView.addTMapPath(tMapPolyLine);
                double Distance = tMapPolyLine.getDistance();
                double time = Distance/1.2;
                Log.i("MyTag", "거리:"+Distance);
                Log.i("MyTag", "시간:"+time);
                travelInfo.setDistance(Distance);
                travelInfo.setTime(time);
                distanceshow = Distance;
                timeshow = time;

            }
        });
        Toast.makeText(TmapActivity.this, "총 소요 거리:"+ distanceshow+ "총 소요 시간:"+timeshow, Toast.LENGTH_SHORT).show();
//        TMapPolyLine tMapPolyLine = new TMapPolyLine();
//        tMapPolyLine.setLineColor(Color.BLUE);
//        tMapPolyLine.setLineWidth(2);
//        tMapPolyLine.addLinePoint(startpoint);
//        tMapPolyLine.addLinePoint(endpoint);
//
    }

    double distance = 0.0;

    /**
     * 두 지점을 연결해서 길로 이어주는 메소드
     *
     * @param start 시작점
     * @param end   끝점
     * @return 길이가 반환된다.
     */
    private double drawStratToEnd(SearchKeyword start, SearchKeyword end) {
        LOG.DEBUG("go to start " + start.toString() + " end" + end.toString());
        double dist = 0.0f;
        double startX = start.getMapx();
        double startY = start.getMapy();
        double endX = end.getMapx();
        double endY = end.getMapy();

        TMapData tMapData = new TMapData();
        final TMapPoint startPoint = new TMapPoint(startX, startY);
        final TMapPoint endPoint = new TMapPoint(endX, endY);
        TMapPolyLine polyLine;
//        try{
//            polyLine = tMapData.findPathDataWithType(TMapData.TMapPathType.PEDESTRIAN_PATH, startPoint, endPoint);
//            distance = polyLine.getDistance();
//            timeText.setText(DropManager.calTime(distance));
//            distanceText.setText(DropManager.calDistance(distance));
//            costText.setText(DropManager.calCost(distance));
//
//            tMapView.addTMapPolyLine("test", polyLine);
//            tMapView.setCenterPoint(startX, startY);
//        }catch (Exception e){
//            LOG.ERROR("tmap polyline error " + e.getMessage());
//        }

        tMapData.findPathDataWithType(TMapData.TMapPathType.PEDESTRIAN_PATH, startPoint, endPoint, new TMapData.FindPathDataListenerCallback() {
            @Override
            public void onFindPathData(TMapPolyLine tMapPolyLine) {
                tMapPolyLine.setLineColor(Color.RED);
                tMapPolyLine.setLineWidth(2);
//                tMapView.addTMapPolyLine("test", tMapPolyLine);
                tMapView.addTMapPath(tMapPolyLine);
                LOG.DEBUG("poly line is created : " + tMapPolyLine.toString()
                        + "\n distance : " + tMapPolyLine.getDistance());
                tMapView.setZoomLevel(15);
//                tMapView.setCenterPoint(lon, lat);
                distance = tMapPolyLine.getDistance();
            }
        });
        return distance;
    }

    /**
     * 점을 찍어서 위치를 보여주는 메소드
     *
     * @param point 지점을 넣는다.
     */
    private void drawPoint(SearchKeyword point) {
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        TMapPoint tMapPoint = new TMapPoint(point.getMapy(), point.getMapx());
        TMapMarkerItem marker = new TMapMarkerItem();
        marker.setTMapPoint(tMapPoint);
        marker.setName(point.getTitle());
        marker.setVisible(TMapMarkerItem.VISIBLE);
        marker.setIcon(icon);
        tMapView.addMarkerItem("point", marker);
    }


}
