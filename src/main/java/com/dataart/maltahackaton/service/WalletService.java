package com.dataart.maltahackaton.service;

import com.dataart.maltahackaton.domain.Wallet;
import com.dataart.maltahackaton.repository.WalletRepository;
import com.dataart.maltahackaton.utils.BlockchainUtils;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet() {
        String privateKey = BlockchainUtils.generatePrivateKey();
        Credentials credentials = BlockchainUtils.buildCredentials(privateKey);
        Wallet wallet = new Wallet();
        wallet.setPrivateKey(privateKey);
        wallet.setPublicKey(credentials.getEcKeyPair().getPublicKey().toString());
        wallet.setAddress(credentials.getAddress());
        return wallet;
    }
}
