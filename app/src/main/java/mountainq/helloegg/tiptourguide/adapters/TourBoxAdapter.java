package mountainq.helloegg.tiptourguide.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mountainq.helloegg.tiptourguide.R;
import mountainq.helloegg.tiptourguide.data.StaticData;
import mountainq.helloegg.tiptourguide.data.TourBoxItem;
import mountainq.helloegg.tiptourguide.manager.DropManager;


/**
 * Created by dnay2 on 2016-11-18.
 */

public class TourBoxAdapter extends BaseAdapter {

    private StaticData mData = StaticData.getInstance();
    private ArrayList<TourBoxItem> items;
    private Context context;

    public TourBoxAdapter(ArrayList<TourBoxItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        if(items != null) return items.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        LinearLayout itembg;
        TextView numberText;
        TextView destinationText;
        TextView timeText;
        TextView distanceText;
        TextView costText;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null){
            v = View.inflate(context, R.layout.fragment_tourbox_item, null);
            holder = new ViewHolder();
            holder.itembg = (LinearLayout) v.findViewById(R.id.itembg);
            holder.numberText =(TextView) v.findViewById(R.id.numberText);
            holder.destinationText = (TextView) v.findViewById(R.id.destinationText);
            holder.timeText = (TextView) v.findViewById(R.id.timeText);
            holder.distanceText = (TextView) v.findViewById(R.id.distanceText);
            holder.costText = (TextView) v.findViewById(R.id.costText);
            v.setTag(holder);
        } else{
            holder = (ViewHolder) v.getTag();
        }
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mData.getHeight()/10);
        holder.itembg.setLayoutParams(llp);
        if(position%2 == 0) holder.itembg.setBackgroundColor(0xffeaeaea);
        else holder.itembg.setBackgroundColor(0xffffffff);

        TourBoxItem item = items.get(position);
        /**
         * holder로 꾸미기
         */
        initTextView(holder.numberText, holder.destinationText, holder.timeText, holder.distanceText, holder.costText);
        holder.numberText.setText(String.valueOf(position+1));
        holder.destinationText.setText(item.getDestination());
        holder.timeText.setText(DropManager.calTime(item.getDistance()));
        holder.distanceText.setText(DropManager.calDistance(item.getDistance()));
        holder.costText.setText(DropManager.calCost(item.getDistance()));

        return v;
    }

    private void initTextView(
            TextView number,
            TextView title,
            TextView time,
            TextView distance,
            TextView cost
    ){

        number.setLayoutParams(new LinearLayout.LayoutParams(mData.getWidth()/10, ViewGroup.LayoutParams.WRAP_CONTENT));
        title.setLayoutParams(new LinearLayout.LayoutParams(mData.getWidth()*3/10, ViewGroup.LayoutParams.WRAP_CONTENT));
        time.setLayoutParams(new LinearLayout.LayoutParams(mData.getWidth()*2/10, ViewGroup.LayoutParams.WRAP_CONTENT));
        distance.setLayoutParams(new LinearLayout.LayoutParams(mData.getWidth()*2/10, ViewGroup.LayoutParams.WRAP_CONTENT));
        cost.setLayoutParams(new LinearLayout.LayoutParams(mData.getWidth()*2/10, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
