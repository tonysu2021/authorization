// core components/views
import {HomePage} from "@view/HomePage";
import {MySetPage} from "@view/MySetPage";
import HomeIcon from '@material-ui/icons/Home';
import SettingsIcon from '@material-ui/icons/Settings';
import IconLibraryBooks from "@material-ui/icons/LibraryBooks";
import {useSelector} from "@store/store";

/**
 * 第二層路由器設定
 *
 * @author Tony.su
 */
export const secondRoutes = [
    {
        path: "/home",
        exact: true,
        name: "首頁",
        icon: HomeIcon,
        component: HomePage,
    },
    {
        path: "/myset",
        exact: true,
        name: "我的設置",
        icon: SettingsIcon,
        component: MySetPage,
    },
    // {
    //     path: "/sse",
    //     exact: true,
    //     name: "server-sent-events test",
    //     icon: MoreVertIcon,
    //     component: SsePage,
    // },
    {
        path: "/level1",
        name: 'Nested Pages',
        icon: IconLibraryBooks,
        items: [
            {
                name: 'Level 2-1',
                path: "/level1/level21",
                exact: true,
                component: HomePage,
            },
            {
                name: 'Level 2-2',
                path: "/level1/level22",
                items: [
                    {
                        name: 'Level 3-1',
                        path: "/level1/level22/level31",
                        exact: true,
                        component: HomePage,
                    },
                    {
                        name: 'Level 3-2',
                        path: "/level1/level22/level32",
                        exact: true,
                        component: HomePage,
                    },
                ],
            },
        ],
    }
]

export const RouteManager = {
    allRouts() {
        const routes = [];
        const filter = items => {
            items.filter(m => m.items).map(m => {
                filter(m.items);
            });
            items.filter(m => (m.component)).map(m => {
                routes.push(m);
            });
        }
        filter(secondRoutes);
        return routes;
    },
    userRouts() {
        const menuList = useSelector(state => state.authentication.routes);
        const routes = [];
        const filter = items => {
            items.filter(m => (m.component && menuList.includes(m.path))).map(m => {
                routes.push(m);
            });
            items.filter(m => m.items).map(m => {
                filter(m.items);
            });
        }
        filter(secondRoutes);
        return routes;
    },
    userMenus() {
        const menuList = useSelector(state => state.authentication.routes);
        const filter = items => {
            let routes = items.filter(m => (m.component && menuList.includes(m.path)) || m.items);
            routes.map((m, i) => {
                if (m.items) {
                    m.items = filter(m.items)
                    routes[i] = m;
                }
            });
            routes = routes.filter(m => (m.component) || (m.items && m.items.length > 0));
            return routes;
        }
        return filter(secondRoutes);
    },
    getRouteNameMap(userMenus) {
        const routeNameMap = {};
        const setMap = items => {
            items.map((m, i) => {
                routeNameMap[m.path] = m.name;
                if (m.items) {
                    setMap(m.items);
                }
            });
        }
        setMap(userMenus);
        return routeNameMap;
    },
}