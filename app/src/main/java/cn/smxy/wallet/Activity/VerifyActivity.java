package cn.smxy.wallet.Activity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import cn.smxy.wallet.R;
import cn.smxy.wallet.utils.ImageAsyncTask;

public class VerifyActivity extends AppCompatActivity{
    public static final int MSG_START_TIME = 0;
    private static final int MSG_OK = 1;
    private int timeCount = 10;
    private static Handler mHandler;
    private Button btnOk,btnSenVerifyCode;
    private EditText phone,etCode;
    private ImageView iv;
    private String verifyCode = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        btnOk = findViewById(R.id.ver_btnOk);
        btnSenVerifyCode = findViewById(R.id.btn_send_verify_code);
        etCode = findViewById(R.id.btn_input_verify_code);
        iv = findViewById(R.id.ver_img);
        //
        HandlerThread ht = new HandlerThread("mainHandler");
        ht.start();
        mHandler = new MyMainHandler(new WeakReference<>(getWindow().getDecorView()));
        onClick();
    }


    private void onClick() {
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = etCode.getText().toString().trim();
                if (!TextUtils.isEmpty(str) && str.equalsIgnoreCase(verifyCode)) {
                    mHandler.removeMessages(MSG_START_TIME);
                    Toast.makeText(VerifyActivity.this,"验证码输入成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(VerifyActivity.this,"验证码输入失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSenVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.sendEmptyMessage(MSG_START_TIME);
                timeCount = 30;
                AsyncTask<String,Integer, Map<String, Object>> execute = new ImageAsyncTask().
                        execute("");
                try {
                    Map<String,Object> map = execute.get();
                    verifyCode = (String)map.get("code");
                    Bitmap bitmap = (Bitmap) map.get("bitmap");
                    if (bitmap!=null){
                        iv.setImageBitmap(bitmap);
                    }
                }catch (ExecutionException e){
                    e.printStackTrace();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
    }

    class MyMainHandler extends Handler {
        public MyMainHandler(WeakReference<View> v){
            View view = v.get();
        }
        @Override
        public void handleMessage(@NonNull Message msg){
            switch (msg.what){
                case MSG_START_TIME:
                    btnSenVerifyCode.setText("剩余时间 ("+timeCount--+"秒)");
                    mHandler.sendEmptyMessageDelayed(MSG_START_TIME,1000*1);
                    if (timeCount==0){
                        mHandler.removeMessages(MSG_START_TIME);
                        btnSenVerifyCode.setText("请重新获取验证码");
                    }
                    break;
                case MSG_OK:
                    break;
            }
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}