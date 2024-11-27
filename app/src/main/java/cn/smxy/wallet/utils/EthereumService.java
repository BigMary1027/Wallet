package cn.smxy.wallet.utils;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class EthereumService {
    private Web3j web3j;

    public EthereumService() {
        // 连接到Infura的以太坊节点
        web3j = Web3j.build(new HttpService("https://sepolia.infura.io/v3/fa1d6f1a4b2447bfa0932c9fb0a35a9f"));
    }

    public Web3j getWeb3j() {
        return web3j;
    }
}
