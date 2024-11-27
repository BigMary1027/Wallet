let web3;
        const address = "0x5e0ACCA54835f2acBfb5b1662aB28609B09f7B34"; // 替换为有效地址

        window.onload = async () => {
            if (typeof window.ethereum !== 'undefined') {
                web3 = new Web3(window.ethereum);
                try {
                    await window.ethereum.request({ method: 'eth_requestAccounts' });
                    const balance = await getBalanceApi();
                    if (balance !== null) {
                        document.getElementById('balance').innerText = "Balance: " + balance + " ETH";
                    }
                } catch (error) {
                    console.error("User denied account access", error);
                    document.getElementById('balance').innerText = "Error: Access denied";
                }
            } else {
                web3 = new Web3("wss://sepolia.infura.io/ws/v3/fa1d6f1a4b2447bfa0932c9fb0a35a9f");
                const balance = await getBalanceApi();
                if (balance !== null) {
                    document.getElementById('balance').innerText = "Balance: " + balance + " ETH";
                }
            }
        };

        const getBalanceApi = async () => {
            try {
                const res = await web3.eth.getBalance(address);
                const amount = web3.utils.fromWei(res, "ether");
                console.log("Balance fetched: ", amount); // 调试信息
                return amount;
            } catch (error) {
                console.error("Error fetching balance:", error);
                document.getElementById('balance').innerText = "Error fetching balance: " + error.message; // 显示错误信息
                return null; // 返回 null 以指示获取失败
            }
        };