import React from 'react';
import flights from './flights';

function UserPage(props) {
  const handleBook = (flight) => {
    alert('Ticket booked from ' + flight.origin + ' to ' + flight.destination);
  };

  return (
    <div>
      <h2>Welcome back</h2>
      <table className="flight-table">
        <thead>
          <tr>
            <th>Origin</th>
            <th>Destination</th>
            <th>Price ($)</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {flights.map((flight) => (
            <tr key={flight.id}>
              <td>{flight.origin}</td>
              <td>{flight.destination}</td>
              <td>{flight.price}</td>
              <td>
                <button onClick={() => handleBook(flight)}>Book</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={props.onLogout}>Logout</button>
    </div>
  );
}

export default UserPage;
