import React, {Component} from 'react';
import {Router} from '../../routes'; 
//import StatusMessage from '../general/StatusMessage';
import ErrorMessage from '../general/ErrorMessage'
import {Form, Grid, Input, Button, Divider} from 'semantic-ui-react';
import web3 from '../../ethereum/web3'
class GoToContributeForm extends Component {

    state = {
        desideredSqi: '',
        loading: false,
        errorMessage: ''
    }

    checkForErrors () {
        let temp='';
        let tempInt = 1;
        
        console.log('available: ',this.props.availableSqi);

        if (isNaN(parseInt(this.state.desideredSqi)) || isNaN(tempInt * this.state.desideredSqi) || (parseInt(this.state.desideredSqi) <= 0))
            temp = 'Please, insert a valid SQI number!';
        else if (parseInt(this.state.desideredSqi) > this.props.availableSqi)
            temp = 'The max number of SQI is '+ this.props.availableSqi;

        return temp;
    }

    onSubmit = async (event) => {

        this.setState({
            errorMessage: '',
            loading: true
        })

        this.setState({errorMessage: await this.checkForErrors()});

        // there aren't errors
        if (this.state.errorMessage=='') {
            //console.log('amount: ',this.state.desideredSqi*this.props.sqiPrice);
            Router.pushRoute('/registered/confirmContribution/'+ this.state.desideredSqi+'/'+(this.state.desideredSqi*web3.utils.toWei(this.props.sqiPrice,'ether').toString())+'/'+this.props.address);
        }

        this.setState({loading: false});

    }

    render() {
        return (
            <div>
                <Form onSubmit={this.onSubmit}>
                    <Grid>
                        <Grid.Row columns={2}>
                            <Grid.Column>
                                <Input
                                    value={this.state.desideredSqi}
                                    onChange={event => this.setState({desideredSqi: event.target.value})}
                                    label="Amount of SQI [integer]"
                                    labelPosition="left"
                                    fluid
                                />

                            </Grid.Column>

                            <Grid.Column>
                                <Button
                                    primary
                                    fluid
                                    loading = {this.state.loading}
                                    disabled = {this.state.loading}
                                >
                                    Contribute
                                </Button>
                            </Grid.Column>
                        </Grid.Row>
                    </Grid>

                </Form>
                <ErrorMessage
                    contentMessage = {this.state.errorMessage}
                    visible = {this.state.errorMessage!=''}
                    //visible = {true}
                />

                <br />
                <Divider horizontal >General information</Divider>
            </div>
        );
    }

}

export default GoToContributeForm;







