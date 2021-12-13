import React, { forwardRef } from 'react'
import ListItem from '@material-ui/core/ListItem'
import { NavLink } from 'react-router-dom'

const MenuItemComponent = props => {
    const { className, onClick, path, children } = props

    // If link is not set return the orinary ListItem
    if (!path || typeof path !== 'string') {
        return (
            <ListItem
                button
                className={className}
                children={children}
                onClick={onClick}
            />
        )
    }

    // Return a LitItem with a link component
    return (
        <ListItem
            button
            className={className}
            children={children}
            component={forwardRef((props, ref) => <NavLink exact {...props} innerRef={ref} />)}
            to={path}
        />
    )
}

export default MenuItemComponent
