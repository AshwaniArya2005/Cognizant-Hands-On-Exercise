import React from 'react';

function Scorebelow70({ players }) {
  const players70 = players.filter((item) => item.score <= 70);

  return (
    <div>
      {players70.map((item) => {
        return (
          <div key={item.name}>
            <li>Mr. {item.name}<span> {item.score} </span></li>
          </div>
        );
      })}
    </div>
  );
}

export default Scorebelow70;
