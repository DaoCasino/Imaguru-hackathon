import {Component} from "react";
import {Message} from 'semantic-ui-react';

class StatusMessage extends Component{
    
    render() {
        if (this.props.visible)
        {
            return (
                <Message
                    error 
                    header="Oops!!!" 
                    content={this.props.contentMessage}
                />
            );
        }
        else 
        {
            return (
                <div></div>
            );

        }
    }
}

export default StatusMessage;