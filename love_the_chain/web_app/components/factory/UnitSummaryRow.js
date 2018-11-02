import React, {Component} from 'react';
import {Table, Button} from 'semantic-ui-react';
import { Router } from '../../routes';
import web3 from '../../ethereum/web3'
import firebase from '../../config/firebase';

class UnitSummaryRow extends Component {

    
    onViewDetails = async (event) => { 
        event.preventDefault();
        const myAddress = this.props.unitAddress;
        Router.pushRoute("/registered/unitDetails/"+myAddress);
    }


    render() {
        const {Row, Cell} = Table;
        //const {id, title, sqiPrice, totalSqi} = this.props;

        return (
            <Row>
                <Cell>{this.props.title}</Cell>
                <Cell>{web3.utils.fromWei(this.props.sqiPrice, 'ether').toString()+' ETH'}</Cell>
                <Cell>{this.props.totalSqi}</Cell>
                <Cell>{this.props.availableSqi}</Cell>
                <Cell>{this.props.unitAddress}</Cell>
                
                <Cell>
                    <Button 
                        color="green" 
                        onClick={this.onViewDetails} 
                        basic
                        disabled={firebase.auth().currentUser == undefined || !firebase.auth().currentUser.emailVerified} 
                        >View Details</Button>
                </Cell>

            </Row>
        );
    }

}

export default UnitSummaryRow;