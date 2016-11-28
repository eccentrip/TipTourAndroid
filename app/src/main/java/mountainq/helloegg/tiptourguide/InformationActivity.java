package mountainq.helloegg.tiptourguide;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import mountainq.helloegg.tiptourguide.activities.SActivity;


/**
 * Created by dnay2 on 2016-11-26.
 */

public class InformationActivity extends SActivity {

    TextView[] informText = new TextView[3];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_information);
        Log.d("Test", "fragment created");
        init();
        setToolbar(getIntent().getStringExtra("text"));
        setText();
    }

    private void init(){
        informText[0] = (TextView) findViewById(R.id.inform1);
        informText[1] = (TextView) findViewById(R.id.inform2);
        informText[2] = (TextView) findViewById(R.id.inform3);


    }

    private void setText(){
        informText[0].setText("개발 정보");
        informText[1].setText("개발자\n동국대 10 임규산\nEmail : dnay2k@gmail.com \n성균관대 15 강철민\nEmail : kcholmin@gmail.com");
        informText[2].setText("이 어플은 안드로이드 OS " + Build.VERSION.RELEASE +"버젼부터 사용 가능합니다.");
    }
}
