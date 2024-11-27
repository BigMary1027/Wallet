package cn.smxy.wallet.utils;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Web3Client {
    private static final String INFURA_URL = "wss://sepolia.infura.io/ws/v3/fa1d6f1a4b2447bfa0932c9fb0a35a9f"; // 替换为你的Infura项目ID
    private static Web3j web3j;

    static {
        web3j = Web3j.build(new HttpService(INFURA_URL));
    }

    public static BigDecimal getBalance(String address) throws Exception {
        EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        BigInteger wei = ethGetBalance.getBalance();
        return Convert.fromWei(new BigDecimal(wei), Convert.Unit.ETHER);
    }
}
