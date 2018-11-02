const {createServer} = require('http');
//const express = require('express');
const next = require('next');
const routes = require('./routes');
const bodyParser = require('body-parser');



const app = next({
    dev: process.env.NODE_ENV !== 'production'
});

const handler = routes.getRequestHandler(app);

app.prepare().then( () => { 
    
    
    createServer(handler).listen(3000, (err) =>{
        if (err) throw err;
        console.log('Ready on localhost:3000');
    });
    

    /*
    const server = express();
    // parse application/x-www-form-urlencoded
    //server.use(bodyParser.urlencoded({ extended: false }));
    server.use(bodyParser());

    // parse application/json
    server.use(bodyParser.json());

    server.listen(3000, (err) => {
        if (err) throw err;
        console.log('> Ready on http://localhost:3000')
    })
    */

});