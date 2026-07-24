import React from 'react';

class CountPeople extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      entrycount: 0,
      exitcount: 0
    };
    this.UpdateEntry = this.UpdateEntry.bind(this);
    this.UpdateExit = this.UpdateExit.bind(this);
  }

  UpdateEntry() {
    this.setState((prevState) => {
      return { entrycount: prevState.entrycount + 1 };
    });
  }

  UpdateExit() {
    this.setState((prevState) => {
      return { exitcount: prevState.exitcount + 1 };
    });
  }

  render() {
    return (
      <div className="counter-container">
        <div>
          <button onClick={this.UpdateEntry}>Login</button>
          {this.state.entrycount} People Entered!!!
        </div>
        <div>
          <button onClick={this.UpdateExit}>Exit</button>
          {this.state.exitcount} People Left!!!
        </div>
      </div>
    );
  }
}

export default CountPeople;
