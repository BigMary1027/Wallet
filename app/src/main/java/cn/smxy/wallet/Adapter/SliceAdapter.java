package cn.smxy.wallet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.Card;
import cn.smxy.wallet.entity.CardList;

public class SliceAdapter extends RecyclerView.Adapter<SliceAdapter.MyViewHolder> {
    private Context context;
    private List<Card> cardList;

    public SliceAdapter(Context context, List<Card> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public SliceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slice_my_card,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.total.setText("$"+card.getTotal());
        String intro = card.getNum();
        int len = intro.length();
        String substr;
        substr = intro.substring(len-4);
        holder.num.setText("**** "+substr);
    }

    @Override
    public int getItemCount() {
        if (cardList == null){
            return 0;
        }else {
            return cardList.size();
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView total,num;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            total = itemView.findViewById(R.id.slice_card_total_money);
            num = itemView.findViewById(R.id.slice_card_num);
        }
    }
}
