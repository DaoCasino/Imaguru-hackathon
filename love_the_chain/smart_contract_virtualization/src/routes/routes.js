import {welcome, admin_createUnit, user_getUnitSummaries} from '../controllers/factoryController';
import {user_getUnitInfos} from '../controllers/unitController';

const routes = (app) => {
     
    app.route('/')
    .get(welcome);

    // ********************************************************************
    // FACTORY ROUTES
    // ********************************************************************
        
    // Admin routes
    // --------------------------------------------------------------------
    app.route('/factory/admin/createUnit')
    .put ( (req, res) => {
        admin_createUnit(req, res);
    })


    // User routes
    // --------------------------------------------------------------------
    app.route('/factory/user/getUnitSummaries')
    .get(user_getUnitSummaries);



    // ********************************************************************
    // UNIT ROUTES
    // ********************************************************************
    
    app.route('/unit/user/getUnitInfos/:unitAddress')
    .get(user_getUnitInfos);


}


export default routes;