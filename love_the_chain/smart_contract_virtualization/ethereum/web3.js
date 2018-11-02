/*

NEL CASO NI CUI LE TRANSAZIONI CON IL NOSTRO CONTRATTO VENGANO FATTE CON IL NOSTRO INDIRIZZO 
********************************************************************************************
*/

import Web3 from 'web3';
import HDWalletProvider from 'truffle-hdwallet-provider';

let web3;

const provider = new HDWalletProvider(
    'possible tuition bless wire early target diary upgrade security later inner suspect',
    'https://rinkeby.infura.io/v3/dfe2719aee67465ebc01925f5dc73c79'
);

web3 = new Web3(provider);

export default web3;




/*
import Web3 from 'web3';

let web3;


if (typeof window !== 'undefined' && typeof window.web3 !== 'undefined') {
    // we are in the browser
    web3 = new Web3(window.web3.currentProvider);
} else {
    // we are on the server *OR* the user is not running metamask
    const provider = new Web3.providers.HttpProvider(
        'https://rinkeby.infura.io/v3/dfe2719aee67465ebc01925f5dc73c79'
    );
    //console.log(campaigns);
    web3 = new Web3(provider);
}

export default web3;
*/