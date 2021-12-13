import {
  defaultFont,
  dangerColor,
  whiteColor
} from "@material-asset/jss/material-dashboard-react.js";

import dropdownStyle from "@material-asset/jss/material-dashboard-react/dropdownStyle.js";

const headerLinksStyle = theme => ({
  ...dropdownStyle(theme),
  search: {
    "& > div": {
      marginTop: "0"
    },
    [theme.breakpoints.down("sm")]: {
      margin: "10px 15px !important",
      float: "none !important",
      paddingTop: "1px",
      paddingBottom: "1px",
      padding: "0!important",
      width: "60%",
      marginTop: "40px",
      "& input": {
        color: whiteColor
      }
    }
  },
  linkText: {
    zIndex: "4",
    ...defaultFont,
    fontSize: "14px",
    margin: "0px"
  },
  buttonLink: {
    [theme.breakpoints.down("sm")]: {
      display: "flex",
      margin: "10px 15px 0",
      width: "-webkit-fill-available",
      "& svg": {
        width: "24px",
        height: "30px",
        marginRight: "15px",
        marginLeft: "-15px"
      },
      "& .fab,& .fas,& .far,& .fal,& .material-icons": {
        fontSize: "24px",
        lineHeight: "30px",
        width: "24px",
        height: "30px",
        marginRight: "15px",
        marginLeft: "-15px"
      },
      "& > span": {
        justifyContent: "flex-start",
        width: "100%"
      }
    }
  },
  searchButton: {
    [theme.breakpoints.down("sm")]: {
      top: "-50px !important",
      marginRight: "22px",
      float: "right"
    }
  },
  margin: {
    zIndex: "4",
    margin: "0"
  },
  searchIcon: {
    width: "17px",
    zIndex: "4"
  },
  notifications: {
    zIndex: "4",
    [theme.breakpoints.up("md")]: {
      position: "absolute",
      top: "2px",
      border: "1px solid " + whiteColor,
      right: "4px",
      fontSize: "9px",
      background: dangerColor[0],
      color: whiteColor,
      minWidth: "16px",
      height: "16px",
      borderRadius: "10px",
      textAlign: "center",
      lineHeight: "16px",
      verticalAlign: "middle",
      display: "block"
    },
    [theme.breakpoints.down("sm")]: {
      ...defaultFont,
      fontSize: "14px",
      marginRight: "8px"
    }
  },
  manager: {
    [theme.breakpoints.down("sm")]: {
      width: "100%"
    },
    display: "inline-block"
  },
  searchWrapper: {
    [theme.breakpoints.down("sm")]: {
      width: "-webkit-fill-available",
      margin: "10px 15px 0"
    },
    display: "inline-block"
  },
  itemStyle:{
    fontSize: "18px",
    fontWeight: "bold",
    alignItems: "center",
    color:"#3f51b5",
    textAlign:"center",
    margin: "10px 15px 0"
},
  itemStyle_w:{
    fontSize: "18px",
    fontWeight: "bold",
    alignItems: "center",
    margin: "10px 15px 0",
    color: "#f8f8f8"
  },
  itemBground:{
    background:"rgba(0,172,193,0.42)",
    margin: "0px 10px 10px 10px",
    width:"150px",
    borderRadius: "10px",
    padding:"5px 10px 5px 10px",
    textAlign:"center",
  },
  vIcon:{
    width:"40px",
    margin:"-13px 5px 0px 5px"
  },
  userBalance_icon:{
    fontSize: "18px",
    textRendering: "auto",
    lineHeight: "inherit",
  },
  btnFont:{
    fontSize: "12px"
  }
});

export default headerLinksStyle;
