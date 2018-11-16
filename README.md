# DAO.Casino Hack Task for SIGMA Hack

![DAOCasino Logo](/images/pattern.jpg)

DAO.Casino revolutionizes the iGaming industry with its cutting-edge two-layer Protocol based on Ethereum. The Protocol automates transactions and facilitates interactions between all potential participants: casino operators, game developers, and affiliates. 
Our SDK enables any developer to create blockchain games and apps without solid knowledge of cryptography or cryptocurrencies. The distributed ledger technology ensures unlimited access to games from any location connected to the Internet.
DAO.Casino team is fully committed to delivering best products that make the gambling industry a better place.

## Contents
    
- [Tasks](#tasks)
- [Nominations](#nominations)
- [Technical requirements](#technical-requirements)
- [Participant guide](#participant-guide)

## Tasks

Here are several tasks aimed at improving our protocol. Best solutions and implementations will be rewarded according to the nominations or separately at the team’s discretion.

### :game_die: Use DAO.Casino SDK to create a game

For the SDK and documentation navigate [hackathon page](https://hackathon.dao.casino/).

### :video_game: Integrate the protocol elements into your game 

For instance, add functionality based on the elements of our protocol (Signidice, GameChannels) to an existing game.
 
### :nut_and_bolt: Implementing DAO.Casino Protocol in a Different Programming Language
 - Create Ethereum interaction component implementing the whole or part of the  ETHInstance interface from [dc-ethereum-utils](https://github.com/DaoCasino/dc-ethereum-utils/blob/development/src/interfaces/IEth.ts). Main methods are: getBalances, startTransaction, allowance, signData, signHash.
 - Implement p2p messaging protocol to communicate with bankroller using JsonRPC format as in current dc-messaging implementation. Run test to communicate with bankroller game instance [IDAppDealerInstance](https://github.com/DaoCasino/dc-core/blob/development/src/interfaces/IDAppInstance.ts)
 - Implement the dc-core client elements like *DAppPlayerInstance* in [dc-core](https://github.com/DaoCasino/dc-core)

## Nominations

### :trophy: Winners’ pot: 50000$ :trophy:

### Additional nominations and bonuses:

 - **Game publication on DAO.Casino platform and its further promotion**
 - **Invitation to the Sandbox team of developers**
 - **Joining the team of the major blockchain Igaming project**

Taking into account our goals and priorities, we offer the following nominations:

### Best DAO.Casino Game

Use our toolkit to create a new game based on DAO.Casino protocol!

 - The nomination is open for p2p (single step) games of change (based on random choice) with simple binary logic; to compute the result, a random number generation algorithm is used. For instance: Dice, Slots, Roulette, Baccarat, Shell game, Guess the number, etc. Surprise us by a new interpretation of this old concept!

### Best DAO.Casino Intergration

Have ready-made state-of-art games? Integrate DAO.Casino into your project. Use our toolkit to integrate mini-games, lotteries, lootboxes, etc.
 - Participation implies using Signidice and/or Game Channel logic, or suggesting another use of our protocol.

### Best visualization

Imagination and vibrant graphics are your forte? Create and implement a game visual theme! Let a unicorn turn the Wheel of Fortune and hamsters throw dices!

- games submitted within this nomination must use the DAO.Casino protocol or be compatible with it.

## Technical Requirements:

The protocol is built on the JS/Solidity stack, therefore all solutions must either be built on it or support integration with it:

 - Javascript (ES6+) / React.js / Vue.js / Angular.js / Pixi.js / Phaser
 - Docker, Node.JS (10.0), IPFS
 - Solidity, Truffle, Web3.js
 - MacOS, Linux, Windows 10 Pro

## Participant Guide:

Participant solutions have to be submitted as  pull-requests to this repository!

- Fork the repository
- Create your solution locally
- Write a README file according to the [template](https://github.com/DaoCasino/MBH-Hackathon/blob/master/EXAMPLE.md)
- create a pull-request
- ...
- PROFIT!
