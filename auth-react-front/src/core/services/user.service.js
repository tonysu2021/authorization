import environments from 'environments';
import { $axios } from '../interceptor/jwt.interceptor';

const USER_PREFIX = 'user';
/**
 * 登入相關API
 * 
 * @author Tony.su
 * 
 */
class UserService {
    findAll() {
        return $axios.get(`${environments.apiHost}/${USER_PREFIX}`)
            .then((response) => {
                return response?.data;
            });
    }
}

export default new UserService();