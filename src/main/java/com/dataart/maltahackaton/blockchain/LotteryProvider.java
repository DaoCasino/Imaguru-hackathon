package com.dataart.maltahackaton.blockchain;

import com.dataart.maltahackaton.utils.BlockchainUtils;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;
import org.web3j.tx.response.TransactionReceiptProcessor;

import java.math.BigInteger;

@Service
public class LotteryProvider {

    private final BlockchainConfig config;
    private final Web3j web3j;

    public LotteryProvider(BlockchainConfig config, Web3j web3j) {
        this.config = config;
        this.web3j = web3j;
    }

    public BlockchainCharity loadReadOnly(String contractAddress) {
        return BlockchainCharity.load(contractAddress, web3j, getReadOnlyTransactionManager(web3j), new DefaultGasProvider());
    }

    public BlockchainCharity loadFromOwner(String contractAddress) {
        return this.load(contractAddress, config.getOwnerWalletPrivateKey());
    }

    public BlockchainCharity loadFromOwner(String contractAddress, BigInteger gasPrice, BigInteger gasLimit) {
        return this.load(contractAddress, config.getOwnerWalletPrivateKey());
    }

    public BlockchainCharity loadFrom(String contractAddress, String privateKey, BigInteger gasPrice, BigInteger gasLimit) {
        return this.load(contractAddress, privateKey);
    }

    private BlockchainCharity load(String contractAddress, String privateKey) {
        return BlockchainCharity.load(contractAddress, web3j, getTransactionManager(privateKey, web3j), new DefaultGasProvider());
    }

    public String deploy(String privateKey, String charityAddress, BigInteger durationInMinutes, BigInteger feePercent,
                         BigInteger maxFee, BigInteger priceForTheTicket, BigInteger winnerPercent, BigInteger charityPercent) throws Exception {
        return BlockchainCharity.deploy(web3j, BlockchainUtils.buildCredentials(privateKey),
                new StaticGasProvider(config.getGasPrice(), config.getGasLimit()), charityAddress,
                durationInMinutes, feePercent, maxFee, priceForTheTicket, winnerPercent, charityPercent).send().getContractAddress();
    }

    private TransactionManager getTransactionManager(String privateKey, Web3j web3j) {
        return new FastRawTransactionManager(web3j, BlockchainUtils.buildCredentials(privateKey),
                getReceiptProcessor(web3j));
    }

    private TransactionManager getReadOnlyTransactionManager(Web3j web3j) {
        return new ReadonlyTransactionManager(web3j, config.getOwnerWalletAddress());
    }

    private TransactionReceiptProcessor getReceiptProcessor(Web3j web3j) {
        return new PollingTransactionReceiptProcessor(web3j,
                config.getSleepDurationMillis(), config.getAttempts());
    }

}
