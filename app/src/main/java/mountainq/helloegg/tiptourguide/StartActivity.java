package mountainq.helloegg.tiptourguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mountainq.helloegg.tiptourguide.register.RegisterActivity;


public class StartActivity extends Activity {

    private Button btnLinkToLogin;
    private Button btnLinkToRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
