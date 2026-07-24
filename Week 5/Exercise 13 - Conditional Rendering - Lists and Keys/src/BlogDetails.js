import React from 'react';
import { blogs } from './data';

function BlogDetails() {
  return (
    <div className="details-block">
      <h1>Blog Details</h1>
      <ul>
        {blogs.map((blog) => (
          <li key={blog.id}>
            <h2>{blog.title}</h2>
            <h4>{blog.author}</h4>
            <p>{blog.content}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default BlogDetails;
