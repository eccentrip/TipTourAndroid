package mountainq.helloegg.tiptourguide.interfaces;


import java.util.Map;

import mountainq.helloegg.tiptourguide.data.StaticData;
import mountainq.helloegg.tiptourguide.data.TourItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by dnay2 on 2016-11-19.
 */

public interface TourAPIService {

    @GET("{country}/searchKeyword")
    Call<TourItem> searchKeyword(@Path("country") String country, @QueryMap Map<String, String> options);

    @GET("{country}/locationBasedList?ServiceKey="+ StaticData.SERVICE_KEY+"&mapX={mapX}&mapY={mapY}&radius=300&MobileOS=AND&MobileApp={appname}")
    Call<String> locationBasedList(@Path("appname") String appname, @QueryMap Map<String, Double> options);

}
