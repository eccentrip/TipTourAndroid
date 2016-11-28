package mountainq.helloegg.tiptourguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import mountainq.helloegg.tiptourguide.activities.SActivity;
import mountainq.helloegg.tiptourguide.data.User;
import mountainq.helloegg.tiptourguide.interfaces.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dnay2 on 2016-11-26.
 */

public class GuideSettingActivity extends SActivity {

    TextView guideSetText;
    Switch guideSetSwitch;
    ApplicationController app;
    NetworkService networkService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_guidesetting);
        Log.d("Test", "fragment created");
        setToolbar(getIntent().getStringExtra("text"));
        init();
    }

    private void init(){
        app = (ApplicationController) getApplicationContext();
        networkService = app.getNetworkService();
        guideSetText = (TextView) findViewById(R.id.guideSetText);
        guideSetSwitch = (Switch) findViewById(R.id.guideSetSwitch);
        initGuiState();

    }

    private void initGuiState(){

        Call<User> userAllData = networkService.receiveUserAllDatas(app.user_id);
        userAllData.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User user = response.body();
                    if(user.getGui() == 1) guideSetSwitch.setChecked(true);
                    else guideSetSwitch.setChecked(false);

                    guideSetSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            sendData(isChecked);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void sendData(boolean isChecked){
        User user = new User();
        user.setId(app.user_id);

        if(isChecked){
            user.setGui(1);
        } else {
            user.setGui(0);
        }

        final Call<Integer> receive = networkService.newGui(user);
        receive.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    if(response.body() == 1)
                        Toast.makeText(GuideSettingActivity.this, "가이드가 설정 되었습니다. 이제 가이드 메시지를 받을 수 있습니다.", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(GuideSettingActivity.this, "가이드가 해제 되었습니다. 이제 가이드 메시지를 받을 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }
}
