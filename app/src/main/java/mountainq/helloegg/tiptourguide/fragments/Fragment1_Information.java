package mountainq.helloegg.tiptourguide.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mountainq.helloegg.tiptourguide.R;
import mountainq.helloegg.tiptourguide.activities.FChildActivity;

/**
 * Created by dnay2 on 2016-11-17.
 */

public class Fragment1_Information extends FChildActivity {


    TextView[] informText = new TextView[3];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_information, container, false);
        init(v);
        setText();
        return v;
    }

    private void init(View v){
        informText[0] = (TextView) v.findViewById(R.id.inform1);
        informText[1] = (TextView) v.findViewById(R.id.inform2);
        informText[2] = (TextView) v.findViewById(R.id.inform3);


    }

    private void setText(){
        informText[0].setText("개발 정보");
        informText[1].setText("개발자\n동국대 10 임규산\nEmail : dnay2k@naver.com \n성균관대 15 강철민");
        informText[2].setText("버젼정보 " + Build.VERSION.RELEASE);
    }
}
