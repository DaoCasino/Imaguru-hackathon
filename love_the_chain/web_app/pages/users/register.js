import react, {Component} from 'react';
import Layout from '../../components/general/Layout';
import firebase from '../../config/firebase'
import {Form, Grid, Input, Button, Divider} from 'semantic-ui-react';
import axios from 'axios';
import { Router } from '../../routes';
import generalConfig from '../../config/generalConfig';
import StatusMessage from '../../components/general/StatusMessage'

class Register extends Component {


    state = {

        first_name: '',
        last_name: '',
        email: '',
        password:'',
        errorMessage: '',
    }

    
    onSubmit = async (event) => { 
            event.preventDefault();

            const ref = firebase.database().ref('love_the_chain').child('users');

            const result = await firebase.auth().createUserWithEmailAndPassword(this.state.email, this.state.password)
            .then( () => {
                // disable user
                const url = generalConfig.firebase_virtualization_rest_api + 'disableUser/'+firebase.auth().currentUser.uid;
                axios.put(url).then( res => {
                    console.log('PUT disableUser - from server: ',res.data);
                })

                // push record 
                var userPlusRecord = {
                    first_name: this.state.first_name,
                    last_name: this.state.last_name,
                    isAdministrator: false
                }
    
                ref.child(firebase.auth().currentUser.uid).set(userPlusRecord);

                firebase.auth().signOut();
                Router.pushRoute("/users/login");

            })
            .catch(function(error) {
                // Handle Errors here.
                //var errorCode = error.code;
                //this.setState({errorMessage: error.message}) ;
                console.log('errorMessage', error.message);
            });

            //console.log('result', result);

            
    }


    render () {
        return (
            <Layout>
                
                <h3>Register</h3>

                <Form onSubmit={this.onSubmit} error={!!this.state.errorMessage}>

                    <Form.Field>
                        <Input 
                            label={{ content: 'First name and last name' }}
                            value={this.state.first_name}
                            onChange={event =>
                                this.setState({first_name: event.target.value})}
                        />
                    </Form.Field>

                    <Form.Field>
                        <Input 
                            label={{ content: 'Email' }}
                            value={this.state.email}
                            onChange={event =>
                                this.setState({email: event.target.value})}
                        />
                    </Form.Field>

                    <Form.Field>
                        <Input 
                            type="password"
                            label={{ content: 'Password' }}
                            value={this.state.password}
                            onChange={event =>
                                this.setState({password: event.target.value})}
                        />
                    </Form.Field>

                    <StatusMessage
                        visible= {true}
                        contentMessage={'After registration you need to wait admin approval. Once approved an email will be send to you to verify your email address.'}
                    />
                    <Button
                        primary
                        fluid
                        margin="10px">
                        Ok, register!
                        </Button>
                    

                </Form>

            </Layout>
        );
    }
}

export default Register;
