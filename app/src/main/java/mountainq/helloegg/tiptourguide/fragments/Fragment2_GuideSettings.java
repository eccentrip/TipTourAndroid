package mountainq.helloegg.tiptourguide.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import mountainq.helloegg.tiptourguide.ApplicationController;
import mountainq.helloegg.tiptourguide.R;
import mountainq.helloegg.tiptourguide.activities.FChildActivity;
import mountainq.helloegg.tiptourguide.data.Content;
import mountainq.helloegg.tiptourguide.interfaces.NetworkService;


/**
 * Created by dnay2 on 2016-11-17.
 */

public class Fragment2_GuideSettings extends FChildActivity {

    TextView guideSetText;
    Switch guideSetSwitch;
    ApplicationController app;
    NetworkService networkService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_guidesetting, container, false);
        init(v);
        return v;
    }

    private void init(View v){
        app = (ApplicationController) getActivity().getApplicationContext();
        networkService = app.getNetworkService();
        guideSetText = (TextView) v.findViewById(R.id.guideSetText);
        guideSetSwitch = (Switch) v.findViewById(R.id.guideSetSwitch);
        guideSetSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sendData(isChecked);
            }
        });
    }

    private void sendData(boolean isChecked){
        Content content = new Content();

        if(isChecked){

        } else {

        }
    }

}
