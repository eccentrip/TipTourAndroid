package mountainq.helloegg.tiptourguide.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mountainq.helloegg.tiptourguide.R;
import mountainq.helloegg.tiptourguide.data.SearchKeyword;
import mountainq.helloegg.tiptourguide.data.StaticData;


/**
 * Created by dnay2 on 2016-11-18.
 */

public class TourSearchAdapter extends BaseAdapter/** implements Filterable*/ {

    private StaticData mData = StaticData.getInstance();
    private ArrayList<SearchKeyword> original;
    private ArrayList<SearchKeyword> items;
    private Context context;

    public TourSearchAdapter(ArrayList<SearchKeyword> items, Context context) {
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
        ImageView firstImgView;
        LinearLayout itembg;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder;
        if(v == null){
            v = View.inflate(context, R.layout.fragment_tourselection_item, null);
            holder = new ViewHolder();
            holder.nameText = (TextView) v.findViewById(R.id.itemText);
            holder.firstImgView = (ImageView) v.findViewById(R.id.itemImg);
            holder.itembg = (LinearLayout) v.findViewById(R.id.itembg);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mData.getHeight()/10);
        holder.itembg.setLayoutParams(llp);
        holder.itembg.setGravity(Gravity.CENTER);
        holder.itembg.setPadding(10,10,10,10);
        /**
         * 홀더의 내용으로 꾸미기
         */

        SearchKeyword item = items.get(position);
        holder.nameText.setText(item.getTitle());
        llp = new LinearLayout.LayoutParams(mData.getHeight()/13, mData.getHeight()/13);
        holder.firstImgView.setLayoutParams(llp);
        Picasso.with(context)
                .load(item.getFirstImgUrl())
                .placeholder(R.drawable.location_me)
                .error(R.mipmap.ic_launcher)
                .fit()
                .into(holder.firstImgView);

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
