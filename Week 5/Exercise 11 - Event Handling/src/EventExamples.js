import React from 'react';

class EventExamples extends React.Component {
  constructor(props) {
    super(props);
    this.state = { count: 0 };
    this.increment = this.increment.bind(this);
    this.decrement = this.decrement.bind(this);
    this.sayWelcome = this.sayWelcome.bind(this);
    this.onPress = this.onPress.bind(this);
  }

  incrementValue() {
    this.setState({ count: this.state.count + 1 });
  }

  sayHello() {
    alert('Hello! Member1');
  }

  increment() {
    this.incrementValue();
    this.sayHello();
  }

  decrement() {
    this.setState({ count: this.state.count - 1 });
  }

  sayWelcome(message) {
    alert(message);
  }

  onPress(event) {
    alert('I was clicked');
  }

  render() {
    return (
      <div>
        <h2>{this.state.count}</h2>
        <button onClick={this.increment}>Increment</button>
        <br />
        <button onClick={this.decrement}>Decrement</button>
        <br />
        <button onClick={() => this.sayWelcome('welcome')}>Say welcome</button>
        <br />
        <button onClick={this.onPress}>Click on me</button>
      </div>
    );
  }
}

export default EventExamples;
