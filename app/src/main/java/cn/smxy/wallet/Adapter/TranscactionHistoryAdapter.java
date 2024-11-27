package cn.smxy.wallet.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.smxy.wallet.Activity.TransactionHistory;
import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.TranHistory;

public class TranscactionHistoryAdapter extends RecyclerView.Adapter<TranscactionHistoryAdapter.MyViewHolder> {
    private Context context;
    private List<TranHistory> tranHistoryList;

    public TranscactionHistoryAdapter(Context context, List<TranHistory> tranHistoryList) {
        this.context = context;
        this.tranHistoryList = tranHistoryList;
    }

    @NonNull
    @Override
    public TranscactionHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transaction_history_list,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
        TranHistory tranHistory = tranHistoryList.get(position);
        viewHolder.historyListId.setText("#"+tranHistory.getId());
        viewHolder.payWhat.setText(tranHistory.getPay_what());
//        viewHolder.payAddress.setText(tranHistory.getPay_address());
        String address = tranHistory.getPay_address();
        int len = address.length();
        String a;
        if (len>=25){
            a = address.substring(0,25) + "...";
        }else {
            a = address;
        }
        viewHolder.payAddress.setText(a);
//        String money = String.format("%.2f",tranHistory.getPay_money());
        viewHolder.payMoney.setText("$" + tranHistory.getPay_money());

        //单击详情账单
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TransactionHistory.class);
                intent.putExtra("history",tranHistory);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (tranHistoryList == null){
            return 0;
        }else {
            return tranHistoryList.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView historyListId,payWhat,payAddress,payMoney;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            historyListId = itemView.findViewById(R.id.history_list_id);
            payWhat = itemView.findViewById(R.id.history_list_style);
            payAddress = itemView.findViewById(R.id.history_list_address);
            payMoney = itemView.findViewById(R.id.history_list_money);
        }
    }
}
