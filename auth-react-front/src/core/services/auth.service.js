import environments from 'environments';
import { $axios } from '../interceptor/jwt.interceptor';
import { DateUtils } from '@core/utils';

const OAUTH_PREFIX = 'oauth';

/**
 * 登入相關API
 * 
 * @author Tony.su
 * 
 */
class AuthService {

    /** 
     * 使用者一般登入
     * @param {String} userId 使用者帳號
     * @param {String} password 使用者密碼
     * @returns {User}
    */
    login(userId, password) {
        return $axios
            .post(`${environments.apiHost}/${OAUTH_PREFIX}/token?grant_type=password&username=${userId}&password=${password}&scope=read`, null, {
                auth: {
                    username: environments.clientId,
                    password: environments.clientSecret
                }
            })
            .then((response) => {
                return response?.data;
            });
    }

    logout() {

    }

    register(registerUserReq) {
        return $axios.post(`${environments.apiHost}/${OAUTH_PREFIX}/register`, {
            userAccount: registerUserReq.userAccount,
            password: btoa(registerUserReq.password),
            userChName: registerUserReq.userChName,
            userEngName: registerUserReq.userEngName,
            userNickName: registerUserReq.userNickName,
            inviteId: registerUserReq.inviteId,
            roleId: registerUserReq.roleId,
            perMissionId: registerUserReq.perMissionId,
            lang: "zh-Hant",
        });
    }

    /** 
     * 查詢使用者Menu選單。
     * 
    */
    getMenuList(userId) {
        // TODO 補真正撈menu的API
        return new Promise((resolve, reject) => {
            const routes = [
                '/home',
                '/myset',
                '/set',
                '/sse',
                '/level1/level22/level31',
            ]
            setTimeout(function () {
                resolve(routes);
            }, 300);
        });
    }

    /** 
     * Get Token duration
     * 
     * @returns duration(unit:sec)
     * 
    */
    getTokenDuration(expireTimeEpoch) {
        let currentTimeEpoch = DateUtils.getTimeEpoch(new Date());
        let expireDuration = expireTimeEpoch - currentTimeEpoch;
        let safetyInterval = environments.minutesToRefresh * 60;
        let refreshDuration = expireDuration - safetyInterval;
        console.log('Expire Token Duration:', expireDuration, ' Refresh Token Duration:', refreshDuration);
        return { expireDuration, refreshDuration };
    }
}

export default new AuthService();
