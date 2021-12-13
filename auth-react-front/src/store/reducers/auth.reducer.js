import { AuthActionTypes } from '@src/store/types';
import { StringUtils } from '@core/utils';

const initialState = {
  loggedIn: false,
  expiresAt: 0,
  language: 'en',
  accesstoken: null,
  routes: [],
};

export function authReducer(state = initialState, action) {
  switch (action.type) {
    case AuthActionTypes.LOGIN_SUCCESS: {
      let jwt = StringUtils.parseJSON(atob(action.payload.auth.access_token.split('.')[1]));
      return {
        ...state,
        loggedIn: true,
        accesstoken: action.payload.auth.access_token,
        expiresAt: jwt.exp
      };
    }
    case AuthActionTypes.LOGOUT:
    case AuthActionTypes.LOGIN_FAILURE:
      return {
        ...state,
        loggedIn: false,
        expiresAt: 0,
        accesstoken: null,
        routes: [],
      };
    case AuthActionTypes.SET_LANGUAGE:
      return {
        ...state,
        language: action.payload.language
      };
    case AuthActionTypes.SET_MENU_LIST:
      return {
        ...state,
        routes: action.payload.routes ? action.payload.routes : []
      };
    default:
      return state
  }
}
