/*eslint no-undef: "error"*/
/*eslint-env node*/

import { store } from '@store/store';
import { alertActions } from '@store/actions'
import AuthService from "@core/services/auth.service";

const axios = require('axios');
export const $axios = axios.create();

// Request interceptor for API calls
$axios.interceptors.request.use(
    config => {
        store.dispatch(alertActions.apiLoading());
        console.log(config.url);
        console.log(config.url.includes('oauth'));
        if (config.url.includes('oauth')) {
            // Do nothing for login
            return config;
        }
        let isRefreshing = false;
        const state = store.getState();
        const auth = state.authentication;
        console.log(auth.accesstoken);
        if (auth.accesstoken) {
            let {
                expireDuration,
                refreshDuration,
            } = AuthService.getTokenDuration(auth.expiresAt);
            // Add Token TODO: 加上去會出問題?
            config.headers.Authorization =  auth.accesstoken;
            if (expireDuration < 0) {
                return false;
            }
            if (refreshDuration < 0 && !isRefreshing) {
                // Do nothing for login
            }
        }
        return config;
    },
    error => {
        Promise.reject(error)
    });

// Response interceptor for API calls
$axios.interceptors.response.use((response) => {


    store.dispatch(alertActions.apiSuccess());
    return response
}, function (error) {
    // [Disabled] Future system use refresh token
    // const originalRequest = error.config;
    // if (error.response && error.response.status === 403 && !originalRequest._retry) {
    //     originalRequest._retry = true;
    //     const access_token = refreshAccessToken();
    //     axios.defaults.headers.common['Authorization'] = 'Bearer ' + access_token;
    //     return $axios(originalRequest);
    // }
    store.dispatch(alertActions.apiError(''));
    return Promise.reject(error);
});
