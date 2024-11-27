package cn.smxy.wallet.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.TranHistory;
import jnr.ffi.annotations.In;

public class TransactionHistory extends AppCompatActivity {
    private PieChart pieChart;
    private TranHistory tranHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.transaction_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        Chart();
    }
    private  void Chart(){
        //Initialize array list
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        // Use for loop
        for (int  i=0;i<20;i++){
            //Convert to float
            float value = (float) (i*10.0);
            //Initialize bar chart entr
            // Initakize pie chart entry
            PieEntry pieEntry = new PieEntry(i,value);
            //Add values in array list
            pieEntries.add(pieEntry);
        }

        //Initialize pie data set
        PieDataSet pieDataSet = new PieDataSet(pieEntries,"Student");
        //Set color
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        // Set bar data
        pieChart.setData(new PieData(pieDataSet));
        //set animation
        pieChart.animateXY(2000,2000);
        //Set description
        pieChart.getDescription().setEnabled(false);
    }
    private void initView(){
        pieChart = findViewById(R.id.pie_chart);

        Intent intent = getIntent();
        tranHistory = (TranHistory) intent.getSerializableExtra("history");
    }
}