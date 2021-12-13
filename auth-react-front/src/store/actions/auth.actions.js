import { AuthActionTypes } from '@src/store/types';
import AuthService from "@core/services/auth.service";
import i18n from '@src/core/config/i18n';
import { history } from '@core/helpers';
import regeneratorRuntime from "regenerator-runtime";


const loginSuccess = (auth) => {
    return async dispatch => {
        // 使用者資訊
        dispatch({ type: AuthActionTypes.LOGIN_SUCCESS, payload: { auth } });
        // 取的可使用的menu
        await dispatch(authActions.getMenuList(""));
        // 導頁至Home
        history.push('/');
    };

}

const loginFailure = (error) => {
    return { type: AuthActionTypes.LOGIN_FAILURE, payload: { error } }
}

const logout = () => {
    return { type: AuthActionTypes.LOGOUT, payload: {} };
}

const setLanguage = (language) => {
    i18n.changeLanguage(language)
    return { type: AuthActionTypes.SET_LANGUAGE, payload: { language } };
}

const getMenuList = (userid) => {
    return async dispatch => {
        const menuList  = await AuthService.getMenuList(userid)
        dispatch({ type: AuthActionTypes.SET_MENU_LIST, payload: { routes: menuList } });
       
    };
}

export const authActions = {
    loginSuccess,
    loginFailure,
    logout,
    setLanguage,
    getMenuList,
};
