package mountainq.helloegg.tiptourguide;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mountainq.helloegg.tiptourguide.activities.SActivity;
import mountainq.helloegg.tiptourguide.data.Content;
import mountainq.helloegg.tiptourguide.data.StaticData;
import mountainq.helloegg.tiptourguide.interfaces.NetworkService;
import mountainq.helloegg.tiptourguide.register.RegisterActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by sukyami on 16. 1. 12..
 */
public class LoginActivity extends SActivity {

    StaticData mData = StaticData.getInstance();
    public void btnHandler(View v) {
        switch (v.getId()) {
            case R.id.login_join_member_btn:
                sendData();
                break;
            case R.id.register:
                moveRegister();
                break;
        }
    }

    private EditText inputEmail;
    private Button btnLogin;
    private Button btnLinkfacebook;
    private Button btnLinkToRegisterScreen;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private TextView inputEmail_temp;
    private TextView inputPassword_temp;
    String emailaddress;
    String password;
    private NetworkService networkService;

    ApplicationController app;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        networkService = ApplicationController.getInstance().getNetworkService();

        app = (ApplicationController) getApplicationContext();

        inputEmail = (EditText) findViewById(R.id.email_input);
        inputPassword = (EditText) findViewById(R.id.password_input);
        btnLogin = (Button) findViewById(R.id.login_join_member_btn);
        btnLinkToRegisterScreen = (Button) findViewById(R.id.register);


    }


    /**
     * function to verify login details in mysql db
     */


    private void sendData() {

        emailaddress = inputEmail.getText().toString();
        password = inputPassword.getText().toString().trim();

        Content loggingin = new Content();
        Log.e("sangik", emailaddress + "  " + password);
        loggingin.setName(emailaddress);
        loggingin.setPassword(password);

        Call<Content> loginCall = networkService.newThumbnail(loggingin);
        loginCall.enqueue(new Callback<Content>() {
            @Override
            public void onResponse(Call<Content> call, Response<Content> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(LoginActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                    Content tempContent = response.body();

                    app.user_id = tempContent.user_idx;
                    mData.setUserId(tempContent.user_idx);

                    Log.i("MyTag", "유저아이디 제데로 넘어왓나요: " + app.user_id + "  ");
                    Intent i = new Intent(getApplicationContext(),
                            MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "로그인 실패.. 아이디와 비밀번호를 다시 확인해 주세요", Toast.LENGTH_SHORT).show();
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);

                }
            }

            @Override
            public void onFailure(Call<Content> call, Throwable t) {

            }
        });

    }

    private void moveRegister(){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }



}
