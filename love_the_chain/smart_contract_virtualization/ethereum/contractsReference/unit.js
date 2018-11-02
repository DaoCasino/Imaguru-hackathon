import web3 from '../web3';
import Unit from '../build/Unit.json';

export default address => {
    return new web3.eth.Contract(
        JSON.parse(Unit.interface),
        address
    );
}