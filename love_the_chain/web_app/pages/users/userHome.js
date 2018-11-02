import react, {Component} from 'react';

import Layout from '../../components/general/Layout';
import firebase from '../../config/firebase';

import {Router} from '../../routes';


class UserHome extends Component {

    
    componentDidMount() 
    {
        if (firebase.auth().currentUser == undefined) {
            Router.pushRoute("/users/login");
        }
        
        else if (firebase.auth().currentUser.isAdministrator)
            Router.pushRoute("/admin/users");
        
        else
            Router.pushRoute("/users/details/"+firebase.auth().currentUser.uid);
    }
    

    render() {
        return(
            <Layout>
                <center>
                <h4>loading...</h4>  
                </center>
            </Layout>
        );
    }
}

export default UserHome;