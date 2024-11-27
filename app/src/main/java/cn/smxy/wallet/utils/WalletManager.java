package cn.smxy.wallet.utils;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;

public class WalletManager {
    private Web3j web3j;

    public WalletManager(String nodeUrl) {
        this.web3j = Web3j.build(new HttpService(nodeUrl));
    }

    public String transferEther(String toAddress, BigDecimal amount, String privateKey) throws Exception {
        // 创建 Credentials
        Credentials credentials = Credentials.create(privateKey);

        // 转账
        TransactionReceipt transactionReceipt = Transfer.sendFunds(
                web3j, credentials, toAddress, amount, Convert.Unit.ETHER
        ).send();

        return transactionReceipt.getTransactionHash();
    }
}
