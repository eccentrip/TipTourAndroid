package mountainq.helloegg.tiptourguide.interfaces;


import java.util.ArrayList;

import mountainq.helloegg.tiptourguide.data.Content;
import mountainq.helloegg.tiptourguide.data.TourBoxItem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NetworkService {

    @Headers("Content-Type:application/json")
    @POST("/contents")
    Call<Content> newContent(@Body Content content);

    @Headers("Content-Type:application/json")
    @POST("/thumbnails")
    Call<Content> newThumbnail(@Body Content loggingin);

    @GET("/tour_info/{id}")
    Call<ArrayList<TourBoxItem>> newTourBoxList(@Path("id") int id);

    @Headers("Content-Type : application")
    @POST("/tour_info")
    Call<TourBoxItem> newTrip (@Body TourBoxItem tourBoxItem);

}
