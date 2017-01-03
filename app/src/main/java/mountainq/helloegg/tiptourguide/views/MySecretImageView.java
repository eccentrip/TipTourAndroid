package mountainq.helloegg.tiptourguide.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by dnay2 on 2016-11-29.
 */

public class MySecretImageView extends ImageView {

    long pressedtime=0;

    interface DoubleTapListener{
        void onDoubleClicked();
    }
    private DoubleTapListener listener = null;

    public MySecretImageView(Context context) {
        super(context);
    }

    public MySecretImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySecretImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(pressedtime+2000 > System.currentTimeMillis() && listener != null ){
                    listener.onDoubleClicked();
                } else{
                    pressedtime = System.currentTimeMillis();
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
