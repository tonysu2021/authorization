import React, { useEffect } from 'react'
import { Switch, BrowserRouter as Router, Redirect } from 'react-router-dom'
import { makeStyles } from '@material-ui/core/styles'
import { Menu } from "@component/Menu";
import logo from "@material-asset/img/reactlogo.png";
import { ErrorBoundary } from "react-error-boundary";
import { ErrorFallback } from "@core/hander";
import styles from "@material-asset/jss/material-dashboard-react/layouts/adminStyle.js";
import PerfectScrollbar from "perfect-scrollbar";
import { AuthGuardRoute } from "@core/guard";
import { RouteManager } from "@core/config/route"

const useStyles = makeStyles(styles);
const switchRoutes = (routes) => {
    const getRedirect = (routes) => {
        let redirect = null;
        if (routes && routes.length > 0) {
            const route = routes[0];
            redirect = <Redirect from="/" to={route.path} />
        }
        return redirect;
    }
    return (
        <Switch>
            {routes.map((route, index) => (
                <AuthGuardRoute
                    key={index}
                    path={route.path}
                    exact={route.exact}
                    component={route.component}
                />
            ))}
            {getRedirect(routes)}
        </Switch>
    );
};
let ps;

export const MenuPage = () => {
    const classes = useStyles()
    const mainPanel = React.createRef();
    const [mobileOpen, setMobileOpen] = React.useState(false);
    const userRouts = RouteManager.userRouts();
    const userMenus = RouteManager.userMenus();
    const routeNameMap = RouteManager.getRouteNameMap(userMenus);
    const handleDrawerToggle = () => {
        setMobileOpen(!mobileOpen);
    };
    const resizeFunction = () => {
        if (window.innerWidth >= 960) {
            setMobileOpen(false);
        }
    };

    useEffect(() => {
        if (navigator.platform.indexOf("Win") > -1) {
            ps = new PerfectScrollbar(mainPanel.current, {
                suppressScrollX: true,
                suppressScrollY: false
            });
            document.body.style.overflow = "hidden";
        }
        window.addEventListener("resize", resizeFunction);
        // Specify how to clean up after this effect:
        return function cleanup() {
            if (navigator.platform.indexOf("Win") > -1) {
                ps.destroy();
            }
            window.removeEventListener("resize", resizeFunction);
        };
    }, [mainPanel]);

    return (
        <Router>
            <div className={classes.wrapper}>
                <Menu
                    logoText={"後臺管理系統"}
                    logo={logo}
                    userMenus={userMenus}
                    handleDrawerToggle={handleDrawerToggle}
                    open={mobileOpen}
                />
                <div className={classes.mainPanel} ref={mainPanel}>
                    <div className={classes.content}>
                        <ErrorBoundary FallbackComponent={ErrorFallback}>
                            {switchRoutes(userRouts)}
                        </ErrorBoundary>
                    </div>
                </div>
            </div>
        </Router>
    )
}
