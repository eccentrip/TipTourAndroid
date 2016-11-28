package mountainq.helloegg.tiptourguide;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mountainq.helloegg.tiptourguide.activities.SActivity;
import mountainq.helloegg.tiptourguide.adapters.TourBoxAdapter;
import mountainq.helloegg.tiptourguide.data.StaticData;
import mountainq.helloegg.tiptourguide.data.TourBoxItem;
import mountainq.helloegg.tiptourguide.interfaces.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dnay2 on 2016-11-26.
 */

public class TourBoxActivity extends SActivity {

    StaticData mData = StaticData.getInstance();

    ArrayList<TourBoxItem> items = new ArrayList<>();
    TourBoxAdapter adapter;
    ListView tourbox;

    NetworkService networkService;
    ApplicationController app;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_tourbox);
        Log.d("Test", "fragment created");
        app = (ApplicationController) getApplicationContext();
        networkService = app.getNetworkService();
        setToolbar(getIntent().getStringExtra("text"));
        tourbox = (ListView) findViewById(R.id.tourbox);
        adapter = new TourBoxAdapter(items, this);
        context = this;
        tourbox.setAdapter(adapter);
        tourbox.setOnItemClickListener(itemClickListener);
        initTextView(
                (TextView)findViewById(R.id.numberText),
                (TextView)findViewById(R.id.destinationText),
                (TextView)findViewById(R.id.timeText),
                (TextView)findViewById(R.id.distanceText),
                (TextView)findViewById(R.id.costText)
        );
        getTourBox();
    }

    private void initTextView(
            TextView number,
            TextView title,
            TextView time,
            TextView distance,
            TextView cost
    ){

        number.setLayoutParams(new LinearLayout.LayoutParams(mData.getWidth()/10, ViewGroup.LayoutParams.WRAP_CONTENT));
        title.setLayoutParams(new LinearLayout.LayoutParams(mData.getWidth()*3/10, ViewGroup.LayoutParams.WRAP_CONTENT));
        time.setLayoutParams(new LinearLayout.LayoutParams(mData.getWidth()*2/10, ViewGroup.LayoutParams.WRAP_CONTENT));
        distance.setLayoutParams(new LinearLayout.LayoutParams(mData.getWidth()*2/10, ViewGroup.LayoutParams.WRAP_CONTENT));
        cost.setLayoutParams(new LinearLayout.LayoutParams(mData.getWidth()*2/10, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private void getTourBox(){
        Call<ArrayList<TourBoxItem>> newTouBoxList = networkService.newTourBoxList(app.user_id);
        newTouBoxList.enqueue(new Callback<ArrayList<TourBoxItem>>() {
            @Override
            public void onResponse(Call<ArrayList<TourBoxItem>> call, Response<ArrayList<TourBoxItem>> response) {
                if(response.isSuccessful()){
                    items = response.body();
                    Log.d("Test", response.body().toString());
                    adapter = new TourBoxAdapter(items, context);
                    tourbox.setAdapter(adapter);
                    Log.d("Test", "adapter is refreshed");
                    Toast.makeText(context, "data is received", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "failed  " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TourBoxItem>> call, Throwable t) {

            }
        });
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final TourBoxItem item = items.get(position);
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle("알림")
                    .setMessage("이 내용을 삭제하시겠습니까?")
                    .setMessage(item.toString())
                    .setPositiveButton("삭제하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DeleteTrip(item);
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("그만두기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
            dialog.show();
        }
    };

    private void DeleteTrip(TourBoxItem item){
        NetworkService networkService = app.getNetworkService();
        TourBoxItem deleteItem = new TourBoxItem(item);

    }


}
