package cn.smxy.wallet.Activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import cn.smxy.wallet.R;
import cn.smxy.wallet.utils.WalletManager;


public class WebActivity extends AppCompatActivity {
    private TextView bance;
    private Web3j web3j;
    //0xf1405736e8d40aed042529bb493e3f03f02bdf0f  0x90D429093ef890E4B919A551CC602898131D2EfA
    private final String address = "0xf1405736e8d40aed042529bb493e3f03f02bdf0f"; // 替换为有效地址0x90D429093ef890E4B919A551CC602898131D2EfA
    private static final String TAG = "WebActivity";
    private static final String CONTRACT_ADDRESS = "0xB617D1AC68C184d193a79cd5C8FF55a69A4f08e6";

    private String currentAccount;
    private TextView transactionsTextView;
    private EditText buyAmount,etToAddress,etAmount;
    private WalletManager walletManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        bance = findViewById(R.id.textView_bannce);
        transactionsTextView = findViewById(R.id.transactionsTextView);

        // 初始化 Web3j
        web3j = Web3j.build(new HttpService("https://sepolia.infura.io/v3/fa1d6f1a4b2447bfa0932c9fb0a35a9f"));

        // 获取余额
        getBalance();


        //getTransactions(address);
        getLatestTransactions(address);

        //转账
        etToAddress = findViewById(R.id.etToAddress);
        etAmount = findViewById(R.id.etAmount);
        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(v -> {
            String toAddress = etToAddress.getText().toString().trim();
            String amountStr = etAmount.getText().toString().trim();

            if (toAddress.isEmpty() || amountStr.isEmpty()) {
                Toast.makeText(this, "请填写目标地址和金额", Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal amount;
            try {
                amount = new BigDecimal(amountStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "金额格式不正确", Toast.LENGTH_SHORT).show();
                return;
            }

            String privateKey = "你的私钥"; // 注意安全处理私钥

            new Thread(() -> {
                try {
                    String txHash = walletManager.transferEther(toAddress, amount, privateKey);
                    runOnUiThread(() -> {
                        Toast.makeText(this, "转账成功，交易哈希: " + txHash, Toast.LENGTH_LONG).show();
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> {
                        Toast.makeText(this, "转账失败: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    });
                }
            }).start();
        });

    }
    private void getLatestTransactions(String accountAddress) {
        new Thread(() -> {
            try {
                Log.d("Debug", "开始获取最新区块的交易记录");
                EthBlock block = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, true).send();
                EthBlock.Block latestBlock = block.getBlock();

                if (latestBlock != null && latestBlock.getTransactions() != null) {
                    //Log.d("Debug", "成功获取最新区块，找到交易数量: " + latestBlock.getTransactions().size());
                    for (EthBlock.TransactionResult txResult : latestBlock.getTransactions()) {
                        Transaction transaction = (Transaction) txResult.get();
                        //Log.d("Debug", "成功获取最新区块: " + transaction.getFrom());
                        String from = transaction.getFrom();
                        String to = transaction.getTo();
                        BigInteger value = transaction.getValue();
                        String hash = transaction.getHash();

                        if (from.equalsIgnoreCase(accountAddress) ||
                                (to != null && to.equalsIgnoreCase(accountAddress))) {
                            displayTransactionDetail("哈希: " + hash + ", 从: " + from + ", 到: " + to + ", 金额: " + value);
                        }
                    }
                } else {
                    Log.d("Debug", "最新区块为空或没有交易");
                }
            } catch (Exception e) {
                Log.e("Error", "获取最新区块的交易失败", e);
            }
        }).start();
    }

    private void displayTransactionDetail(String detail) {
        runOnUiThread(() -> {
            transactionsTextView.append(detail + "\n");
        });
    }

    private void getBalance() {
        CompletableFuture<EthGetBalance> futureBalance = web3j.ethGetBalance(address, org.web3j.protocol.core.DefaultBlockParameterName.LATEST).sendAsync();

        futureBalance.thenAccept(ethGetBalance -> {
            BigInteger balanceInWei = ethGetBalance.getBalance();
            BigDecimal balanceInEth = new BigDecimal(balanceInWei).divide(new BigDecimal(1_000_000_000_000_000_000L)); // Wei to Ether
            runOnUiThread(() -> {
                bance.setText("Balance: " + balanceInEth + " ETH");
            });
        }).exceptionally(e -> {
            runOnUiThread(() -> {
                Toast.makeText(WebActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            });
            return null;
        });
    }
}