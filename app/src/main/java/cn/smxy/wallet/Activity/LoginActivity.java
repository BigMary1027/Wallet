package cn.smxy.wallet.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hhmedic.android.sdk.okhttputils.okhttp.OkHttpUtils;
import com.hhmedic.android.sdk.okhttputils.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import cn.smxy.wallet.R;
import cn.smxy.wallet.databinding.ActivityLoginBinding;
import cn.smxy.wallet.fragment.MyWalletsFragment;
import okhttp3.Call;
import okhttp3.OkHttpClient;

public class LoginActivity extends Activity {
    private TextView tvForgotPas,tvGoRegister;
    private EditText tvMnemonic,tvPassword;
    private Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        setListeners();
    }

    private void initView() {
        tvMnemonic = findViewById(R.id.inputEmail);
        tvPassword = findViewById(R.id.inputPassword);
        tvForgotPas = findViewById(R.id.forgot_password);
        tvGoRegister = findViewById(R.id.textCreateNewAccount);
        btnSignIn = findViewById(R.id.buttonSignIn);
    }

    private void setListeners(){
        tvGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                checkLogin();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        tvForgotPas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,VerifyActivity.class);
                startActivity(intent);
            }
        });
    }
    private void checkLogin(){
        String url ="";
        String userEmail = tvMnemonic.getText().toString();
        String password = tvPassword.getText().toString();
        Map<String,String> map = new HashMap<>();
        map.put("useremail",userEmail);
        map.put("password",password);
        OkHttpUtils.post().url(url).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }
}