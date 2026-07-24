import axios from 'axios';
import GitClient from './GitClient';

jest.mock('axios');

describe('Git Client Tests', () => {
  test('should return repository names for techiesyed', async () => {
    const mockRepos = [{ name: 'repo-one' }, { name: 'repo-two' }];
    axios.get.mockResolvedValue({ data: mockRepos });

    const gitClient = new GitClient();
    const repositoryNames = await gitClient.getRepositories('techiesyed');

    expect(repositoryNames).toEqual(['repo-one', 'repo-two']);
    expect(axios.get).toHaveBeenCalledWith('https://api.github.com/users/techiesyed/repos');
  });
});
