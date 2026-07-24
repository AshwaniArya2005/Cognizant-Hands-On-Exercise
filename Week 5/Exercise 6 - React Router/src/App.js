import React from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Home from './Home';
import TrainersList from './TrainersList';
import TrainerDetail from './TrainerDetail';
import trainersMock from './TrainersMock';

function App() {
  return (
    <BrowserRouter>
      <div className="app-header">
        <h1>My Academy Trainers App</h1>
      </div>
      <div className="app-nav">
        <Link to="/">Home</Link> | <Link to="/trainers">Show Trainers</Link>
      </div>
      <div className="app-content">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/trainers" element={<TrainersList trainers={trainersMock} />} />
          <Route path="/trainers/:id" element={<TrainerDetail />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
