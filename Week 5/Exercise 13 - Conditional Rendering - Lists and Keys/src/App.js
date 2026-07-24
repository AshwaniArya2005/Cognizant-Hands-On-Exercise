import React, { useState } from 'react';
import BookDetails from './BookDetails';
import BlogDetails from './BlogDetails';
import CourseDetails from './CourseDetails';

function renderBookSection(showBooks) {
  if (showBooks) {
    return <BookDetails />;
  } else {
    return <p>Book details are hidden.</p>;
  }
}

function App() {
  const [showBooks, setShowBooks] = useState(true);
  const [showBlogs, setShowBlogs] = useState(true);
  const [showCourses, setShowCourses] = useState(true);

  let courseSection;
  if (showCourses) {
    courseSection = <CourseDetails />;
  } else {
    courseSection = <p>Course details are hidden.</p>;
  }

  return (
    <div className="app-container">
      <h1>Blogger App</h1>
      <div className="toggle-bar">
        <button onClick={() => setShowBooks(!showBooks)}>Toggle Books (if/else)</button>
        <button onClick={() => setShowBlogs(!showBlogs)}>Toggle Blogs (ternary)</button>
        <button onClick={() => setShowCourses(!showCourses)}>Toggle Courses (element variable)</button>
      </div>

      <div className="section">{renderBookSection(showBooks)}</div>

      <div className="section">{showBlogs ? <BlogDetails /> : <p>Blog details are hidden.</p>}</div>

      <div className="section">{courseSection}</div>

      <div className="section">
        {showBooks && showBlogs && <p>Both books and blogs are currently visible.</p>}
      </div>
    </div>
  );
}

export default App;
