package cn.smxy.wallet.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hhmedic.android.sdk.okhttputils.okhttp.OkHttpUtils;
import com.hhmedic.android.sdk.okhttputils.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.MnemonicUtils;
import org.web3j.crypto.WalletUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.smxy.wallet.R;
import okhttp3.Call;


public class RegisterActivity extends AppCompatActivity {
    private EditText etName,etPassword,etConfigPass;
    private TextView tvSignIn;
    private Button btnSignUp;
    private static final String TAG = "RegisterActivity";
    private static final String REGISTER_URL = "http://192.168.158.1:8089/flowerServer/wallet/user/add"; // 替换为你的API基础URL
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        setListeners();
    }

    private void initView() {
        etName = findViewById(R.id.re_inputName);
        etPassword = findViewById(R.id.re_inputPassword);
        etConfigPass = findViewById(R.id.re_inputConfirmPassword);
        tvSignIn = findViewById(R.id.textSignIn);
        btnSignUp = findViewById(R.id.buttonSignUp);
    }

    private void setListeners(){
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRegister();
            }
        });
    }
    private void checkRegister(){
        String username = etName.getText().toString();
        String password = etPassword.getText().toString();
        if (etPassword.getText().toString().equals(etConfigPass.getText().toString())){
            new Thread(() -> {
                try {
                    // 处理注册成功的逻辑
                    String mnemonic = generateMnemonic();
                    //Log.d("Mnemonic", "Generated mnemonic: " + mnemonic);
                    //创建钱包
                    Pair<String,String> wallet = createWalletFromMnemonic(mnemonic);
                    // 检查钱包是否成功创建，并输出钱包地址和私钥
                    if (wallet != null) {
                        String walletAddress = wallet.getFirst();
                        String privateKey = wallet.getSecond();
                        // 存储助记词
                        OkHttpUtils.post().url(REGISTER_URL)
                                .addParams("username",username)
                                .addParams("password",password)
                                .addParams("mnemonic",mnemonic)
                                .addParams("address",walletAddress)
                                .addParams("keys",privateKey)
                                .build().execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        Log.d(TAG, "Generated mnemonic: " + mnemonic); // 打印助记词到日志
                                        runOnUiThread(() -> {
                                            Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                            //创建提醒对话框
                                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                            builder.setTitle("尊敬的用户");
                                            builder.setMessage("请记住和保存好您的助记词："+mnemonic);
                                            //设置对话框肯定按钮文本及其监听器
                                            builder.setPositiveButton("OK",(dialog,which)->{
                                                // 跳转到登录页面
                                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                            });
                                            //根据创造器构建提醒对话框
                                            AlertDialog dialog = builder.create();
                                            dialog.show();

                                        });
                                    }
                                });
                    } else {
                        System.out.println("Failed to create wallet.");
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Registration error:", e);
                    runOnUiThread(() -> {
                        Toast.makeText(RegisterActivity.this, "Registration error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }
            }).start();
        }else {
            Toast.makeText(this,"两次密码不一样",Toast.LENGTH_SHORT).show();
        }
    }

    private String generateMnemonic() {
        String jsonString = loadJSONFromAsset("eCNYABI.json");

        if (jsonString == null || jsonString.isEmpty()) {
            System.out.println("JSON string is empty or null.");
            return "";
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray functions = jsonObject.getJSONArray("functions");

            List<String> addressInputNames = new ArrayList<>();
            for (int i = 0; i < functions.length(); i++) {
                JSONObject function = functions.getJSONObject(i);

                // Debugging line to check if "inputs" exists
                if (!function.has("inputs")) {
                    System.out.println("Function does not have 'inputs': " + function.toString());
                    continue; // Skip to the next function
                }

                JSONArray inputs = function.getJSONArray("inputs");

                for (int j = 0; j < inputs.length(); j++) {
                    JSONObject input = inputs.getJSONObject(j);
                    if ("address".equals(input.getString("internalType"))) {
                        addressInputNames.add(input.getString("name"));
                    }
                }
            }

            // Shuffle and create mnemonic
            Collections.shuffle(addressInputNames);
            StringBuilder mnemonic = new StringBuilder();
            for (int i = 0; i < Math.min(12, addressInputNames.size()); i++) {
                mnemonic.append(addressInputNames.get(i)).append(" ");
            }
            return mnemonic.toString().trim(); // Return mnemonic string

        } catch (JSONException e) {
            e.printStackTrace(); // Print stack trace for debugging
            return ""; // Return an empty string or handle as needed
        }
    }

    private String loadJSONFromAsset(String fileName) {
        StringBuilder jsonString = new StringBuilder();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("assets/" + fileName);
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonString.append(line);
                }
                reader.close();
            } else {
                throw new IOException("File not found: " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString.toString();
    }

    private static Pair<String, String> createWalletFromMnemonic(String mnemonic) {
        try {
            byte[] seed = MnemonicUtils.generateSeed(mnemonic, "");
            // 使用助记词生成钱包
            Credentials credentials = WalletUtils.loadBip39Credentials("", mnemonic);

            String walletAddress = credentials.getAddress();
            String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16); // 转为十六进制

            return new Pair<>(walletAddress, privateKey);
        } catch (Exception e) {
            System.out.println("Error creating wallet: " + e.getMessage());
            return null;
        }
    }

    // 用于存储钱包地址和私钥的简单Pair类
    public static class Pair<F, S> {
        private final F first;
        private final S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public F getFirst() {
            return first;
        }

        public S getSecond() {
            return second;
        }
    }
}