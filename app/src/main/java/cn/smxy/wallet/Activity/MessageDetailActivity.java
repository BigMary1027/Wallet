package cn.smxy.wallet.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.Messages;

public class MessageDetailActivity extends AppCompatActivity {
    private Messages messages;
    private TextView mesTitle,mesTime,mesAuthor,mesContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_message_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        messages = (Messages) intent.getSerializableExtra("messages");

        mesTitle = this.findViewById(R.id.mes_detail_title);
        mesTime = this.findViewById(R.id.mes_detail_time);
        mesAuthor = this.findViewById(R.id.mes_detail_author);
        mesContacts = this.findViewById(R.id.mes_detail_contacts);
    }

}