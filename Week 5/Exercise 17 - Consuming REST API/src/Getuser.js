import { Component } from 'react';

class Getuser extends Component {
  constructor(props) {
    super(props);
    this.state = { user: null };
  }

  componentDidMount() {
    fetch('https://randomuser.me/api/')
      .then((response) => response.json())
      .then((data) => {
        this.setState({ user: data.results[0] });
      });
  }

  render() {
    const { user } = this.state;

    if (user === null) {
      return <div>Loading...</div>;
    }

    return (
      <div>
        <h1>
          {user.name.title} {user.name.first}
        </h1>
        <img src={user.picture.large} alt={user.name.first} />
      </div>
    );
  }
}

export default Getuser;
