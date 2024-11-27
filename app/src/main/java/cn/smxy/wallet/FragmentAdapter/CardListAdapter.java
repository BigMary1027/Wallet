package cn.smxy.wallet.FragmentAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.CardList;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.MyViewHolder> {
    private Context context;
    private List<CardList> cardListData;

    public CardListAdapter(List<CardList> cardListData) {
        this.cardListData = cardListData;
    }



    @NonNull
    @Override
    public CardListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
        CardList card = cardListData.get(position);
//        Glide.with(context).load(card.getImg()).into(viewHolder.iconTitle);

        int drawableResourceId = viewHolder.itemView.getContext().getResources()
                .getIdentifier(card.getImg(),"mipmap",viewHolder.itemView.getContext().getPackageName());

        Glide.with(viewHolder.itemView.getContext())
                .load(drawableResourceId)
                .into(viewHolder.iconTitle);

        String intro = card.getCardNumber();
        int len = intro.length();
        String substr;
        substr = intro.substring(len-4);
        viewHolder.cardNum.setText("**** "+substr);
    }


    @Override
    public int getItemCount() {
        return cardListData.size();
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView == null){
//            convertView = LayoutInflater.from(context).inflate(R.layout.card_list,parent,false);
//            viewHolder = new ViewHolder(convertView);
//            convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        Card card = cardListData.get(position);
//        Glide.with(context).load(card.getImg()).into(viewHolder.iconTitle);
//        String intro = card.getCardNumber();
//        int len = intro.length();
//        String substr;
//        substr = intro.substring(len-4);
//        viewHolder.cardNum.setText(substr);
//
//        return convertView;
//    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView cardNum;
        ImageView iconTitle,iconCheck;


    public MyViewHolder(@NonNull View view) {
        super(view);
        cardNum = view.findViewById(R.id.card_num);
        iconTitle = view.findViewById(R.id.icon_title);
        iconCheck = view.findViewById(R.id.icon_check);
    }
}
}
