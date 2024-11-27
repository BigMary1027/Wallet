package cn.smxy.wallet.FragmentAdapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.AllCard;

public class MyWalletAdapter extends RecyclerView.Adapter<MyWalletAdapter.Viewholder> {
    private List<AllCard> allCardList;
    private DecimalFormat format;

    public MyWalletAdapter(List<AllCard> allCardList) {
        this.allCardList = allCardList;
        format = new DecimalFormat("###,###,###,###.###");
    }

    @NonNull
    @Override
    public MyWalletAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_wallets_cards,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyWalletAdapter.Viewholder holder, int position) {
        AllCard allCard = allCardList.get(position);
        holder.totalMoney.setText("$"+format.format(allCard.getTotalMoney()));
        String intro = allCard.getCardNum();
        int len = intro.length();
        String substr;
        substr = intro.substring(len-4);
        holder.cardNum.setText("**** "+substr);

        if (allCard.getTotalMoney()>200){
            holder.cardBac.setBackgroundResource(R.drawable.background_round);
        }else if (allCard.getTotalMoney()<200){
            holder.cardBac.setBackgroundResource(R.drawable.background_round_three);
        }

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(allCard.getIconImg(),"mipmap",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.iconImg);
    }

    @Override
    public int getItemCount() {
        return allCardList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ConstraintLayout cardBac;
        ImageButton iconImg;
        TextView cardNum,totalMoney;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            cardBac = itemView.findViewById(R.id.mainCardLayout);
            iconImg = itemView.findViewById(R.id.iconImg);
            cardNum = itemView.findViewById(R.id.cardNum);
            totalMoney = itemView.findViewById(R.id.totalMoney);
        }
    }
}
