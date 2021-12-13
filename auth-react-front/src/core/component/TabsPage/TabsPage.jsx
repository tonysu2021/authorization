import React, {useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Tab from '@material-ui/core/Tab';
import TabContext from '@material-ui/lab/TabContext';
import TabList from '@material-ui/lab/TabList';
import TabPanel from '@material-ui/lab/TabPanel';



const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
        backgroundColor: theme.palette.background.paper,
    },
}));


/**
 * 自動下單設定頁面
 * 
 * @author Tony.su
 */
export const TabsPage = ({titles}) => {
    const classes = useStyles();
    const [value, setValue] = useState('1');

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };
    return (

        <div className={classes.root}>
            <TabContext value={value}>
                <AppBar position="static" color="default">

                    <TabList onChange={handleChange} variant="fullWidth" >
                        {titles.map(item=><Tab key={item.value} label={item.title} value={item.value} textColor="inherit" />)}
                    </TabList>
                </AppBar>
                {titles.map(item=><TabPanel key={item.value} value={item.value}>{item.child}</TabPanel>)}

            </TabContext>
        </div>
    );

}