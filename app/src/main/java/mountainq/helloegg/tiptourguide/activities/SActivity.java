package mountainq.helloegg.tiptourguide.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import mountainq.helloegg.tiptourguide.R;

/**
 * Created by dnay2 on 2016-11-16.
 */

public abstract class SActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    public TextView toolbarText;
    protected ImageView backBtn;
    protected Typeface fontNanum;
    protected Context mContext;

    protected static final HashMap<String, String> menuHashMap = new HashMap<>();

    protected void onCreate(Bundle savedInstanceState, int layout) {
        super.onCreate(savedInstanceState);
        Log.d("test", "abstract class created");
        setContentView(layout);
        if(findViewById(R.id.toolbar) != null){
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("");
            setSupportActionBar(toolbar);
        }
    }

    protected void changeToolbarText(String text){
        ((TextView) findViewById(R.id.toolbarText)).setText(text);
    }



    protected void setToolbar(String text){
        backBtn = (ImageView) findViewById(R.id.backBtn);
        backBtn.setVisibility(View.VISIBLE);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbarText = (TextView) findViewById(R.id.toolbarText);
        toolbarText.setText(text);
    }



}