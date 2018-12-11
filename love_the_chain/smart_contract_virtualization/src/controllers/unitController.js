import config from '../../config/config';
import Unit from '../../ethereum/contractsReference/unit';
import web3 from "../../ethereum/web3";


// Get unit infos
// -----------------------------------------------------------------------------
export const user_getUnitInfos = async (req, res) => {
    console.log('user_getUnitInfos');
    console.log(req.params.unitAddress);
    
    try {
        const unit = Unit(req.params.unitAddress);
        const summary = await unit.methods.getUnitInfo().call();
        res.send(summary)
    }
    catch (err) {
        res.send(config.error_message);
    }
    

}

