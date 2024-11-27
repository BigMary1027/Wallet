package cn.smxy.wallet.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.Gson;
import com.hhmedic.android.sdk.okhttputils.okhttp.OkHttpUtils;
import com.hhmedic.android.sdk.okhttputils.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import cn.smxy.wallet.Adapter.TranscactionHistoryAdapter;
import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.TranHistory;
import cn.smxy.wallet.entity.TranHistoryResponse;
import okhttp3.Call;

public class ChartActivity extends AppCompatActivity {
    private BarChart barChart;
    private RecyclerView tranHistoryListView;
    private TextView totalMoney,cardNumber;
    private LinearLayout onCardNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        getDataFromNet();
    }
    private void initView(){
        totalMoney = findViewById(R.id.total_money);
        cardNumber = findViewById(R.id.card_number);
        onCardNumber = findViewById(R.id.onclick_card_number);
        // Assign variable
        barChart = findViewById(R.id.bar_chart);
        tranHistoryListView = findViewById(R.id.chart_card_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        tranHistoryListView.setLayoutManager(layoutManager);
    }
    private void getDataFromNet(){
        String url = "http://192.168.158.1:8089/flowerServer/card/findById";
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("TAG", "onError: "+ e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("TAG", "onResponse: "+response);
                TranHistoryResponse tranHistoryResponse = new Gson().fromJson(response,TranHistoryResponse.class);
                if (tranHistoryResponse != null && tranHistoryResponse.getCode() == 2000){
                    List<TranHistory> tranHistories = tranHistoryResponse.getDataobject();
                    ArrayList<BarEntry> barEntries = new ArrayList<>();
                    for (int  i=0; i<tranHistories.size(); i++){
//                        String x = tranHistories.get(i).getPay_address();
                        float y = tranHistories.get(i).getPay_money();
                        //Initialize bar chart entr
                        BarEntry barEntry = new BarEntry(i,y);
                        //Add values in array list
                        barEntries.add(barEntry);
                    }
                    // Initalize bar data set
                    BarDataSet barDataSet = new BarDataSet(barEntries,"Employees");
                    //Set color
                    barDataSet.setColors(Color.BLUE);
                    // Hie draw value
                    barDataSet.setDrawValues(true);
                    //text size
                    barDataSet.setValueTextSize(16f);
                    // Set bar data
                    barChart.setData(new BarData(barDataSet));
                    barChart.setDrawGridBackground(false);
                    //set animation
                    barChart.animateY(2000);
                    //Set description text and color
                    barChart.getDescription().setText("Employees Chart");
                    barChart.getDescription().setTextColor(Color.WHITE);

                    TranscactionHistoryAdapter adapter = new TranscactionHistoryAdapter(ChartActivity.this,tranHistories);
                    tranHistoryListView.setAdapter(adapter);
                }
            }
        });
    }

}