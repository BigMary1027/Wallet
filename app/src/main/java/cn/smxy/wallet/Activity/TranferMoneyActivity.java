package cn.smxy.wallet.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.hhmedic.android.sdk.okhttputils.okhttp.OkHttpUtils;
import com.hhmedic.android.sdk.okhttputils.okhttp.callback.StringCallback;

import java.util.List;

import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.CardList;
import cn.smxy.wallet.entity.CardListResponse;
import okhttp3.Call;

public class TranferMoneyActivity extends AppCompatActivity {
    private LinearLayout myCardList,yourNameList;
    private TextView myCardNum,myCardMoney,yourName,yourCardNum,myPayMoney;
    private ImageView yourHeadImg;
    private Button btnOk;
    private List<CardList> cardList;
    private CardList card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tranfer_money);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        getMyCardList();
        setOnclick();
    }
    private void initView(){
        myCardNum = findViewById(R.id.my_card_num);
        myCardMoney = findViewById(R.id.my_card_money);
        yourName = findViewById(R.id.you_name);
        yourCardNum = findViewById(R.id.you_card_num);
        yourHeadImg = findViewById(R.id.you_head_img);
        myCardList = findViewById(R.id.my_card_list);
        yourNameList = findViewById(R.id.yourNameList);
        myPayMoney = findViewById(R.id.pay_money);
        btnOk = findViewById(R.id.btn_ok);
    }
    private void setOnclick(){
        myCardList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseCard();
            }
        });
        yourNameList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFriendly();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tranferMoney();
            }
        });
    }
    private void tranferMoney(){
        String url = "http://192.168.158.1:8089/flowerServer/cardList/update";

    }
    private void getMyCardList(){
        String url = "http://192.168.158.1:8089/flowerServer/card/list/findAll";

        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                CardListResponse cardResponse = new Gson().fromJson(response, CardListResponse.class);
                if (cardResponse != null && cardResponse.getCode() == 2000){
                    cardList = cardResponse.getDataobject();
                }
            }
        });
    }
    private void chooseCard(){
        String[] s = new String[cardList.size()];
        Float[] m = new Float[cardList.size()];
        for (int i = 0;i < cardList.size(); i++){
            s[i] = cardList.get(i).getCardNumber();
            m[i] = cardList.get(i).getMoney();
        }
        // 计算合并后数组的长度
        int length1 = s.length;
        int length2 = m.length;
        int combinedLength = length1 + length2;

// 创建一个二维数组
        String[][] combinedArray = new String[2][combinedLength];

// 将第一个数组复制到二维数组的第一行
        System.arraycopy(s, 0, combinedArray[0], 0, length1);

// 将第二个数组复制到二维数组的第二行
        System.arraycopy(m, 0, combinedArray[1], 0, length2);

// 使用builder.setItems时，需要将二维数组转换为一维数组
        CharSequence[] items = new CharSequence[combinedLength];
        for (int i = 0; i < combinedLength; i++) {
            items[i] = combinedArray[i / length2][i % length2]; // 确保正确处理行和列索引
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(TranferMoneyActivity.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int len = s[which].length();
                String substr;
                substr = s[which].substring(len - 4);
                myCardNum.setText("**** " + substr);
                myCardMoney.setText("Current Balance $" + m[which]);
                card = cardList.get(which);
            }
        });
        builder.show();
    }
    private void chooseFriendly(){
        String[] s = new String[cardList.size()];
        String[] m = new String[cardList.size()];
        for (int i = 0;i < cardList.size(); i++){
            s[i] = cardList.get(i).getName();
            m[i] = cardList.get(i).getCardNumber();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(TranferMoneyActivity.this);
        builder.setItems(s, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                yourName.setText("**** " + s[which]);
                yourCardNum.setText("Wallet ID:" + m[which]);
                card = cardList.get(which);
            }
        });
        builder.show();
    }
}