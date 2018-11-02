import web3 from '../web3';

import UnitsFactory from '../build/UnitsFactory';

const instance = new web3.eth.Contract(
    JSON.parse(UnitsFactory.interface),
    '0x24fACF1530EA145Fe1CcD50A73c41e66097FdeD9'    
);

export default instance;