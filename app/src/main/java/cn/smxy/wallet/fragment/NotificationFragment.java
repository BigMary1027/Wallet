package cn.smxy.wallet.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.smxy.wallet.Activity.MessageActivity;
import cn.smxy.wallet.Activity.ProfileActivity;
import cn.smxy.wallet.Activity.TranferMoneyActivity;
import cn.smxy.wallet.Activity.qRCodeActivity;
import cn.smxy.wallet.R;

public class NotificationFragment extends Fragment {
    private View view;
    private ImageView noticeHead;
    private TextView noticeName;
    private AppCompatButton btnSendMoney,btnRequest;
    private LinearLayout btnMessage;
    public NotificationFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notification, container, false);
        initView();
        setOnclick();
        return view;
    }

    private void setOnclick() {
        noticeHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        noticeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        btnSendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TranferMoneyActivity.class);
                startActivity(intent);
            }
        });
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), qRCodeActivity.class);
                startActivity(intent);
            }
        });
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MessageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        noticeHead = view.findViewById(R.id.user_recently_paid_img);
        noticeName = view.findViewById(R.id.noti_name);
        btnSendMoney = view.findViewById(R.id.btn_send_money);
        btnRequest = view.findViewById(R.id.btn_request);
        btnMessage = view.findViewById(R.id.notic_message);
    }
}