package cn.smxy.wallet.FragmentAdapter;

import android.graphics.Color;
import android.service.quickaccesswallet.WalletCard;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.PersonWallet;
import cn.smxy.wallet.fragment.HistoryFragment;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Viewholder> {
    private DecimalFormat format;
    private List<PersonWallet> personWalletList;

    public HistoryAdapter(List<PersonWallet> personWalletList) {
        this.personWalletList = personWalletList;
        format = new DecimalFormat("###,###,###,###.###");
    }

    @NonNull
    @Override
    public HistoryAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.Viewholder holder, int position) {
        PersonWallet personWallet = personWalletList.get(position);
        holder.name.setText(personWallet.getName());
        holder.walletCard.setText("Wallet ID -" + personWallet.getWalletCad());
        holder.money.setText("$" + format.format(personWallet.getMoney()));

        if (personWallet.getMoney()>0){
            holder.money.setTextColor(Color.parseColor("#12c737"));
        }else if (personWallet.getMoney()<0){
            holder.money.setTextColor(Color.parseColor("#fc0000"));
        }else {
            holder.money.setTextColor(Color.parseColor("#ffffff"));
        }

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(personWallet.getName(),"mipmap",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.itemImg);
    }

    @Override
    public int getItemCount() {
        return personWalletList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView itemImg;
        TextView name,walletCard,money;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.item_head);
            name = itemView.findViewById(R.id.item_name);
            walletCard = itemView.findViewById(R.id.item_wallet_id_card);
            money = itemView.findViewById(R.id.item_money);
        }
    }
}
