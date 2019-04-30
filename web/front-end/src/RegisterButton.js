import React from 'react';
import Button from 'react-bootstrap/Button';

const divStyle = {
    margin: '5px',
  };

class RegisterButton extends React.Component{

    constructor(props){
        super(props);

    }

    

    render(){
        return(
            <Button variant={this.props.style} style="submit" className="register-button" style={divStyle}>
                {this.props.name}
            </Button>
        );
    }
}

export default RegisterButton;
