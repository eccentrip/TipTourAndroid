package mountainq.helloegg.tiptourguide.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mountainq.helloegg.tiptourguide.ApplicationController;
import mountainq.helloegg.tiptourguide.R;
import mountainq.helloegg.tiptourguide.activities.FChildActivity;
import mountainq.helloegg.tiptourguide.adapters.TourBoxAdapter;
import mountainq.helloegg.tiptourguide.data.StaticData;
import mountainq.helloegg.tiptourguide.data.TourBoxItem;
import mountainq.helloegg.tiptourguide.interfaces.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dnay2 on 2016-11-17.
 */

public class Fragment4_TourBox extends FChildActivity {

    StaticData mData = StaticData.getInstance();

    ArrayList<TourBoxItem> items = new ArrayList<>();
    TourBoxAdapter adapter;
    ListView tourbox;

    NetworkService networkService;
    ApplicationController app;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (ApplicationController) getActivity().getApplicationContext();
        networkService = app.getNetworkService();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tourbox, container, false);
        tourbox = (ListView) v.findViewById(R.id.tourbox);
        adapter = new TourBoxAdapter(items, getActivity());
        tourbox.setAdapter(adapter);
        tourbox.setOnItemClickListener(itemClickListener);
        getTourBox();
        return v;
    }

    private void getTourBox(){

        Call<ArrayList<TourBoxItem>> newTouBoxList = networkService.newTourBoxList(7);

        newTouBoxList.enqueue(new Callback<ArrayList<TourBoxItem>>() {
            @Override
            public void onResponse(Call<ArrayList<TourBoxItem>> call, Response<ArrayList<TourBoxItem>> response) {
                if(response.isSuccessful()){
                    items = response.body();
                    Log.d("Test", response.body().toString());
                    adapter = new TourBoxAdapter(items, getActivity());
                    tourbox.setAdapter(adapter);
                    Log.d("Test", "adapter is refreshed");
                    Toast.makeText(getActivity(), "data is received", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "failed  " + response.code(), Toast.LENGTH_SHORT).show();
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
            AlertDialog dialog = new AlertDialog.Builder(getActivity())
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
