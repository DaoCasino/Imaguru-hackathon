import admin from './config_firebase_admin';


export const enableUser = (req, res) => {
    console.log('enableUser called');
    admin.auth().updateUser(req.params.userId, {disabled:false})
    .then ((userRecord) => {
        res.send('ENABLE USER OK');
    })
    .catch( (err) => {
        res.send(err.message);
    });
} 

export const disableUser = (req, res) => {
    admin.auth().updateUser(req.params.userId, {disabled:true})
    .then ((userRecord) => {
        res.send('DISABLE USER OK'); 
    })
    .catch( (err) => {
        res.send(err.message);
    });
} 

export const getUserData = (req, res) => {
    admin.auth().getUser(req.params.userId)
    .then( ( userRecord) => {
        var o = {};
        var key = 'userRecord';
        o[key] = [];

        var data = {
            email: userRecord.email,
            emailVerified: userRecord.emailVerified,
            approved: userRecord.approved,
            disabled: userRecord.disabled,
        }
        o[key].push(data);
     
        res.send(o[key]);
    })
    .catch( (err) => {
        res.send(err.message);
    });
}


















