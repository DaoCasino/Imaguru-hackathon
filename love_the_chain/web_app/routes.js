const routes = require('next-routes')();

routes
    .add('/','/unitsList')
    .add('/admin/newUnit', '/admin/createUnit')
    .add('/registered/unitDetails/:unitAddress','/registered/unitDetails')
    .add('/registered/confirmContribution/:sqiNumber/:amount/:address','/registered/confirmContribution')
    
    .add('/admin/users', '/admin/users')
    //.add('/','/users/login')
    .add('/users/details/:userkey','/users/userDetails')
    .add('/users/register','/users/register')
    .add('/users/login', '/users/login')
    .add('/users/sendVerificationEmail/:userkey','/users/sendVerificationEmail')
    .add('/users/resetPassword','/users/resetPassword')
    .add('/users/changeEmailOrPassword/:userkey','/users/changeEmailOrPassword')
    //.add('/users','/users/userHome')

module.exports = routes;

