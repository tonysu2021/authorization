import React from 'react'
import { makeStyles } from '@material-ui/core/styles'
import List from '@material-ui/core/List'
import bgImage from "@material-asset/img/sidebar-2.jpg";
import MenuItem from './MenuItem'
import classNames from "classnames";
import Drawer from "@material-ui/core/Drawer";
import Hidden from "@material-ui/core/Hidden";
import styles from "@material-asset/jss/material-dashboard-react/components/sidebarStyle.js";

const useStyles = makeStyles(styles);
export const Menu = (props) => {
    const classes = useStyles()
    const {logo, logoText, userMenus} = props;
    const menu = (
        <>
            <div className={classes.logo}>
                <a
                    className={classNames(classes.logoLink, {
                        [classes.logoLinkRTL]: props.rtlActive
                    })}
                >
                    <div className={classes.logoImage}>
                        <img src={logo} alt="logo" className={classes.img}/>
                    </div>
                    {logoText}
                </a>
            </div>
            <div className={classes.sidebarWrapper}>
                <List className={classes.list}>
                    {userMenus.map((item, index) => (
                        <MenuItem {...item} key={index}/>
                    ))}
                </List>
            </div>
            <div
                className={classes.background}
                style={{backgroundImage: "url(" + bgImage + ")"}}
            />
        </>
    );

    return (
        <div>
            <Hidden mdUp implementation="css">
                <Drawer
                    variant="temporary"
                    anchor={props.rtlActive ? "left" : "right"}
                    open={props.open}
                    classes={{
                        paper: classNames(classes.drawerPaper, {
                            [classes.drawerPaperRTL]: props.rtlActive
                        })
                    }}
                    onClose={props.handleDrawerToggle}
                    ModalProps={{
                        keepMounted: true // Better open performance on mobile.
                    }}
                >
                    {menu}
                </Drawer>
            </Hidden>
            <Hidden smDown implementation="css">
                <Drawer
                    anchor={props.rtlActive ? "right" : "left"}
                    variant="permanent"
                    open
                    classes={{
                        paper: classNames(classes.drawerPaper, {
                            [classes.drawerPaperRTL]: props.rtlActive
                        })
                    }}
                >
                    {menu}
                </Drawer>
            </Hidden>
        </div>
    )
}

