import React from 'react'
import { makeStyles, createStyles } from '@material-ui/core/styles'
import List from '@material-ui/core/List'
import ListItemText from '@material-ui/core/ListItemText'
import Divider from '@material-ui/core/Divider'
import Collapse from '@material-ui/core/Collapse'
import IconExpandLess from '@material-ui/icons/ExpandLess'
import IconExpandMore from '@material-ui/icons/ExpandMore'
import MenuItemComponent from './MenuItemComponent'
import {
    defaultFont,
    hexToRgb,
    whiteColor
} from "@src/assets/material/jss/material-dashboard-react";

const useStyles = makeStyles(
    createStyles({
        menuItem: {
            '&.active': {
                background: '#00ACC1',
                '& .MuiListItemIcon-root': {
                    color: '#fff',
                },
            },
            width: "auto",
            transition: "all 300ms linear",
            margin: "10px 15px 0",
            borderRadius: "3px",
            position: "relative",
            padding: "10px 15px",
            backgroundColor: "transparent",
            ...defaultFont
        },
        menuItemIcon: {
            // color: '#fff',
            // color: '#97c05c',
            width: "24px",
            height: "30px",
            fontSize: "24px",
            lineHeight: "30px",
            float: "left",
            marginRight: "15px",
            textAlign: "center",
            verticalAlign: "middle",
            color: "rgba(" + hexToRgb(whiteColor) + ", 0.8)"
        },
        itemText: {
            ...defaultFont,
            margin: "0",
            lineHeight: "30px",
            fontSize: "14px",
            color: whiteColor
        }
    }),
);
const MenuItem = props => {
    const { name, path, icon, items = [] } = props
    const classes = useStyles()
    const isExpandable = items && items.length > 0
    const [open, setOpen] = React.useState(false)
    function handleClick() {
        setOpen(!open)
    }
    function getPath(){
        if (isExpandable){
            return null;
        }
        return path;
    }
    const MenuItemRoot = (
        <MenuItemComponent className={classes.menuItem} path={getPath()} onClick={handleClick}>
            {/* Display an icon if any */}
            {!!icon && (
                <props.icon className={classes.menuItemIcon} />
            )}
            <ListItemText primary={name} inset={!icon} className={classes.itemText} disableTypography={true} />
            {/* Display the expand menu if the item has children */}
            {isExpandable && !open && <IconExpandMore className={classes.menuItemIcon} />}
            {isExpandable && open && <IconExpandLess className={classes.menuItemIcon} />}
        </MenuItemComponent>
    )

    const MenuItemChildren = isExpandable ? (
        <Collapse in={open} timeout="auto" unmountOnExit>
            <Divider />
            <List component="div" disablePadding>
                {items.map((item, index) => (
                    <MenuItem {...item} key={index} />
                ))}
            </List>
        </Collapse>
    ) : null

    return (
        <>
            {MenuItemRoot}
            {MenuItemChildren}
        </>
    )
}

export default MenuItem
