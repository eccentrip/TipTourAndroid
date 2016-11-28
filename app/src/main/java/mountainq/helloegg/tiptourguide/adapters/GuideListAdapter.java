package mountainq.helloegg.tiptourguide.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mountainq.helloegg.tiptourguide.R;
import mountainq.helloegg.tiptourguide.data.Guide;
import mountainq.helloegg.tiptourguide.data.StaticData;

/**
 * Created by dnay2 on 2016-11-26.
 */

public class GuideListAdapter extends BaseAdapter {

    private StaticData mData = StaticData.getInstance();
    private ArrayList<Guide> original;
    private ArrayList<Guide> items;
    private Context context;

    public GuideListAdapter(ArrayList<Guide> items, Context context) {
        this.items = items;
        this.original = items;
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
        TextView nameText;
        TextView descriptionText;
        TextView scoreText;
        LinearLayout itembg;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;

        if(v == null){
            v = View.inflate(context, R.layout.activity_guidelist_item, null);
            holder = new ViewHolder();
            holder.nameText = (TextView) v.findViewById(R.id.name);
            holder.descriptionText = (TextView) v.findViewById(R.id.description);
            holder.scoreText = (TextView) v.findViewById(R.id.score);
            holder.itembg = (LinearLayout) v.findViewById(R.id.itembg);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mData.getHeight()/10);
        holder.itembg.setLayoutParams(llp);
        holder.itembg.setGravity(Gravity.CENTER);
        /**
         * 홀더의 내용으로 꾸미기
         */

        Guide item = items.get(position);
        holder.nameText.setText(item.getName());
        holder.descriptionText.setText(item.getDescription());
        holder.scoreText.setText(String.valueOf(item.getScore()));


        return v;
    }

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                FilterResults results = new FilterResults();
//                ArrayList<TourItem> FilteredArrList = new ArrayList<>();
//
//                if (items == null) {
//                    items = new ArrayList<>(original);
//                }
//                if (constraint == null || constraint.length() == 0) {
//                    results.count = original.size();
//                    results.values = original;
//                } else {
//                    constraint = constraint.toString().toLowerCase(Locale.KOREA);
//                    for (TourItem data : original) {
//                        String str = data.getTitle();
//                        if (str.toLowerCase().startsWith(constraint.toString()) //검색어로 시작
//                                || str.toLowerCase().equals(constraint.toString()) //검색어와 일치
//                                || str.toLowerCase().contains(constraint) //검색어를 포함
//                                ) {
//                            FilteredArrList.add(new TourItem(
//                                    data.getTitle(),
//                                    data.getAddr1()));
//                        }
//                    }
//                    results.count = FilteredArrList.size();
//                    results.values = FilteredArrList;
//                }
//                return results;
//            }
//
//            @SuppressWarnings("unchecked")
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//
//                items = (ArrayList<TourItem>) results.values;
//                notifyDataSetChanged();
//            }
//
//        };
//    }
}
