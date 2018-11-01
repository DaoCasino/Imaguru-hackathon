package com.dataart.maltahackaton.blockchain;

import com.dataart.maltahackaton.utils.BlockchainUtils;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
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
        return BlockchainCharity.load(contractAddress, web3j,
                getReadOnlyTransactionManager(web3j), config.getGasPrice(), config.getGasLimit());
    }

    public BlockchainCharity loadFromOwner(String contractAddress) {
        return this.load(contractAddress, config.getOwnerWalletPrivateKey(), config.getGasPrice(), config.getGasLimit());
    }

    public BlockchainCharity loadFromOwner(String contractAddress, BigInteger gasPrice, BigInteger gasLimit) {
        return this.load(contractAddress, config.getOwnerWalletPrivateKey(), gasPrice, gasLimit);
    }

    public BlockchainCharity loadFrom(String contractAddress, String privateKey) {
        return this.load(contractAddress, privateKey, config.getGasPrice(), config.getGasLimit());
    }

    public BlockchainCharity loadFrom(String contractAddress, String privateKey, BigInteger gasPrice, BigInteger gasLimit) {
        return this.load(contractAddress, privateKey, gasPrice, gasLimit);
    }

    private BlockchainCharity load(String contractAddress, String privateKey, BigInteger gasPrice, BigInteger gasLimit) {
        return BlockchainCharity.load(contractAddress, web3j, getTransactionManager(privateKey, web3j), gasPrice, gasLimit);
    }

    public String deploy(String lotteryName, String lotteryDescription, String fundName, String fundDescription) {
        // TODO: deploy functionality
        return "0x............";
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
