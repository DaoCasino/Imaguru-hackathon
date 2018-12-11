import React, {Component} from 'react';
import {Form, Button, Input} from 'semantic-ui-react';
import Layout from '../../components/general/Layout';
import axios from 'axios';
import generalConfig from '../../config/generalConfig'; 
import StatusMessage from '../../components/general/StatusMessage';
import ErrorMessage from '../../components/general/ErrorMessage'; 
import web3 from '../../ethereum/web3';
import LayoutAdmin from '../../components/general/LayoutAdmin';


class UnitsList extends Component {

    state = {
        loading: false,
        errorMessage: '',
        statusMessage: '',

        title: '',
        sqiPrice: '',
        totalSqi: ''
    };

    validateFields () {
        
        let temp = ''; 
        if (this.state.title == '')
            temp = '*** Insert a valid title';
        if (this.state.sqiPrice == '')
            temp = temp + ' *** Insert a valid SQI price';
        if (this.state.totalSqi == '')
            temp = temp + '*** Insert a valid amount of Total SQI';

        console.log('temp: '+temp)    
        if (temp != '')
            this.setState({errorMessage: temp});
        
    }

    onSubmit = async (event) => {
        event.preventDefault();

        this.setState({
            errorMessage: '',
            statusMessage: ''
        });

        
        await this.validateFields();
        //console.log('errorMessage: ',this.state.errorMessage);
        if (this.state.errorMessage == '') {

            this.setState({loading: true});

            const url = generalConfig.smart_contract_virtualization_api_url;
            console.log('TotalSqi: ', this.state.totalSqi)
            await axios.put(url+'/factory/admin/createUnit', {
                title: this.state.title, 
                totalSqi: this.state.totalSqi,
                sqiPrice: web3.utils.toWei(this.state.sqiPrice, 'ether')
            })
            .then( (res) => {
                //console.log(res.data);
                if (res.data == generalConfig.smart_contract_virtualization_error_message)
                    this.setState({errorMessage: res.data});
                else 
                    this.setState({statusMessage: res.data});
            })
            .catch ((err) => {
                this.setState({errorMessage: err.message});
            })
            
            if (this.state.errorMessage == '') {
                this.setState({
                    title: '',
                    totalSqi: '',
                    sqiPrice: ''
                })
            }
            
            this.setState({loading: false});
        }
    }

    render() {
        return (
            <LayoutAdmin>
                <h3>Create a new Unit</h3>
                <Form onSubmit={this.onSubmit} error={!!this.state.errorMessage}>

                    <Form.Field>
                        <Input 
                            label={{ content: 'Title' }}
                            value={this.state.title}
                            onChange={event =>
                                this.setState({title: event.target.value})}
                        />
                    </Form.Field>

                    <Form.Field>
                        <Input 
                            label={{ content: 'SQI Price [ether]' }}
                            value={this.state.sqiPrice}
                            onChange={event =>
                                this.setState({sqiPrice: event.target.value})}
                        />
                    </Form.Field>

                    <Form.Field>
                        <Input 
                            label={{ content: 'Total SQI [integer]' }}
                            value={this.state.totalSqi}
                            onChange={event =>
                                this.setState({totalSqi: event.target.value})}
                        />
                    </Form.Field>
                                
                    <Button 
                        loading={this.state.loading} 
                        disabled={this.state.loading}
                        primary
                        fluid>
                        Create!
                    </Button>

                    <StatusMessage 
                        visible = {this.state.statusMessage!=''}
                        contentMessage = {this.state.statusMessage}
                    />

                    <ErrorMessage
                        visible = {this.state.errorMessage!=''}
                        contentMessage = {this.state.errorMessage}
                    />

                </Form>
            </LayoutAdmin>
        );
    }
}


export default UnitsList;


