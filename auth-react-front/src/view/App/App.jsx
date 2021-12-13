import React from 'react';
import { Router, Route, Switch, Redirect } from 'react-router-dom';
import { history } from '@core/helpers';
import { useDispatch } from '@store/store';
import { AuthGuardRoute } from '@core/guard';
import { LoginPage } from '@view/LoginPage';
import { RegisterPage } from '@view/RegisterPage';
import { RouteManager } from '@core/config/route'
import ReduxBlockUi from 'react-block-ui/redux';
import 'react-block-ui/style.css';
import { AlertActionType } from '@store/types/alert-action.type'
import { authActions } from "@store/actions";
import { AccessDeniedPage } from '@view/AccessDeniedPage';
import { MenuPage } from '@view/MenuPage';

/**
 * 第一層路由
 * 
 * @author Tony.su
 * 
 */
export const App = () => {
    const dispatch = useDispatch();
    /**
     * I18切換語言
     *
     * */
    const testI18 = () => {
        dispatch(authActions.setLanguage('cn'));
    }
    const allRoutes = RouteManager.allRouts();
    history.listen(() => {
        // clear alert on location change
        // dispatch(alertActions.clear());
    });

    return (
        <>
            {/*{alert.message &&*/}
            {/*    <div className={`alert ${alert.type}`}>{alert.message}</div>*/}
            {/*}*/}
            {/*<SelectLng value={defLng} onChange={changeLanguage} />*/}
            <ReduxBlockUi tag="div" block={AlertActionType.API_LOADING} unblock={[AlertActionType.API_SUCCESS, AlertActionType.API_ERROR, /fail/i]}>
                <Router history={history}>
                    <Switch>
                        <AuthGuardRoute exact path="/" component={MenuPage} />
                        <Route path="/login" component={LoginPage} />
                        <Route path="/register" component={RegisterPage} />
                        <Route path="/403" component={AccessDeniedPage} />
                        {allRoutes.map((route, index) => (
                            <AuthGuardRoute
                                key={index}
                                path={route.path}
                                component={MenuPage}
                            />
                        ))}
                        <Redirect from="*" to="/" />
                    </Switch>
                </Router>
            </ReduxBlockUi>
            {/*<Button onClick={testI18}>TestI18n</Button>*/}
        </>
    )
}
