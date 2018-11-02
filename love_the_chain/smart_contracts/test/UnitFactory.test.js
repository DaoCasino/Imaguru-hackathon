const compiledFactory = require('../ethereum/build/UnitsFactory.json');
const compiledUnit = require('../ethereum/build/Unit.json');

const assert = require('assert');
const ganache = require('ganache-cli');
const Web3 = require('web3');
const web3 = new Web3(ganache.provider());

beforeEach(async () => {

    accounts = await web3.eth.getAccounts();

    factory = await new web3.eth.Contract(JSON.parse(compiledFactory.interface))
        .deploy({data: compiledFactory.bytecode})
        .send({from: accounts[0], gas: '3000000'});

    
    await factory.methods.createUnit("First Unit", 100, 5000).send({
        from: accounts[0],
        gas: '5000000'
    });

    [unitAddress] = await factory.methods.getProposedUnits().call();
    unit = await new web3.eth.Contract(
        JSON.parse(compiledUnit.interface),
        unitAddress
    );


});

describe ('Contracts deployed', () => {
    it ('Deploy a factory and a unit', () => {
        assert.ok(factory.options.address);
        assert.ok(unit.options.address);
        console.log('factory address: ',factory.options.address);
        console.log('unit address',unit.options.address);
        console.log('creator address',accounts[0]);
        
    });


    it ('Invest', async () => {
        //let summary = await factory.methods.getProposedTasks().call();
        await unit.methods.invest(10).send({
            from: accounts[0],
            value: 1000
        });
        
        let summary = await unit.methods.getUnitInfo().call();
        console.log('factory address: '+summary[3]);

        assert.ok(summary[5] == 1000);
        //assert.ok(summary[3] == 90);


/*
        await unit.methods.createInvestimentContract("ciao",10,100);
        const inv = await unit.methods.getInvestiments().call();
        console.log(inv);
        assert.ok(inv != undefined);

        
        const balance = await factory.methods.getFactoryBalance().call();
        console.log(balance);
        */
        
    });

    /*
    it ('task done is false at init', async () => {
        const summary = await task.methods.getTaskInfo().call();

        //console.log('creator address: '+summary[1]); 
        //console.log('task address: '+summary[7]); 
        assert.equal(summary[2], false);
    });

    
    it ('catchTask invocation', async () => {
        await task.methods.catchTask(factory.options.address).send({
            from: accounts[0],
            value: 20000
        });
        const summary = await task.methods.getTaskInfo().call();
        assert.equal(summary[2], false);
    });
    */



});


