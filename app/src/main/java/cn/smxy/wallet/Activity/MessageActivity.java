package cn.smxy.wallet.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cn.smxy.wallet.Adapter.MessageAdapter;
import cn.smxy.wallet.FragmentAdapter.CardListAdapter;
import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.CardList;
import cn.smxy.wallet.entity.Messages;

public class MessageActivity extends AppCompatActivity {
    private RecyclerView messageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_message);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setMessage();
    }

    private void setMessage() {
        messageView = findViewById(R.id.messageView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        messageView.setLayoutManager(layoutManager);

        ArrayList<Messages> messagesArrayList = new ArrayList<>();
        messagesArrayList.add(new Messages("美国9月就业大超预期带动降息预期收敛--评美国9月","美国今年巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉八零八零零八零八","2024-10-05","BTX","王之凡"));
        messagesArrayList.add(new Messages("17岁女子退婚不退彩礼，男方举牌喊退钱……知情人：38万元彩礼已退回33万元","男方家属在讨要彩礼钱引发围观近日，山东济宁市梁山县一女子收38万彩礼后退婚不退钱，遭男方家庭多次声讨引发全城关注。","2024-10-09","ETH","王之凡"));
        messagesArrayList.add(new Messages("面对不法侵害，隐忍绝非唯一上策","近日，山东青岛崂山区一女子驾驶路虎车逆行插队，殴打对向正常行驶的男司","2024-10-08","ROX","王之凡"));
        messagesArrayList.add(new Messages("国庆档票房破16亿","据网络平台数据显示，2024年国庆档新片票房（含点映及预售）突破16亿！","2024-10-19","ROX","王之凡"));
        messagesArrayList.add(new Messages("国庆档票房破16亿","据网络平台数据显示，2024年国庆档新片票房（含点映及预售）突破16亿！","2024-09-08","ROX","王之凡"));
        messagesArrayList.add(new Messages("国庆档票房破16亿","据网络平台数据显示，2024年国庆档新片票房（含点映及预售）突破16亿！","2024-05-09","ROX","王之凡"));
        messageView.setAdapter(new MessageAdapter(messagesArrayList,this));
    }
}