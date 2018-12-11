import React, {Component} from 'react';
import {Router} from '../../routes'; 
import StatusMessage from '../../components/general/StatusMessage';
import ErrorMessage from '../../components/general/ErrorMessage'
import Layout from '../../components/general/Layout'
import ContributeForm from '../../components/unit/contributeForm'
import {Form, Checkbox, Divider} from 'semantic-ui-react';

class ConfirmContribution extends Component {

    state = {
        payment: ''
    }

    static async getInitialProps(props) {
        const amount = props.query.amount;
        const address = props.query.address;
        const sqiNumber = props.query.sqiNumber;

        return {
            amount,
            address,
            sqiNumber
        }
    }    


    handleChange = (e, { value }) => this.setState({ payment: value })

    renderPaymentOptions() {
        
        return (
            <Form>
              <Form.Field>
                <b>Select the payment method: {this.state.payment}</b>
              </Form.Field>
              <Form.Field>
                <Checkbox
                  radio
                  label='Pay with Metamask'
                  name='checkboxRadioGroup'
                  value='metamask'
                  checked={this.state.payment === 'metamask'}
                  onChange={this.handleChange}
                />
              </Form.Field>
              <Form.Field>
                <Checkbox
                  radio
                  label='Pay with Coinbase'
                  name='checkboxRadioGroup'
                  value='coinbase'
                  checked={this.state.payment === 'coinbase'}
                  onChange={this.handleChange}
                />
              </Form.Field>
              <br/>
            </Form>
        )
    }


    render() {
        return (
            <Layout>
                <h3>Confirm contribution</h3>
                <Divider horizontal> STEP 1</Divider>
                {this.renderPaymentOptions()}

                <Divider horizontal> STEP 2</Divider>
                <ContributeForm 
                    amount = {this.props.amount}
                    address = {this.props.address}
                    payment = {this.state.payment}
                    sqiNumber = {this.props.sqiNumber}
                />

            </Layout>
        );
    }


}

export default ConfirmContribution;