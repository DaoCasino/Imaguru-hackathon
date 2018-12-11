import React, {Component} from 'react';
import { Menu, Button, Icon } from 'semantic-ui-react';
import { Link, Router } from '../../routes';
import firebase from '../../config/firebase'



class AdminMenu extends Component {
   
    onHomeClick = async (event) => {
        console.log('onProfileClick called');
        Router.pushRoute("/");
    }

    render ()  {

        return (
            <div>
            <br/>
            
            <Menu style={{ marginBottom: '10px' }}>

                
                <Menu.Item>
                    <Button basic icon labelPosition='left'>
                        Users
                        <Icon name="user" />
                    </Button>
                </Menu.Item>

                <Menu.Item >
                   
                    <Button basic icon labelPosition='left'>
                        Create House
                        <Icon name="home" />
                    </Button>

                </Menu.Item>

                <Menu.Item>
                    <Button basic icon labelPosition='left'>
                        Transfer
                        <Icon name="eye" />
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

export default AdminMenu;