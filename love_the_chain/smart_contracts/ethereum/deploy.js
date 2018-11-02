const HDWalletProvider = require('truffle-hdwallet-provider');
const Web3 = require('web3');
const compiledFactory = require('./build/UnitsFactory.json');

const provider = new HDWalletProvider(
    'possible tuition bless wire early target diary upgrade security later inner suspect',
    'https://rinkeby.infura.io/v3/dfe2719aee67465ebc01925f5dc73c79'
);

const web3 = new Web3(provider);

const deploy = async () => {
    const accounts = await web3.eth.getAccounts();
    console.log('Attempting to deploy from account',accounts[0]);
    
    const result = await new web3.eth.Contract(JSON.parse(compiledFactory.interface))
    .deploy({data: '0x' + compiledFactory.bytecode})
    .send( { from: accounts[0] });
//    .send( {gas: '7000000', from: accounts[0] });

    /*
    const result = await new web3.eth.Contract(JSON.parse(compiledFactory.interface))
        .deploy({ data: '0x' + bytecode, arguments: ['Hi there!'] })
        //.send( {gas: '7100000', from: accounts[0] }); // firstly I tried with this line of code, but got the same error
        .send( {from: accounts[0] }); //I replaced the preceeding line with this one, with no results
*/
    console.log('Contract deployd to', result.options.address);
};

deploy();