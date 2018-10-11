# DAO.Casino Hack Tasks for Malta Blockchain Hackathon

![DAOCasino Logo](/images/pattern.jpg)

DAO.Casino revolutionizes the iGaming industry with its cutting-edge two-layer Protocol based on Ethereum. The Protocol automates transactions and facilitates interactions between all potential participants: casino operators, game developers, and affiliates. 
Our SDK enables any developer to create blockchain games and apps without solid knowledge of cryptography or cryptocurrencies. The distributed ledger technology ensures unlimited access to games from any location connected to the Internet.
DAO.Casino team is fully committed to delivering best products that make the gambling industry a better place.

Присоединяйтесь к нашему чату в [telegram](https://t.me/daocasino_developers)
Удачи!

## Содержание
    
- [Задачи](#Задачи)
- [Номинации](#Номинации)
- [Технические требования](#Технические-требования)
- [Правила подачи решений](#Процедура-подачи-работ-от-участников)

## Tasks

Here are several tasks aimed at improving our protocol. Best solutions and implementations will be rewarded according to the nominations or separately at the team’s discretion.

### :game_die: Use DAO.Casino SDK to create a game

For the SDK and documentation navigate to [hackathon page](https://hackathon.dao.casino/)

### :video_game: Integrate the protocol elements to your game 

For instance, add functionality based on elements of our protocol (Signidice, GameChannels) to an existing game.

### :cat: Create a gambling Chrome extension:

Develop a browser extension (metamask fork/equivalent) that allows:
 - importing/creating accounts (private keys/ mnemonic);
 - receiving data from iframe, signing it with private key (without request to a user) and sending the signature back to iframe.
 - validating received data and displaying warnings in case of invalidity; saving data and relevant signatures, implementing  - integration with web3js 1.0
Optional tasks:
 - Provide a convenient display option for the signed data; implement data request from the game smart-contract;
 - Implement data sending to the game smart contract;
 - Implement integration with gameChannels where responses are made only to signatures from gameChannels(open/update/close/gameRound) function;
 - Ensure storage of smart-contract/site/public key Whitelist/ Blacklist.


### :floppy_disk: IPFS deployer

Implement functionality to deploy a game in the IPFS with further recording in the Game market contract.

Steps:
 - Write a game deploy script in the IPFS (~50-150 MB) using  ipscend.js or any other method (e.g. IPFS daemon setup, etc.);
 - Once the hash is received, the game has to be added to the game market contract (game contract address and the IPFS hash for the game front-end storage);
 - Obtain and render the game list.


## Номинации

### :trophy: Winners’ pot: ask organizers :trophy:

Additional bonuses:

 - **Game publication on DAO.Casino platform and its further promotion**
 - **Invitation to the Sandbox team of developers**
 - **Joining the team of the major blockchain Igaming project**

Taking into account our goals and priorities, we offer the following nominations:

### «Best DAO.Casino Game»

Use our toolkit to create a new game based on DAO.Casino protocol!

 - The nomination is open for p2p (single step) games of change (based on random choice) with simple binary logic; to compute the result, a random number generation algorithm is used. For instance: Dice, Slots, Roulette, Baccarat, Shell game, Guess the number, etc. Surprise us by a new interpretation of this old concept!


### «Best DAO.Casino Intergration»


Have ready-made state-of-art games? Integrate DAO.Casino into your project. Use our toolkit to integrate mini-games, lotteries, lootboxes, etc.
 - Participation implies using Signidice and/or Game Channel logic, or suggesting another use of our protocol.

### «Best Solution for DAO.Casino Protocol»

Have improvements for our product and implementation in mind? You are welcome to share your own algorithms and schemes for game development workflow and for random number generation game, as well as off-chain solutions and other ideas!

- Assessment criteria: value for DAO.Casino protocol in general or for a specific implementation.

### «Best Smart Contract»

Solidity guru is your second name? Then write the best game contract, implement complex logic or extend functionality of original contracts!

 - solutions submitted within this nomination must be compilable with and deployable in the Ropsten network

### «Лучшая визуализация»

Imagination and vibrant graphics are your forte? Create and implement a game visual theme! Let a unicorn turn the Wheel of Fortune and hamsters throw dices!

- games submitted within this nomination must use the DAO.Casino protocol or be compatible with it.

## Technical Requirements:

The protocol is built on the JS/Solidity stack, therefore all solutions must either be built on it or support integration with it:

 - Javascript (ES6+) / React.js / Vue.js / Angular.js / Pixi.js / Phaser
 - Docker, Node.JS (10.0), IPFS
 - Solidity, Truffle, Web3.js
 - MacOS, Linux, Windows 10 Pro


## Participant Guide:

Решения принимаются в виде **pull-request'ов** в этот репозиторий!

- Fork the repository
- Create your solution locally
- Write a README file according to the (template)[]
- create a pull-request
- ...
- PROFIT!
