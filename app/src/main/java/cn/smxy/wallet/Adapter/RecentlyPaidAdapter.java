package cn.smxy.wallet.Adapter;

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
import cn.smxy.wallet.entity.UserDemo;

public class RecentlyPaidAdapter extends RecyclerView.Adapter<RecentlyPaidAdapter.Viewholder> {
    private List<UserDemo> userDemoList;

    public RecentlyPaidAdapter(List<UserDemo> userDemoList) {
        this.userDemoList = userDemoList;
    }

    @NonNull
    @Override
    public RecentlyPaidAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_recently_paid,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyPaidAdapter.Viewholder holder, int position) {
        UserDemo user = userDemoList.get(position);
        holder.name.setText(user.getName());

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(user.getName(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.recentImg);
    }

    @Override
    public int getItemCount() {
        return userDemoList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView recentImg;
        TextView name;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            recentImg = itemView.findViewById(R.id.user_recently_paid_img);
            name = itemView.findViewById(R.id.user_recently_paid_name);
        }
    }
}
