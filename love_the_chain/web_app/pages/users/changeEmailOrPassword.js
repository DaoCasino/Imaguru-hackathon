import react, {Component} from 'react';
import Layout from '../../components/general/Layout';
import {Grid, Button, Input, Form} from 'semantic-ui-react';
import firebase from '../../config/firebase';
import ErrorMessage from '../../components/general/ErrorMessage';
import StatusMessage from '../../components/general/StatusMessage';



class ChangeEmailOrPassword extends Component {


    state = {
        newEmail: '',
        newPassword: '',
        statusMessage: '',
        errorMessage: ''
    }
    
    static async getInitialProps(props) {

        let canAccess = false;
    
        if (firebase.auth().currentUser != undefined) {
            canAccess = true;

            const userKey = props.query.userkey;
            const ref = firebase.database().ref('love_the_chain').child('users');
            var item;
    
            await ref.orderByKey().equalTo(userKey).once('value').then(function(snapshot) {
                item = snapshot.val()[userKey];
            });

            return {canAccess, item};
        
        }
        else 
            return {canAccess}
    
    }

    onChangeEmail = async (event) => {
        event.preventDefault();
        this.setState({
            statusMessage: '',
            errorMessage: ''
        })

        firebase.auth().currentUser.updateEmail(this.state.newEmail)
        .then ( () => {
            this.setState({statusMessage: 'email changed'});
        })
        .catch ( (err) => {
            this.setState({errorMessage: err.message});
        });

    }


    onChangePassword = async (event) => {
        event.preventDefault();

        this.setState({
            statusMessage: '',
            errorMessage: ''
        })


        firebase.auth().currentUser.updatePassword(this.state.newPassword)
        .then ( () => {
            this.setState({statusMessage: 'Password changed'});
        })
        .catch ( (err) => {
            this.setState({errorMessage: err.message});
        });

    }
    
    
    render() {
        if (!this.props.canAccess)
        return (
            <Layout>
                <h3>Change email or password</h3>
                <ErrorMessage 
                    visible={true}
                    contentMessage='Sorry, you are not authorized to wiew this page!'
                />
            </Layout>
        );
        else return(
            <Layout>
                <h3>Change email or password</h3>
                <Grid>
                    <Grid.Row columns = {2}>
                        <Grid.Column>
                            <Form onSubmit={this.onChangeEmail}>
                                <Form.Field>
                                    <Input 
                                        fluid
                                        label={{ content: 'New Email' }}
                                        value={this.state.newEmail}
                                        onChange={event =>
                                            this.setState({newEmail: event.target.value})}
                                    />
                                    <br/>
                                    <Button
                                        fluid
                                        primary
                                    >
                                        Change email
                                    </Button>
                                </Form.Field>
                            </Form>


                        </Grid.Column>

                        <Grid.Column>
                            <Form onSubmit={this.onChangePassword}>
                                <Form.Field>
                                    <Input 
                                        fluid
                                        type = "password"
                                        label={{ content: 'New Password' }}
                                        value={this.state.newPassword}
                                        onChange={event =>
                                            this.setState({newPassword: event.target.value})}
                                    />
                                    <br/>
                                    <Button
                                        fluid
                                        primary
                                    >
                                        Change password
                                    </Button>
                                </Form.Field>
                            </Form>


                        </Grid.Column>
                    </Grid.Row>
                </Grid>
                <StatusMessage
                    visible = {this.state.statusMessage!=''}
                    contentMessage = {this.state.statusMessage}
                />
                <ErrorMessage
                    visible = {this.state.errorMessage!=''}
                    contentMessage = {this.state.errorMessage}
                />
            </Layout>
        );
    }
}

export default ChangeEmailOrPassword;