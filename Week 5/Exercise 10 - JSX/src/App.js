import React from 'react';
import logo from './logo.svg';

const element = 'Office Space';

const jsxattr = <img src={logo} width="25%" height="25%" alt="Office Space" />;

const ItemName = { Name: 'DBS', Rent: 50000, Address: 'Chennai' };

const officeList = [
  { Name: 'DBS', Rent: 50000, Address: 'Chennai' },
  { Name: 'TCS', Rent: 65000, Address: 'Bangalore' },
  { Name: 'Infosys', Rent: 55000, Address: 'Pune' },
  { Name: 'Wipro', Rent: 72000, Address: 'Hyderabad' }
];

function getRentColor(rent) {
  return rent <= 60000 ? 'red' : 'green';
}

function App() {
  return (
    <div>
      <h1>{element} , at Affordable Range </h1>
      {jsxattr}
      <h1>Name: {ItemName.Name}</h1>
      <h3 style={{ color: getRentColor(ItemName.Rent) }}> Rent: Rs. {ItemName.Rent}</h3>
      <h3> Address: {ItemName.Address}</h3>
      <hr />
      <h1>More Office Spaces</h1>
      {officeList.map((item) => {
        return (
          <div key={item.Name}>
            <h2>Name: {item.Name}</h2>
            <h3 style={{ color: getRentColor(item.Rent) }}> Rent: Rs. {item.Rent}</h3>
            <h3> Address: {item.Address}</h3>
            <hr />
          </div>
        );
      })}
    </div>
  );
}

export default App;
