import react, {Component} from 'react';
import firebase from '../../config/firebase';
import Layout from '../../components/general/Layout';
import StatusMessage from '../../components/general/StatusMessage';
import {Button} from 'semantic-ui-react';

class SendVerificationEmail extends Component {

    state = {
        textMessage: 'You have to confirm your email address to access to Hype.estate',
        emailSent: false
    }

    onSendEmail = async (event) => {
        firebase.auth().currentUser.sendEmailVerification()
        .then( () => {
            this.setState({
                textMessage: 'Verification email sent again',
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
                <h3>Email not yet verified</h3>
                <StatusMessage
                    visible={true}
                    contentMessage={this.state.textMessage}
                />
                <center>
                <Button
                    primary
                    onClick={this.onSendEmail}   
                    disabled={this.state.emailSent} 

                >
                    Send verification email 
                </Button>
                </center>
            </Layout>
        );
    }
}

export default SendVerificationEmail;
