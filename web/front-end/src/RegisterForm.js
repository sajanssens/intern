import React from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';

const divStyle = {
    margin: '5px',
};

class RegisterForm extends React.Component {

    // state = {
    //     name: '',
    // }

    constructor(props) {
        super(props)
        this.state = { name: '' }
    }

    handleChange(e) {
        this.setState({ name: e.target.value });
    }

    handleSubmit(e) {
        e.preventDefault();
        console.log(this.state.name);

        const person = {
            name: this.state.name
        };


        // const axios = require('axios');

        // axios.post('http://localhost:8080/person/register',
        //     {name : person.name})
        //     .then(response => {
        //         console.log(response.data.url);
        //         console.log(response.data.explanation);
        //     })
        //     .catch(error => {
        //         console.log("an error");
        //         console.log(error);
        //     });
    }

    render() {
        return (

            <Form onSubmit={(e)=>this.handleSubmit(e)}>
                <Form.Row className="justify-content-sm-center text-center">
                    <Col className="col-md-6">
                        <Form.Group controlId="name">
                            <Form.Label name="name">Vul uw naam in om te registreren.</Form.Label>
                            <Form.Control name="name" type="text" placeholder="Uw naam" onChange={(e) => this.handleChange(e)} />
                        </Form.Group>
                    </Col>
                </Form.Row>
                <Form.Row className="justify-content-sm-center text-center">
                    <Col className="col-sm-6">
                        <Button variant="secondary" style={divStyle}>Annuleren</Button>
                        <Button variant="primary" type="submit" style={divStyle}>Registreren</Button>
                    </Col>
                </Form.Row>
            </Form >
        );
    }
}

export default RegisterForm;