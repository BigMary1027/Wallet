package cn.smxy.wallet.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.hhmedic.android.sdk.okhttputils.okhttp.OkHttpUtils;
import com.hhmedic.android.sdk.okhttputils.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import cn.smxy.wallet.FragmentAdapter.CardListAdapter;
import cn.smxy.wallet.FragmentAdapter.HistoryAdapter;
import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.CardList;
import cn.smxy.wallet.entity.CardListResponse;
import cn.smxy.wallet.entity.PersonWallet;
import okhttp3.Call;

public class allCardActivity extends AppCompatActivity {
    private LinearLayout iconDown,iconList,iconUp;
    private ImageView bottom,up;
    private RecyclerView cardListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_card);

        initView();
        setOnclick();
        getDataFromNet();
    }
    private void initView(){
        iconDown = findViewById(R.id.icon_down);
        iconList = findViewById(R.id.icon_list);
        bottom = findViewById(R.id.bottom);
        iconUp = findViewById(R.id.icon_up);
        iconUp.setVisibility(View.GONE);
        //iconDown.bringToFront();
        cardListView = findViewById(R.id.card_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        cardListView.setLayoutManager(layoutManager);
    }
    private void getDataFromNet(){
        ArrayList<CardList> cardLists = new ArrayList<>();
        cardLists.add(new CardList("12543757","BTX"));
        cardLists.add(new CardList("12586347","ETH"));
        cardLists.add(new CardList("12574375","ROX"));
        cardLists.add(new CardList("45867254","ROX"));
        cardListView.setAdapter(new CardListAdapter(cardLists));
    }
    private void setOnclick(){
        iconDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iconList.setVisibility(View.GONE);
                iconUp.setVisibility(View.VISIBLE);
                iconDown.setVisibility(View.GONE);
            }
        });
        iconUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iconList.setVisibility(View.VISIBLE);
                iconUp.setVisibility(View.GONE);
                iconDown.setVisibility(View.VISIBLE);
            }
        });
    }
}