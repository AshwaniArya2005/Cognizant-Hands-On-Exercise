import React from 'react';
import Cart from './Cart';

class OnlineShopping extends React.Component {
  constructor(props) {
    super(props);
    this.items = [
      new Cart('Laptop', 80000),
      new Cart('TV', 120000),
      new Cart('Washing Machine', 50000),
      new Cart('Mobile', 30000),
      new Cart('Fridge', 70000)
    ];
  }

  render() {
    return (
      <div className="shopping-container">
        <h1>Items Ordered :</h1>
        <ul className="shopping-list">
          {this.items.map((item) => (
            <li key={item.Itemname}>
              <span>{item.Itemname}</span>
              <span>{item.Price}</span>
            </li>
          ))}
        </ul>
      </div>
    );
  }
}

export default OnlineShopping;
