import React from 'react';
import { useParams } from 'react-router-dom';
import trainersMock from './TrainersMock';

function TrainerDetail() {
  const { id } = useParams();
  const trainer = trainersMock.find((t) => t.TrainerId === id);

  if (!trainer) {
    return (
      <div>
        <h2>Trainers Details</h2>
        <p>No trainer found for id "{id}"</p>
      </div>
    );
  }

  return (
    <div>
      <h2>Trainers Details</h2>
      <p>
        <strong>
          {trainer.Name} ({trainer.Technology})
        </strong>
      </p>
      <p>{trainer.Email}</p>
      <p>{trainer.Phone}</p>
      <ul>
        {trainer.Skills.map((skill) => (
          <li key={skill}>{skill}</li>
        ))}
      </ul>
    </div>
  );
}

export default TrainerDetail;
