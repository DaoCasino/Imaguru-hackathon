import React, {Component} from 'react';
import {Table, Button} from 'semantic-ui-react';
import Layout from '../components/general/Layout';
import axios from 'axios';
import generalConfig from '../config/generalConfig'; 
import UnitSummaryRow from '../components/factory/UnitSummaryRow'


class UnitsList extends Component {


    static async getInitialProps() {
        let unitInfos = [];

        const url = generalConfig.smart_contract_virtualization_api_url+'/factory/user/getUnitSummaries';
        await axios.get(url)
        .then( res => {
            unitInfos = res.data;
        });
        
        return {unitInfos}
    }

    renderRows() {
        return this.props.unitInfos.map((unitInfo, index) =>{
            return (
                <UnitSummaryRow
                    key = {index}
                    title={unitInfo.title}
                    sqiPrice={unitInfo.sqiPrice}
                    totalSqi={unitInfo.totalSqi}
                    availableSqi={unitInfo.currentAvailableSqi}
                    unitAddress={unitInfo.unitAddress}
                />
            );
        });
    }


    render () {
        const {Header, Row, HeaderCell, Body} = Table;


        return (
            <Layout>
                <h3>Houses List</h3>
                <Table>
                    <Header>
                        <Row>
                            <HeaderCell>Title</HeaderCell>
                            <HeaderCell>SQI Price</HeaderCell>
                            <HeaderCell>Total SQI</HeaderCell>
                            <HeaderCell>Available SQI</HeaderCell>
                            <HeaderCell>Address</HeaderCell>
                            <HeaderCell>View Details</HeaderCell>
                        </Row>
                    </Header>
                    <Body>
                        {this.renderRows()}
                    </Body>
                </Table>
            </Layout>
        );
    }
}

export default UnitsList;

