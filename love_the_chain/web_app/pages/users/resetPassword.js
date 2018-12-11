import react, {Component} from 'react';
import firebase from '../../config/firebase';
import Layout from '../../components/general/Layout';
import StatusMessage from '../../components/general/StatusMessage';
import {Button, Input, Grid} from 'semantic-ui-react';

class ResetPassword extends Component {

    state = {
        textMessage: 'An email will be send to reset your password',
        emailSent: false,
        email:''
    }

    onSendEmail = async (event) => {
        firebase.auth().sendPasswordResetEmail(this.state.email)
        .then( () => {
            this.setState({
                textMessage: 'Email to reset your password sent',
                emailSent: true
            });
        })
        .catch ((err) => {
            console.log(err.message);
        });
    }
    
    render() {
        return (
            <Layout>
                <h3>Reset password</h3>
                <StatusMessage
                    visible={true}
                    contentMessage={this.state.textMessage}
                />
                <Grid>
                    <Grid.Row columns={2}>
                        <Grid.Column>
                            <Input 
                                fluid
                                label={{ content: 'email' }}
                                value={this.state.email}
                                onChange={event =>
                                    this.setState({email: event.target.value})}
                            />
                        </Grid.Column>
                        
                        <Grid.Column>
                            <Button
                            fluid
                                primary
                                onClick={this.onSendEmail}   
                                disabled={this.state.emailSent} 

                            >
                                Reset Password
                            </Button>
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
            </Layout>
        );
    }
}

export default ResetPassword;
