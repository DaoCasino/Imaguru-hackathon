import react, {Component} from 'react';
import Layout from '../../components/general/Layout';
import ErrorMessage from '../../components/general/ErrorMessage';
import {Form, Grid, Input, Button, Message} from 'semantic-ui-react';
import firebase from '../../config/firebase';
import { Router } from '../../routes';

class Login extends Component {

    state = {
        email: '',
        password: '',
        loading: false,
        errorMessage: '',
        
    }


    onLogin = async (event) => { 
        event.preventDefault();

        this.setState({
            loading: true, 
            errorMessage: ''
        });

        await firebase.auth().signInWithEmailAndPassword(this.state.email, this.state.password)
        .then( () => {
            if (!firebase.auth().currentUser.disabled)
            {
                console.log('user enabled');
                console.log('firebase.auth().currentUser.emailVerified',firebase.auth().currentUser.emailVerified)
                if (firebase.auth().currentUser.emailVerified) {
                    console.log("before pushing");
                    Router.pushRoute("/");
                    console.log("after pushing");
                }
                else    
                    Router.pushRoute("/users/sendVerificationEmail/"+firebase.auth().currentUser.uid);
            }
            else    
                console.log('user disabled');
        })
        .catch(error => {
            this.setState({errorMessage: error.message})
        });
        this.setState({loading: false});
        
    }

    goToRegisterPage = async (event) => {
        Router.pushRoute("/users/register");
    }

    resetPassword = async (event) => {
        Router.pushRoute("/users/resetPassword");                
    }

    render() {
        return(
            <Layout>
                <h3>Login</h3>
                <Form onSubmit={this.onLogin} >   
                    <Grid>
                        <Grid.Row columns={2}>

                            <Grid.Column>
                                <Form.Field>
                                    <Input 
                                        label={{ content: 'Email' }}
                                        value={this.state.email}
                                        onChange={event =>
                                            this.setState({email: event.target.value})}
                                    />
                                </Form.Field>
                            </Grid.Column>

                            <Grid.Column>
                                <Form.Field>
                                    <Input 
                                        type="password"
                                        label={{ content: 'Password' }}
                                        value={this.state.password}
                                        onChange={event =>
                                            this.setState({password: event.target.value})}
                                    />
                                </Form.Field>
                            </Grid.Column>

                        </Grid.Row>
                    </Grid>
                    <br/>
                    <Button
                        primary
                        fluid
                        loading={this.state.loading}
                        disabled={this.state.loading}
                        margin="10px">
                        Login
                        
                        </Button>
                    

                </Form>
                <ErrorMessage
                    visible={this.state.errorMessage!=''} 
                    contentMessage={this.state.errorMessage} 
                /> 
                <br/>
                <center>
                <Button
                    basic
                    fluid
                    onClick={this.goToRegisterPage}
                >
                    Not yet registered?
                </Button>
                </center>

                <br/>
                <center>
                <Button
                    basic
                    fluid
                    onClick={this.resetPassword}
                >
                    Forgot your password?
                </Button>
                </center>

            </Layout>


        );
    }
}

export default Login;