import React from 'react';
import { courses } from './data';

function CourseDetails() {
  return (
    <div className="details-block">
      <h1>Course Details</h1>
      <ul>
        {courses.map((course) => (
          <li key={course.id}>
            <h3>{course.cname}</h3>
            <h4>{course.date}</h4>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default CourseDetails;
