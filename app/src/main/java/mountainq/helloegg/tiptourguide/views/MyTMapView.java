package mountainq.helloegg.tiptourguide.views;

import android.content.Context;
import android.util.AttributeSet;

import com.skp.Tmap.TMapView;

/**
 * Created by dnay2 on 2016-11-17.
 */

public class MyTMapView extends TMapView {

    public MyTMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTMapView(Context context) {
        super(context, null);
    }

    public MyTMapView(Context context, int tileType) {
        super(context, tileType);
    }

    public MyTMapView(Context context, double centerLon, double centerLat, int zoomLevel) {
        super(context, centerLon, centerLat, zoomLevel);
    }

    public MyTMapView(Context context, double centerLon, double centerLat, int zoomLevel, int tileType) {
        super(context, centerLon, centerLat, zoomLevel, tileType);
    }
}
