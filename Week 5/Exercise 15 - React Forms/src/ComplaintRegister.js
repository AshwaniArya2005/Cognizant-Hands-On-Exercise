import React, { Component } from 'react';

class ComplaintRegister extends Component {
  constructor(props) {
    super(props);
    this.state = {
      ename: '',
      complaint: ''
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  }

  handleSubmit(event) {
    event.preventDefault();
    const referenceNumber = 'REF' + Math.floor(Math.random() * 1000000);
    const msg =
      'Complaint registered.\nReference Number: ' + referenceNumber;
    alert(msg);
    this.setState({ ename: '', complaint: '' });
  }

  render() {
    return (
      <div className="complaint-container">
        <h1>Register your complaints here!!!</h1>
        <form onSubmit={this.handleSubmit}>
          <div className="form-row">
            <label htmlFor="ename">Name:</label>
            <input
              type="text"
              id="ename"
              name="ename"
              value={this.state.ename}
              onChange={this.handleChange}
            />
          </div>
          <div className="form-row">
            <label htmlFor="complaint">Complaint:</label>
            <textarea
              id="complaint"
              name="complaint"
              rows="4"
              value={this.state.complaint}
              onChange={this.handleChange}
            />
          </div>
          <button type="submit">Submit</button>
        </form>
      </div>
    );
  }
}

export default ComplaintRegister;
