import React from 'react';

class CurrencyConvertor extends React.Component {
  constructor(props) {
    super(props);
    this.state = { amount: '', currency: '' };
    this.handleAmountChange = this.handleAmountChange.bind(this);
    this.handleCurrencyChange = this.handleCurrencyChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleAmountChange(event) {
    this.setState({ amount: event.target.value });
  }

  handleCurrencyChange(event) {
    this.setState({ currency: event.target.value });
  }

  handleSubmit() {
    const conversionRate = 80;
    const convertedAmount = Number(this.state.amount) * conversionRate;
    alert('Converting to ' + this.state.currency + ' Amount is ' + convertedAmount);
  }

  render() {
    return (
      <div>
        <h1 style={{ color: 'green' }}>Currency Convertor!!!</h1>
        <div>
          <label>Amount: </label>
          <input type="text" value={this.state.amount} onChange={this.handleAmountChange} />
        </div>
        <div>
          <label>Currency: </label>
          <input type="text" value={this.state.currency} onChange={this.handleCurrencyChange} />
        </div>
        <button onClick={this.handleSubmit}>Submit</button>
      </div>
    );
  }
}

export default CurrencyConvertor;
