package cn.smxy.wallet.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

import cn.smxy.wallet.R;

public class LinkYourCardActivity extends AppCompatActivity {
    private LinearLayout chooseDate;
    private EditText date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_link_your_card);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        setDate();
    }
    private void initView(){
        date = findViewById(R.id.lyc_date_text);
        chooseDate = findViewById(R.id.lyc_date);
    }
    private void setDate(){
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get year.month.day
                Calendar calendar=Calendar.getInstance();
                int y = calendar.get(Calendar.YEAR);
                int m = calendar.get(Calendar.MONTH);
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                date.setText(y + "/" + m + "/" + d);
                DatePickerDialog datePickerDialog = new DatePickerDialog(LinkYourCardActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(year + "/" + month + "/" + dayOfMonth);
                    }
                }, y, m, d);
                //show date
                datePickerDialog.show();
            }
        });
    }
}