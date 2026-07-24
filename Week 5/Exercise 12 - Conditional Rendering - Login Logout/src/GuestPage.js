import React from 'react';
import flights from './flights';

function GuestPage(props) {
  return (
    <div>
      <h2>Please sign up.</h2>
      <table className="flight-table">
        <thead>
          <tr>
            <th>Origin</th>
            <th>Destination</th>
            <th>Price ($)</th>
          </tr>
        </thead>
        <tbody>
          {flights.map((flight) => (
            <tr key={flight.id}>
              <td>{flight.origin}</td>
              <td>{flight.destination}</td>
              <td>{flight.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={props.onLogin}>Login</button>
    </div>
  );
}

export default GuestPage;
