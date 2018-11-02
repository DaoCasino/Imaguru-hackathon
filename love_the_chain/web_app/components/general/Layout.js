import React from 'react';
import {Container} from 'semantic-ui-react';
import Head from 'next/head';
import Header from './Header';
import UserMenu from '../../components/general/userMenu';
import {Router} from '../../routes';

export default props => {
    return (
        <Container>
            <center><img src="http://www.lovethechain.com/lovethechain.jpg"></img></center>
            
            <Head>    
                <link 
                    rel="stylesheet" 
                    href="//cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.1/semantic.min.css">
                </link>
            </Head>

            <UserMenu />
            {props.children}
        </Container>

    );
}