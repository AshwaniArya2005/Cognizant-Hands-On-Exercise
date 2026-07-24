import React from 'react';
import { books } from './data';

function BookDetails() {
  const bookdet = (
    <ul>
      {books.map((book) => (
        <li key={book.id}>
          <h3>{book.bname}</h3>
          <h4>{book.price}</h4>
        </li>
      ))}
    </ul>
  );

  return (
    <div className="details-block">
      <h1>Book Details</h1>
      {bookdet}
    </div>
  );
}

export default BookDetails;
