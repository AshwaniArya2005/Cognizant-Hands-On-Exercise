import { useEffect, useState } from 'react';
import GitClient from './GitClient';

function App() {
  const [repositories, setRepositories] = useState([]);

  useEffect(() => {
    const gitClient = new GitClient();
    gitClient.getRepositories('techiesyed').then((names) => setRepositories(names));
  }, []);

  return (
    <div className="App">
      <h1>Git repositories of User - TechieSyed</h1>
      <ul>
        {repositories.map((name) => (
          <li key={name}>{name}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;
