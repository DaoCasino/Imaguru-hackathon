import {enableUser, disableUser, getUserData, createUser} from '../controllers/hypeController'

const routes = (app) => {
 
    app.route('/getUserData/:userId')
    .get(getUserData);
    
    
    app.route('/enableUser/:userId')
    .put((req, res) => {
        enableUser(req, res);
    })
      
    app.route('/disableUser/:userId')
    .put((req, res) => {
       disableUser(req,res);
    })
    
}

export default routes;