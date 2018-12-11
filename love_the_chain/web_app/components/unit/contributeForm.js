import React, {Component} from 'react';
import {Card, Button, Icon} from 'semantic-ui-react';
import {Router} from '../../routes'; 
import StatusMessage from '../general/StatusMessage';
import ErrorMessage from '../general/ErrorMessage';
import Unit from '../../ethereum/contractReference/unit';
import web3 from '../../ethereum/web3';


class ContributeForm extends Component {

    state = {
        errorMessage: '',
        statusMessage: '',
        loading: false
    }
    
    onDecline = async (event) => {
        event.preventDefault();
        Router.pushRoute('/');
    }

    async payWithMetamask () {
        const unit = Unit(this.props.address);

        console.log('this.props.amount: '+web3.utils.fromWei(this.props.amount.toString(),'ether'));
        const accounts = await web3.eth.getAccounts();

        if (accounts[0] == undefined)
            this.setState({errorMessage: 'Please, connect to Metamask'});

        else {
            await unit.methods.invest(this.props.sqiNumber).send({
                from: accounts[0],
                value: this.props.amount.toString()
            })
            .then( (receipt) => {
                this.setState({statusMessage: 'The transactionwas successfully processed and confirmed!'});
            })
            .catch ((err) =>{

                this.setState({
                    errorMessage: 'Something went wrong! Check Metamask status to verify the transaction was correctly processed.',
                    statusMessage: ''
                });
            });
        }

    }

    onAccept = async (event) => {
        event.preventDefault();
        console.log('onAccept')

        this.setState({
            errorMessage: '', 
            statusMessage: '',
            loading: true
        });

        if (this.props.payment == '')
            this.setState({errorMessage: 'Please, select a payment method.'})
        else {
            if (this.props.payment == 'metamask') {
                this.setState({statusMessage: 'Waiting for transaction confirmation...'})
                console.log('onAccept')
                await this.payWithMetamask();
            }
        }

        this.setState({loading: false});
    }

    render() {
        return (
            <div>
            <Card fluid>
                <Card.Content>
                    <Card.Header>Total amount: {web3.utils.fromWei(this.props.amount,'ether')} Ether</Card.Header>
                    <Card.Meta>Total amount of the contribution</Card.Meta>
                    <Card.Description>
                    If you confirm, you'll send <strong>{web3.utils.fromWei(this.props.amount, 'ether')} Ether </strong> to unit: {this.props.address}
                    </Card.Description>
                </Card.Content>
                <Card.Content extra>
                    <div className='ui two buttons'>
                    <Button 
                        primary 
                        loading={this.state.loading}
                        disabled={this.state.loading}
                        onClick={this.onAccept}>
                        Approve
                    </Button>
                    <Button 
                        basic 
                        color='grey' 
                        disabled={this.state.loading}
                        onClick={this.onDecline}>
                        Decline
                    </Button>
                    </div>
                </Card.Content>
            </Card>
            <ErrorMessage 
                visible={this.state.errorMessage!=''}
                contentMessage={this.state.errorMessage}
            />
            <StatusMessage 
                visible={this.state.statusMessage!=''}
                contentMessage={this.state.statusMessage}
            />
            </div>
        );
    }

}

export default ContributeForm;