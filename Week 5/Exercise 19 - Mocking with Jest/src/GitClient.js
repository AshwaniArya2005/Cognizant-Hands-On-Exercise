import axios from 'axios';

class GitClient {
  getRepositories(username) {
    const url = `https://api.github.com/users/${username}/repos`;
    return axios.get(url).then((response) => response.data.map((repo) => repo.name));
  }
}

export default GitClient;
