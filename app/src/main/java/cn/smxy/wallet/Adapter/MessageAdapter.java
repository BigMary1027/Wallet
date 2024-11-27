package cn.smxy.wallet.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.smxy.wallet.Activity.MessageDetailActivity;
import cn.smxy.wallet.Activity.TransactionHistory;
import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.Messages;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.Viewholder> {
    private List<Messages> messagesList;
    private Context context;

    public MessageAdapter(List<Messages> messagesList, Context context) {
        this.messagesList = messagesList;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.Viewholder holder, int position) {
        Messages messages = messagesList.get(position);
        String t = messages.getTitle();
        int len = t.length();
        String a;
        if (len>=21){
            a = t.substring(0,21) + "...";
        }else {
            a = t;
        }
        holder.title.setText(a);
        holder.time.setText(messages.getTime());

//        int drawableResourceId = holder.itemView.getContext().getResources()
//                .getIdentifier(messages.getImg(),"drawable",holder.itemView.getContext().getPackageName());
//
//        Glide.with(holder.itemView.getContext())
//                .load(drawableResourceId)
//                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageDetailActivity.class);
                intent.putExtra("messages",messages);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView title,time;
//        ImageView img;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.message_time);
            title = itemView.findViewById(R.id.message_title);
//            img = itemView.findViewById(R.id.message_img);
        }
    }
}
