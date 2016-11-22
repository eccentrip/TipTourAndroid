package mountainq.helloegg.tiptourguide.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mountainq.helloegg.tiptourguide.ApplicationController;
import mountainq.helloegg.tiptourguide.LoginActivity;
import mountainq.helloegg.tiptourguide.R;
import mountainq.helloegg.tiptourguide.data.Content;
import mountainq.helloegg.tiptourguide.interfaces.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnRegister;
    private EditText email_input;
    private EditText password_input;
    private EditText password_confirm;
    private NetworkService networkService;
    private ApplicationController app;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        networkService = ApplicationController.getInstance().getNetworkService();
        app = ApplicationController.getInstance();
        email_input = (EditText) findViewById(R.id.email_register);
        password_input = (EditText) findViewById(R.id.password_register);
        btnRegister = (Button) findViewById(R.id.login_join_member_btn);
        password_confirm= (EditText) findViewById(R.id.password_confirm);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_register);


        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                String name = email_input.getText().toString().trim();
                String password = password_input.getText().toString().trim();
                String password_check = password_confirm.getText().toString().trim();



               if (!name.isEmpty() && !password.isEmpty() && !password_check.isEmpty()) {

                   if ((password).equals(password_check)) {

//                       Intent intent = new Intent(getApplicationContext(), NicknameActivity.class);
//                       intent.putExtra("userid",user_id_email);
//                       intent.putExtra("userpassword", user_password);
//                       Log.e("상익", user_id_email + "   " + user_password);
//                       startActivity(intent);
                       networkService = ApplicationController.getInstance().getNetworkService();
                       Content content = new Content();
                       content.name = name;
                       content.password = password;
                       content.setToken(app.getToken());
                       content.setDeviceid(app.getDeviceid());
                       Call<Content> registerCall = networkService.newContent(content);
                       registerCall.enqueue(new Callback<Content>() {
                           @Override
                           public void onResponse(Call<Content> call, Response<Content> response) {
                               if (response.isSuccessful()) {
                                   Toast.makeText(RegisterActivity.this, "회원가입이 완료되었습니다! 다시 로그인 해주세요!", Toast.LENGTH_SHORT).show();


                                   Log.i("MyTag", "썸네일 제목 : ");
                               } else {
                                   int statusCode = response.code();
                                   Log.i("MyTag", "응답코드 : " + statusCode);
                               }
                           }

                           @Override
                           public void onFailure(Call<Content> call, Throwable t) {

                           }
                       });
                       Intent i = new Intent(getApplicationContext(),
                               LoginActivity.class);
                       startActivity(i);
                       finish();

                   }
                   else {

                       Toast.makeText(getApplicationContext(),
                               "비밀번호가 일치하지 않습니다! 다시 입력해 주세요!", Toast.LENGTH_LONG)
                               .show();
                       return ;
                   }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                   return ;
                }





            }
        });

        // Link to Login Screen


    }
}



