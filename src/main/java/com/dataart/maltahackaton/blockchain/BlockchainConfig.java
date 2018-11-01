package com.dataart.maltahackaton.blockchain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.infura.InfuraHttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

@Slf4j
@Getter
@Configuration
public class BlockchainConfig {

    @Value("${ethereum.geth.url}")
    private String gethUrl;

    @Value("${ethereum.geth.connPool.maxConnTotal}")
    private Integer maxConnTotal;

    @Value("${ethereum.geth.connPool.maxConnPerRoute}")
    private Integer maxConnPerRoute;

    @Value("${ethereum.geth.connPool.maxConnIdle}")
    private Integer maxConnIdle;

    @Value("${ethereum.geth.connPool.maxIdleTimeSeconds}")
    private Integer maxIdleTimeSeconds;

    @Value("${ethereum.geth.connPool.readTimeoutSeconds}")
    private Integer readTimeoutSeconds;

    @Value("${ethereum.geth.connPool.connectionTimeoutSeconds}")
    private Integer connectionTimeoutSeconds;

    @Value("#{'${ethereum.owner.wallet.address}'.toLowerCase()}")
    private String ownerWalletAddress;

    @Value("${ethereum.owner.wallet.privateKey}")
    private String ownerWalletPrivateKey;

    @Value("${ethereum.receiptProcessor.sleepDurationMillis}")
    private Integer sleepDurationMillis;

    @Value("${ethereum.receiptProcessor.attempts}")
    private Integer attempts;

    @Value("${ethereum.cryptocoin.gas-price}")
    private BigInteger gasPrice;

    @Value("${ethereum.cryptocoin.gas-limit}")
    private BigInteger gasLimit;

    @Bean
    public Web3j web3j() throws IOException {
        log.info("Building web3j service for endpoint: {}", gethUrl);
        Web3j web3j = Web3j.build(new InfuraHttpService(gethUrl));
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        log.info("Geth version: {}", web3ClientVersion.getWeb3ClientVersion());
        return web3j;
    }

    @Bean
    public OkHttpClient gethHttpClient() {
        log.info("Building new OkHttpClient");
        ConnectionPool connectionPool = new ConnectionPool(
                maxConnIdle,
                maxIdleTimeSeconds,
                TimeUnit.SECONDS);
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(maxConnPerRoute);
        dispatcher.setMaxRequests(maxConnTotal);
        return new OkHttpClient.Builder()
                .connectionPool(connectionPool)
                .dispatcher(dispatcher)
                .readTimeout(readTimeoutSeconds, TimeUnit.SECONDS)
                .connectTimeout(connectionTimeoutSeconds, TimeUnit.SECONDS)
                .build();
    }
}
