import React from 'react';
import { Menu, Image, Button, Icon, Grid } from 'semantic-ui-react';
import { Link } from '../../routes';


export default () => {

    return (
        <div>
            <br/>
        <Menu style={{ marginBottom: '15px' }}>
            <Menu.Item>
            <Link route="...">
                <a className="item"> 
                    
                <Button icon labelPosition='left'>
                    Menu item
                    <Icon name='left arrow' />
                </Button>
                
                </a>
            </Link>
            </Menu.Item>
            <Menu.Item position="right">
                <Link route="#">
                <a className="item"> 
                    <Button animated='vertical'>
                    <Button.Content hidden>Help</Button.Content>
                    <Button.Content visible>
                        <Icon name='help' />
                    </Button.Content>
                    </Button>
                </a>
                </Link>
            </Menu.Item>
        </Menu>
        </div>

    );
};