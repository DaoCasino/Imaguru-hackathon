import React, {Component} from 'react';
import axios from 'axios';
import generalConfig from '../../config/generalConfig'; 
import Layout from '../../components/general/Layout';
import {Card, Grid, Divider, Message, GridRow} from 'semantic-ui-react';
import GoToContributeForm from '../../components/unit/goToContributeForm';
import web3 from '../../ethereum/web3'


class UnitDetails extends Component {


    static async getInitialProps(props) {
        console.log('inside');
        console.log('unit: ',props.query.unitAddress);

        let data = [];
        let result;

        const etherscanLink = 'https://rinkeby.etherscan.io/address/'+props.query.unitAddress;
        console.log(etherscanLink);
        
        const url = generalConfig.smart_contract_virtualization_api_url+'/unit/user/getUnitInfos/'+props.query.unitAddress;

        await axios.get(url)
        .then((res)=>{
            data.push(res.data);
            data.map( (a, b) => {
                result = a;
            })
        });

        return {
            etherscanLink: etherscanLink,
            address: props.query.unitAddress,
            //factoryAddress: result[0],
            title: result[1],
            totalSqi: result[2],
            currentSqi: result[3],
            sqiPrice: web3.utils.fromWei(result[4],'ether'),
            balance: web3.utils.fromWei(result[5], 'ether')
        }

    }

    renderInfos() {
        const items = [
            {
                header: this.props.title,
                meta: 'Unit title',
                description: 'The title of the Unit',
                style: {overflowWrap: 'break-word'}  
            },

            {
                header: 'Etherscan',
                meta: 'The etherscan link of the unit',
                description: <a href={this.props.etherscanLink} target="_blank"> {this.props.etherscanLink}</a>,
                style: {overflowWrap: 'break-word'}  
            },
            {
                header: 'Total SQI',
                meta: 'Total SQI of the unit',
                description: this.props.totalSqi,
                style: {overflowWrap: 'break-word'}  
            },
            {
                header: 'Current available SQI',
                meta: 'Current Available SQI of the unit',
                description: this.props.currentSqi,
                style: {overflowWrap: 'break-word'}  
            },
            {
                header: 'SQI price',
                meta: 'The price of a single SQI in Ether',
                description: this.props.sqiPrice,
                style: {overflowWrap: 'break-word'}  
            },
            {
                header: 'Unit Balance',
                meta: 'The current balance of the Unit in Ether',
                description: this.props.balance,
                style: {overflowWrap: 'break-word'}  ,
            }

        ]

        return <Card.Group centered items={items} />

    }


    render() {
        return (
            <Layout>
                <h3>Unit Details</h3>
                <GoToContributeForm 
                    address = {this.props.address}
                    sqiPrice = {this.props.sqiPrice}
                    availableSqi = {this.props.currentSqi}
                />

                <Grid>
                    <Grid.Row >
                        <Grid.Column>
                            {this.renderInfos()}
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
            </Layout>
        );
    }
}

export default UnitDetails