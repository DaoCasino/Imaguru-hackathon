import react, {Component} from 'react';
import Layout from '../../components/general/Layout';
import {Label} from 'semantic-ui-react';
import firebase from '../../config/firebase';
import ErrorMessage from '../../components/general/ErrorMessage';



class ChangeEmailOrPassword extends Component {
    
    static async getInitialProps(props) {

        let canAccess = false;
    
        if (firebase.auth().currentUser != undefined)
            canAccess = true;
        
        return {canAccess};
    
    }
    
    
    render() {
        if (!this.props.canAccess)
        return (
            <Layout>
                <ErrorMessage 
                    visible={true}
                    contentMessage='Sorry, you are not authorized to wiew this page!'
                />
            </Layout>
        );
        else return(
            <Layout>
                <h3>Change email or password</h3>
            </Layout>
        );
    }
}

export default ChangeEmailOrPassword;