package cn.smxy.wallet.FragmentAdapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.majorik.sparklinelibrary.SparkLineLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;

import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.Domain;

public class CrtproAdapter extends RecyclerView.Adapter<CrtproAdapter.Viewholder> {
    ArrayList<Domain> domainList;
    DecimalFormat format;

    public CrtproAdapter(ArrayList<Domain> domainList) {
        this.domainList = domainList;
        format = new DecimalFormat("###,###,###,###.###");
    }

    @NonNull
    @Override
    public CrtproAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_crypto,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CrtproAdapter.Viewholder holder, int position) {
        Domain domain = domainList.get(position);
        holder.nameTxt.setText(domain.getName());
        holder.priceTxt.setText("$"+format.format(domain.getPrice()));
        holder.changePercentTxt.setText(domain.getChangePercent() + "%");
        holder.propertySizeTxt.setText(domain.getPropertySize() + domain.getSymbol());
        holder.propertyAmountTxt.setText("$" + format.format(domain.getPropertyAmount()));
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

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(domain.getName(),"mipmap",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.logo);
    }

    @Override
    public int getItemCount() {
        return domainList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView nameTxt,priceTxt,changePercentTxt,propertySizeTxt,propertyAmountTxt;
        ImageView logo;
        SparkLineLayout layoutChart;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.cryptoNameTxt);
            priceTxt = itemView.findViewById(R.id.cryptoPriceTxt);
            changePercentTxt = itemView.findViewById(R.id.changePercentTxt);
            propertySizeTxt = itemView.findViewById(R.id.propertySizeTxt);
            propertyAmountTxt = itemView.findViewById(R.id.propertyAmountTxt);
            logo = itemView.findViewById(R.id.logoImg);
            layoutChart = itemView.findViewById(R.id.sparkLineLayout);
        }
    }
}
