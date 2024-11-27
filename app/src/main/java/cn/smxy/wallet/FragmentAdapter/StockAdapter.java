package cn.smxy.wallet.FragmentAdapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.majorik.sparklinelibrary.SparkLineLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;

import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.Domain;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.Viewholder> {
    ArrayList<Domain> domainList;
    DecimalFormat format;

    public StockAdapter(ArrayList<Domain> domainList) {
        this.domainList = domainList;
        format = new DecimalFormat("###,###,###,###.###");
    }

    @NonNull
    @Override
    public StockAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_stock,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StockAdapter.Viewholder holder, int position) {
        Domain domain = domainList.get(position);
        holder.nameTxt.setText(domain.getName());
        holder.priceTxt.setText("$"+format.format(domain.getPrice()));
        holder.changePercentTxt.setText(domain.getChangePercent() + "%");
        holder.layoutChart.setData(domain.getLineData());

        if (domain.getChangePercent()>0){
            holder.changePercentTxt.setTextColor(Color.parseColor("#12c737"));
            holder.layoutChart.setSparkLineColor(Color.parseColor("#12c737"));
        }else if (domain.getChangePercent()<0){
            holder.changePercentTxt.setTextColor(Color.parseColor("#fc0000"));
            holder.layoutChart.setSparkLineColor(Color.parseColor("#fc0000"));
        }else {
            holder.changePercentTxt.setTextColor(Color.parseColor("#ffffff"));
            holder.layoutChart.setSparkLineColor(Color.parseColor("#ffffff"));
        }

        if (position == 0){
            holder.mainLayout.setBackgroundResource(R.drawable.purple_bg);
            holder.nameTxt.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.white));
            holder.priceTxt.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.white));
            holder.changePercentTxt.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.white));
        }

        String picName = "";
        switch (domain.getName()){
            case "NASDAQ100":{
                picName = "stock_1";
                break;
            }
            case "S&P 500":{
                picName = "stock_2";
                break;
            }
            case "Dow Jones":{
                picName = "stock_1";
                break;
            }
        }

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(picName,"mipmap",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.logo);
    }

    @Override
    public int getItemCount() {
        return domainList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView nameTxt,priceTxt,changePercentTxt;
        ImageView logo;
        SparkLineLayout layoutChart;
        ConstraintLayout mainLayout;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.cryptoNameTxt);
            priceTxt = itemView.findViewById(R.id.cryptoPriceTxt);
            changePercentTxt = itemView.findViewById(R.id.changePercentTxt);
            logo = itemView.findViewById(R.id.logoImg);
            layoutChart = itemView.findViewById(R.id.sparkLineLayout);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
