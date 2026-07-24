import React from 'react';

function ListofPlayers({ players }) {
  return (
    <div>
      {players.map((item) => {
        return (
          <div key={item.name}>
            <li>Mr. {item.name}<span> {item.score} </span></li>
          </div>
        );
      })}
    </div>
  );
}

export default ListofPlayers;
