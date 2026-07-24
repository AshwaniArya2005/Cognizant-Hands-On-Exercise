import React from 'react';

function ListofIndianPlayers({ IndianPlayers }) {
  return (
    <div>
      {IndianPlayers.map((item) => {
        return <li key={item}>Mr. {item}</li>;
      })}
    </div>
  );
}

export default ListofIndianPlayers;
