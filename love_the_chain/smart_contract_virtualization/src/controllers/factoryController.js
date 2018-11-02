
import config from '../../config/config'
import web3 from "../../ethereum/web3";
import unitsFactory from '../../ethereum/contractsReference/unitsFactory';

// Welcome message
export const welcome = (req, res) => {
    res.send('You are Welcome to smart contract virtualization layer...');
}


// ***************************************************************************
// ADMIN CONTROLLERS
// ***************************************************************************


// CREATE UNIT
// --------------------------------------------------------------------------
export const admin_createUnit = async (req, res) => {
    console.log('admin_createUnit');
    //console.log(config.error_message);

    const accounts = await web3.eth.getAccounts();
    console.log('accounts[0]: ',accounts[0]);

    try {
        await unitsFactory.methods.createUnit(
            req.body.title, 
            req.body.sqiPrice, 
            req.body.totalSqi
        )
        .send({
            from: accounts[0]
        })
        .then( () => {
            res.send('Congrats!!! Unit created successfully.');
        })
    }
    catch (err) {
        //res.send(config.error_message);
        res.send(err.message);
    }

}




// ***************************************************************************
// USER CONTROLLERS
// ***************************************************************************


// Get units summary infos
// ---------------------------------------------------------------------------
export const user_getUnitSummaries = async (req, res) => {

    console.log('user_getUnitSummaries');

    //const accounts = await web3.eth.getAccounts();

    try {
        const units = await unitsFactory.methods.getProposedUnits().call();

        const unitInfos = await Promise.all (
            Array(units.length)
            .fill()
            .map((element, index) => {
                return unitsFactory.methods.proposedUnitSummaries(units[index]).call();
            })
        )
        res.send(unitInfos);
    }
    catch (err) {
        res.send(config.error_message);
    };

}




