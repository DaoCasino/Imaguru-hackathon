import React, {Component} from 'react';
import { Menu, Button, Icon } from 'semantic-ui-react';
import { Link, Router } from '../../routes';
import firebase from '../../config/firebase'



class UserMenu extends Component {
    state = {
        loginButtonLabel: '',
        loginButtonIcon: '',
        isLogged: false,
        profileUrl:''
    }

    
    componentDidMount() {
        if (firebase.auth().currentUser == undefined || !firebase.auth().currentUser.emailVerified)
        {
            this.setState({loginButtonLabel: 'login'})
            this.setState({loginButtonIcon: 'sign in'});
            this.setState({isLogged: false});
            this.setState({profileUrl: '/users/login'});
        }
        else {
            this.setState({loginButtonLabel: 'logout'})
            this.setState({loginButtonIcon: 'sign out'});
            this.setState({isLogged: true});
            this.setState({profileUrl: '/users/details/'+firebase.auth().currentUser.uid});
        }
    }

    
    

    onProfileClick = async (event) => {
        console.log('onProfileClick called');
        Router.pushRoute("/users/details/"+firebase.auth().currentUser.uid);
    }

    onHomeClick = async (event) => {
        console.log('onProfileClick called');
        Router.pushRoute("/");
    }

    onLoginClick = async (event) => {
        console.log('onProfileClick called');
        if (this.state.isLogged)
            firebase.auth().signOut();
        
        Router.pushRoute("/users/login");

    }



    render ()  {

        return (
            <div>
            <br/>
            
            <Menu style={{ marginBottom: '10px' }}>

                
                <Menu.Item>
                    <Button basic icon labelPosition='left'
                    onClick = {this.onLoginClick}>
                        {this.state.loginButtonLabel}
                        <Icon name={this.state.loginButtonIcon} />
                    </Button>
                </Menu.Item>

                <Menu.Item >
                   
                    <Button basic icon labelPosition='left' 
                        onClick={this.onProfileClick}
                        disabled={!this.state.isLogged}>
                        Profile
                        <Icon name="address book" />
                    </Button>

                </Menu.Item>

                <Menu.Item>
                    <Button basic icon labelPosition='left'
                    disabled={!this.state.isLogged}>
                        Investments
                        <Icon name="eye" />
                    </Button>
                </Menu.Item>
                
                <Menu.Item>
                    <Button basic icon labelPosition='left'
                    disabled={!this.state.isLogged}>
                        Incomes
                        <Icon name="dollar sign" />
                    </Button>
                </Menu.Item>

                <Menu.Item>
                    <Button basic icon labelPosition='left'
                    disabled={!this.state.isLogged}>
                        MarketPlace
                        <Icon name="shopping cart" />
                    </Button>
                </Menu.Item>


                <Menu.Item position="right">

                    <Button animated='vertical' onClick = {this.onHomeClick}>
                    <Button.Content hidden>Home</Button.Content>
                    <Button.Content visible>
                        <Icon name='home' />
                    </Button.Content>
                    </Button>

                </Menu.Item>

            </Menu>
            </div>

        )
    };
};

export default UserMenu;