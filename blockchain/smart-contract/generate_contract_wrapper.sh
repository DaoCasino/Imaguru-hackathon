#!/usr/bin/env bash
solcjs BlockchainCharity.sol --bin --abi --optimize -o ./
#mv BlockchainCharity_sol_BlockchainCharity.abi BlockchainCharity.abi
#mv BlockchainCharity_sol_BlockchainCharity.bin BlockchainCharity.bin
#rm BlockchainCharity_sol_owned.abi
#rm BlockchainCharity_sol_owned.bin
#rm BlockchainCharity_sol_administrated.abi
#rm BlockchainCharity_sol_administrated.bin
#cd ../..
#web3j solidity generate config/smartcontract/BlockchainCharity.bin config/smartcontract/BlockchainCharity.abi -o ./src/main/java -p com.dataart.maltahackaton.blockchain
