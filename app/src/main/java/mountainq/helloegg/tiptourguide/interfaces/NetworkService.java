package mountainq.helloegg.tiptourguide.interfaces;


import java.util.ArrayList;

import mountainq.helloegg.tiptourguide.data.BooleanData;
import mountainq.helloegg.tiptourguide.data.Guide;
import mountainq.helloegg.tiptourguide.data.PushDatas;
import mountainq.helloegg.tiptourguide.data.PushGuideToTourist;
import mountainq.helloegg.tiptourguide.data.TourBoxItem;
import mountainq.helloegg.tiptourguide.data.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NetworkService {

    /**
     * 회원가입
     * @param content 포스트 형태로 회원정보를 보낸다.
     * @return
     */
    @Headers("Content-Type:application/json")
    @POST("/contents")
    Call<User> newContent(@Body User content);

    /**
     * 로그인 요청
     * @param loggingin 로그인 정보를 포스트 형태로 보낸다.
     * @return
     */
    @Headers("Content-Type:application/json")
    @POST("/login")
    Call<User> newThumbnail(@Body User loggingin);

    /**
     * 가이드로 변경 요청
     * @param chageGui
     * @return
     */
    @POST("/update_profile/gui")
    Call<Integer> newGui(@Body User chageGui);


    /**
     * 유저의 정보를 모두 가져옴
     * @param id_idx application controller에 저장되어 있는 아이디 값을 넘겨서 받아옴
     * @return
     */
    @GET("/user_all_data/{id}")
    Call<User> receiveUserAllDatas(@Path("id") int id_idx);

    /**
     * 유저의 투어박스 리스트를 가져옴
      * @param id_idx 유저 PK 값을 전달
     * @return ArrayList 형태로 받아온다.
     */
    @GET("/tour_info/{id}")
    Call<ArrayList<TourBoxItem>> newTourBoxList(@Path("id") int id_idx);

    /**
     * 가이드 리스트를 가져옴
     * @param gui gui값이 1인 사람들을 모두가져옴
     * @return
     */
    @GET("/all_guides/{gui}")
    Call<ArrayList<Guide>> getGuideList(@Path("gui") int gui);

    /**
     *  투어박스에 여행 저장 이건 아마도 클라이언트에서 바로는 안씀
     * @param tourBoxItem
     * @return
     */
    @Headers("Content-Type : application")
    @POST("/tour_info")
    Call<TourBoxItem> newTrip (@Body TourBoxItem tourBoxItem);

    //지정된 가이드에게 푸시요청

    /**
     * 지정된 가이드에게 가이드 제안 푸시를 요청
     * @param pushToGuide 투어 정보 보냄
     * @return
     */
    @POST("/push_guide/toguide")
    Call<BooleanData> pushToGuide(@Body PushDatas pushToGuide);

    /**
     * 가이드에게 받는 푸시 데이터
     * @param idx idx값
     * @return
     */
    @GET("/reserve_tour/{idx}")
    Call<PushDatas> getGuidePushData(@Path("idx") int idx);

    /**
     * 가이드의 요청 수락/거절 내용을 푸시에 담아서 보내는 것!
     * @param pushGuideToTourist 푸시 직접 넣는 정보
     * @return
     */
    @POST("/push_guide/tourRespond")
    Call<BooleanData> pushToTourist(@Body PushGuideToTourist pushGuideToTourist);




}
