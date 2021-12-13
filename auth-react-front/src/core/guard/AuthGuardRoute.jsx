import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { useSelector } from '@store/store';
import { queryAuth } from "@store/selectors";
import PropTypes from 'prop-types';

/** 
 * 路由守衛(Route Guards)檢驗使用者是否登入過
 * 
 * @author Tony.su
*/
export const AuthGuardRoute = ({ component: Component, ...rest }) => {
    const auth = useSelector(queryAuth);

    // 驗證是否能瀏覽目前頁面, 並判斷預設頁面
    const menuList = useSelector(state => state.authentication.routes);
    let isActive = menuList.includes(rest.path);
    let defPage = "/login";
    if (menuList && menuList.length > 0) {
        if (!rest.path || rest.path === '/') {
            isActive = true;
            defPage = "/home";
        } else {
            defPage = "/403";
        }
    }
    return (
        <Route {...rest} render={(props) => (
            isActive && auth && auth.accesstoken
                ? <Component {...props} />
                : <Redirect to={{ pathname: defPage, state: { from: props.location } }} />
        )} />
    )
};

AuthGuardRoute.propTypes = {
    component: PropTypes.func,
    location: PropTypes.object
}
