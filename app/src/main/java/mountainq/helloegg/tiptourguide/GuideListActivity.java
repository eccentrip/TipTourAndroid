package mountainq.helloegg.tiptourguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mountainq.helloegg.tiptourguide.activities.SActivity;
import mountainq.helloegg.tiptourguide.adapters.GuideListAdapter;
import mountainq.helloegg.tiptourguide.data.BooleanData;
import mountainq.helloegg.tiptourguide.data.Guide;
import mountainq.helloegg.tiptourguide.data.PushDatas;
import mountainq.helloegg.tiptourguide.data.StaticData;
import mountainq.helloegg.tiptourguide.interfaces.NetworkService;
import mountainq.helloegg.tiptourguide.manager.LOG;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dnay2 on 2016-11-26.
 */

public class GuideListActivity extends SActivity {

    private StaticData mData = StaticData.getInstance();
    ApplicationController app;
    NetworkService networkService;


    ArrayList<Guide> items = new ArrayList<>();
    GuideListAdapter guideListAdapter = null;

    ListView guideListView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_guidelist);
        mContext = this;
        guideListView = (ListView) findViewById(R.id.guideListView);
        app = (ApplicationController) getApplicationContext();
        networkService = app.getNetworkService();

        requestGuideList();

    }

    private void requestGuideList() {

        Call<ArrayList<Guide>> allGuides = networkService.getGuideList(1);

        allGuides.enqueue(new Callback<ArrayList<Guide>>() {
            @Override
            public void onResponse(Call<ArrayList<Guide>> call, Response<ArrayList<Guide>> response) {
                if (response.isSuccessful()) {
                    items = response.body();
                    guideListAdapter = new GuideListAdapter(items, mContext);
                    guideListView.setAdapter(guideListAdapter);
                    guideListView.setOnItemClickListener(guideClickListener);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Guide>> call, Throwable t) {

            }
        });
    }

    private void requestPushToGuide(final Guide guide) {
        //푸시요청
        PushDatas pushDatas = new PushDatas(mData.getTempPushDatas());
        pushDatas.setId(guide.getId());

        Call<BooleanData> isSuccess = networkService.pushToGuide(pushDatas);
        isSuccess.enqueue(new Callback<BooleanData>() {
            @Override
            public void onResponse(Call<BooleanData> call, Response<BooleanData> response) {
                LOG.ERROR(response.body()+"");
                LOG.ERROR("pushToTourist error : " + response.body() +"\nToken : " + app.getToken());

                Toast.makeText(mContext, guide.getName() + "님에게 가이드 요청을 보냈습니다. 잠시만 기다려주세요", Toast.LENGTH_SHORT).show();
                finish();

            }

            @Override
            public void onFailure(Call<BooleanData> call, Throwable t) {
                LOG.ERROR("t : " + t.getMessage());
                Toast.makeText(mContext, "실패했습니다. 다시한번 시도해주세요", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private AdapterView.OnItemClickListener guideClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Guide item = items.get(position);
            requestPushToGuide(item);
        }
    };


}
