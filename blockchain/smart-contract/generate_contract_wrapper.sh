#!/usr/bin/env bash
solcjs BlockchainCharity.sol --bin --abi --optimize -o ./
mv BlockchainCharity_sol_CharityLottery.abi BlockchainCharity.abi
mv BlockchainCharity_sol_CharityLottery.bin BlockchainCharity.bin
rm BlockchainCharity_sol_owned.abi
rm BlockchainCharity_sol_owned.bin
cd ../..
web3j solidity generate blockchain/smart-contract/BlockchainCharity.bin blockchain/smart-contract/BlockchainCharity.abi -o ./src/main/java -p com.dataart.maltahackaton.blockchain
