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

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.Viewholder> {
    private List<UserDemo> userDemoList;

    public ContactsAdapter(List<UserDemo> userDemoList) {
        this.userDemoList = userDemoList;
    }

    @NonNull
    @Override
    public ContactsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_contacts,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.Viewholder holder, int position) {
        UserDemo user = userDemoList.get(position);
        holder.name.setText(user.getName());
        holder.contacts.setText(user.getContacts());

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(user.getName(),"mipmap",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.contactImg);
    }

    @Override
    public int getItemCount() {
        return userDemoList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView contactImg;
        TextView name,contacts;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            contactImg = itemView.findViewById(R.id.contactImg);
            name = itemView.findViewById(R.id.contactName);
            contacts = itemView.findViewById(R.id.contacts_contacts);
        }
    }
}
