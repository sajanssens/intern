import React from 'react';
import Button from 'react-bootstrap/Button';
import RegisterForm from './RegisterForm'

const divStyle = {
    margin: '5px',
};

class Home extends React.Component {
 
    handleClick() {
        console.log("click");

    }


    render() {
        return (
            <div>
                <Button style={divStyle} onClick={(e) => this.handleClick()}>Registreren</Button>
                <Button style={divStyle} onClick={(e) => this.handleClick()}>Bijleren</Button>

            </div >
        );
    }
}

export default Home;
