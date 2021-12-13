import {fade,makeStyles, withStyles} from '@material-ui/core/styles';
import MuiAccordion from "@material-ui/core/Accordion";
import MuiAccordionSummary from "@material-ui/core/AccordionSummary";
import MuiAccordionDetails from "@material-ui/core/AccordionDetails";
import SearchImg from "@assets/image/searchicon.png";

export const ucStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
    },
    backButton: {
        marginRight: theme.spacing(1),
    },
    instructions: {
        marginTop: theme.spacing(1),
        marginBottom: theme.spacing(1),
    },
}));

export const useTableStyles = makeStyles({
    table: {
        minWidth: 700,
    },
    pageSelect: {
        outline: "none",
        overflow: "hidden",
        height: "25px",
        color: "#747a80",
        padding: "3px 3px 3px 3px",
        "-moz-border-radius": "6px",
        "-webkit-border-radius": "6px",
        background: "none repeat scroll 0 0 #FFFFFF",
        border: "1px solid #E5E5E5",
        borderRadius: "5px 5px 5px 5px",
        boxShadow: "0 0 10px #E8E8E8 inset",
        marginRight: "8px",
        width: "52px",
        "& option": {
            border: "none",
            boxShadow: "10 10 5px #ccc",
        },
    },
    smallTable: {
        minWidth: 300,
    },
    tableMargin:{
        margin:"10px",
    },
});

export const useSearchStyles = makeStyles((theme) => ({
    searchInputBox: {
        border:"1px solid #ccc",
        overflow:"hidden",
        "-webkit-box-sizing": "border-box",
        "-moz-box-sizing": "border-box",
        boxSizing: "border-box",
        marginTop:"10px",
    },
    searchInput: {
        display:"block",
        backgroundImage:`url(${SearchImg})`,
        backgroundPosition: "10px 12px",
        backgroundRepeat: "no-repeat",
        width:"100%",
        fontSize: "16px",
        padding: "12px 20px 12px 40px",
        border: "0px",
        marginBottom: "0px",
        outline:"none",
    },

}));

export const cardStyles = makeStyles((theme) => ({
    footerBody:{
        position:"fixed",
        top: 'auto',
        height:"50px",
        bottom: 0,
        margin:"0px auto" ,
        width:"90vw",
        [theme.breakpoints.up(900)]: {
            left:"55%",
        },
        [theme.breakpoints.between(800,900)]: {
            left:"40%",
        },
        [theme.breakpoints.between(400,800)]: {
            left:"35%",
        },
        [theme.breakpoints.between(0,400)]: {
            left:"25%",
        },
    },
    card: {
        margin:"20px",
        display: "flex",
        flexWrap: "wrap",
        boxShadow:"2px 2px 3px 3px #cccccc",
        "& > *": {
            margin: theme.spacing(3),
        }
    },
    timeFormControl: {
        margin: theme.spacing(1),
        minWidth: 120
    },
    checkButton:{
      margin:"-5px"
    },
    typeButtonStyle: {
        backgroundColor: "#4c68af",
        border: "none",
        color: "white",
        padding: "5px 5px",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        margin: "2px 2px",
        cursor: "pointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "20px",
        wordBreak:" break-all",
        fontSize: "12px",
        width: "60px",
    },
    buttonStyle: {
    backgroundColor: "#4CAF50",
    border: "none",
    color: "white",
    padding: "5px 5px",
    textAlign: "center",
    textDecoration: "none",
    display: "inline-block",
    fontSize: "10px",
    margin: "2px 2px",
    cursor: "pointer",
    "-webkit-transition-duration":" 0.4s",
    transitionDuration: "0.4s",
    boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
    borderRadius: "20px",
    wordBreak:" break-all",
        [theme.breakpoints.up(900)]: {
            fontSize: "16px",
            width: "150px",
        },
        [theme.breakpoints.between(800,900)]: {
            fontSize: "16px",
            width: "130px",
        },
        [theme.breakpoints.between(600,800)]: {
            fontSize: "16px",
            width: "120px",
        },
        [theme.breakpoints.between(450,600)]: {
            fontSize: "14px",
            width: "110px",
        },
        [theme.breakpoints.between(399,450)]: {
            fontSize: "12px",
            width: "90px",
        },
        [theme.breakpoints.between(345,399)]: {
            fontSize: "12px",
            width: "70px",
        },
        [theme.breakpoints.between(0,345)]: {
            fontSize: "12px",
            width: "60px",
        },
    },
    buttonStyle_non: {
        backgroundColor: "#FFF",
        border: "none",
        color: "block",
        padding: "5px 5px",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        fontSize: "10px",
        margin: "2px 2px",
        cursor: "pointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "20px",
        wordBreak:" break-all",
        [theme.breakpoints.up(900)]: {
            fontSize: "16px",
            width: "150px",
        },
        [theme.breakpoints.between(800,900)]: {
            fontSize: "16px",
            width: "130px",
        },
        [theme.breakpoints.between(600,800)]: {
            fontSize: "16px",
            width: "120px",
        },
        [theme.breakpoints.between(450,600)]: {
            fontSize: "14px",
            width: "110px",
        },
        [theme.breakpoints.between(399,450)]: {
            fontSize: "12px",
            width: "90px",
        },
        [theme.breakpoints.between(345,399)]: {
            fontSize: "12px",
            width: "70px",
        },
        [theme.breakpoints.between(0,345)]: {
            fontSize: "12px",
            width: "60px",
        },
    },
    buttonStyle_in: {
        backgroundColor:"rgb(245,44,103)",
        border: "none",
        color: "white",
        padding: "5px 5px",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        fontSize: "10px",
        margin: "2px 2px",
        cursor: "pointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "20px",
        wordBreak:" break-all",
        [theme.breakpoints.up(900)]: {
            fontSize: "16px",
            width: "150px",
        },
        [theme.breakpoints.between(600,900)]: {
            fontSize: "16px",
            width: "130px",
        },
        [theme.breakpoints.between(450,600)]: {
            fontSize: "14px",
            width: "120px",
        },
        [theme.breakpoints.between(0,450)]: {
            fontSize: "12px",
            width: "100px",
        },
    },
    buttonStyle_in_non: {
        backgroundColor: "#FFF",
        border: "none",
        color: "block",
        padding: "5px 5px",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        fontSize: "10px",
        margin: "2px 2px",
        cursor: "pointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "20px",
        wordBreak:" break-all",
        [theme.breakpoints.up(900)]: {
            fontSize: "16px",
            width: "150px",
        },
        [theme.breakpoints.between(600,900)]: {
            fontSize: "16px",
            width: "130px",
        },
        [theme.breakpoints.between(450,600)]: {
            fontSize: "14px",
            width: "120px",
        },
        [theme.breakpoints.between(0,450)]: {
            fontSize: "12px",
            width: "100px",
        },

    },
    buttonStyle_h: {
        backgroundColor:"rgba(255, 140, 177,1)",
        border: "none",
        color: "rgb(101,101,101)",
        padding: "2px 2px",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        fontSize: "10px",
        margin: "1px 1px",
        cursor: "pointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "10px",
        [theme.breakpoints.up(900)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "140px",
        },
        [theme.breakpoints.between(800,900)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "120px",
        },
        [theme.breakpoints.between(600,800)]: {
            padding: "10px 28px",
            fontSize: "16px",
            width: "100px",
        },
        [theme.breakpoints.between(450,600)]: {
            padding: "10px 22px",
            fontSize: "14px",
            width: "80px",
        },
        [theme.breakpoints.between(399,450)]: {
            padding: "10px 20px",
            fontSize: "12px",
            width: "65px",
        },
        [theme.breakpoints.between(345,399)]: {
            padding: "5px 15px",
            fontSize: "12px",
            width: "60px",
        },
        [theme.breakpoints.between(0,345)]: {
            padding: "5px 10px",
            fontSize: "12px",
            width: "50px",
        },
    },
    buttonStyle_h_non: {
        backgroundColor: "#FFF",
        border: "none",
        color: "#000",
        padding: "2px 2px",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        fontSize: "10px",
        margin: "1px 1px",
        cursor: "pointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "10px",
        [theme.breakpoints.up(900)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "140px",
        },
        [theme.breakpoints.between(800,900)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "120px",
        },
        [theme.breakpoints.between(600,800)]: {
            padding: "10px 28px",
            fontSize: "16px",
            width: "100px",
        },
        [theme.breakpoints.between(450,600)]: {
            padding: "10px 22px",
            fontSize: "14px",
            width: "80px",
        },
        [theme.breakpoints.between(399,450)]: {
            padding: "10px 20px",
            fontSize: "12px",
            width: "65px",
        },
        [theme.breakpoints.between(345,399)]: {
            padding: "5px 15px",
            fontSize: "12px",
            width: "60px",
        },
        [theme.breakpoints.between(0,345)]: {
            padding: "5px 10px",
            fontSize: "12px",
            width: "50px",
        },
    },
    buttonStyle_s: {
        backgroundColor: 'rgb(250,242,206)',
        border: "none",
        color: "rgb(101,101,101)",
        padding: "2px 2px",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        fontSize: "10px",
        margin: "1px 1px",
        cursor: "pointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "20px",
        [theme.breakpoints.up(900)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "140px",
        },
        [theme.breakpoints.between(800,900)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "120px",
        },
        [theme.breakpoints.between(600,800)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "100px",
        },
        [theme.breakpoints.between(450,600)]: {
            padding: "10px 20px",
            fontSize: "14px",
            width: "80px",
        },
        [theme.breakpoints.between(399,450)]: {
            padding: "10px 20px",
            fontSize: "12px",
            width: "65px",
        },
        [theme.breakpoints.between(345,399)]: {
            padding: "5px 5px",
            fontSize: "12px",
            width: "60px",
        },
        [theme.breakpoints.between(0,345)]: {
            padding: "5px 5px",
            fontSize: "12px",
            width: "50px",
        },
    },
    buttonStyle_s_non: {
        backgroundColor: "#FFF",
        border: "none",
        color: "#000",
        padding: "2px 2px",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        fontSize: "10px",
        margin: "1px 1px",
        cursor: "pointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "20px",
        [theme.breakpoints.up(900)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "140px",
        },
        [theme.breakpoints.between(800,900)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "120px",
        },
        [theme.breakpoints.between(600,800)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "100px",
        },
        [theme.breakpoints.between(450,600)]: {
            padding: "10px 20px",
            fontSize: "14px",
            width: "80px",
        },
        [theme.breakpoints.between(399,450)]: {
            padding: "10px 20px",
            fontSize: "12px",
            width: "65px",
        },
        [theme.breakpoints.between(345,399)]: {
            padding: "5px 5px",
            fontSize: "12px",
            width: "60px",
        },
        [theme.breakpoints.between(0,345)]: {
            padding: "5px 5px",
            fontSize: "12px",
            width: "50px",
        },
    },
    buttonStyle_all: {
        backgroundColor: "rgb(255,162,0)",
        color:"#FFF",
        border: "none",
        padding: "5px 5px",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        fontSize: "10px",
        margin: "2px 2px",
        cursor: "pointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "20px",
        wordBreak:" break-all",
        [theme.breakpoints.up(900)]: {
            fontSize: "16px",
            width: "160px",
        },
        [theme.breakpoints.between(800,900)]: {
            fontSize: "16px",
            width: "150px",
        },
        [theme.breakpoints.between(600,800)]: {
            fontSize: "16px",
            width: "140px",
        },
        [theme.breakpoints.between(450,600)]: {
            fontSize: "14px",
            width: "120px",
        },
        [theme.breakpoints.between(399,450)]: {
            fontSize: "12px",
            width: "110px",
        },
        [theme.breakpoints.between(345,399)]: {
            fontSize: "12px",
            width: "100px",
        },
        [theme.breakpoints.between(0,345)]: {
            fontSize: "12px",
            width: "90px",
        },
    },
    buttonStyle_cancel: {
        backgroundColor: "#807f7f",
        color:"#FFF",
        border: "none",
        padding: "5px 5px",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        fontSize: "10px",
        margin: "2px 2px",
        cursor: "pointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "20px",
        wordBreak:" break-all",
        [theme.breakpoints.up(900)]: {
            fontSize: "16px",
            width: "160px",
        },
        [theme.breakpoints.between(800,900)]: {
            fontSize: "16px",
            width: "150px",
        },
        [theme.breakpoints.between(600,800)]: {
            fontSize: "16px",
            width: "140px",
        },
        [theme.breakpoints.between(450,600)]: {
            fontSize: "14px",
            width: "120px",
        },
        [theme.breakpoints.between(399,450)]: {
            fontSize: "12px",
            width: "110px",
        },
        [theme.breakpoints.between(345,399)]: {
            fontSize: "12px",
            width: "100px",
        },
        [theme.breakpoints.between(0,345)]: {
            fontSize: "12px",
            width: "90px",
        },
    },
    buttonStyle_ok: {
        backgroundColor: "rgb(0,102,255)",
        color:"#FFF",
        border: "none",
        padding: "5px 5px",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        fontSize: "10px",
        margin: "2px 2px",
        cursor: "pointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "20px",
        wordBreak:" break-all",
        [theme.breakpoints.up(900)]: {
            fontSize: "16px",
            width: "160px",
        },
        [theme.breakpoints.between(800,900)]: {
            fontSize: "16px",
            width: "150px",
        },
        [theme.breakpoints.between(600,800)]: {
            fontSize: "16px",
            width: "140px",
        },
        [theme.breakpoints.between(450,600)]: {
            fontSize: "14px",
            width: "120px",
        },
        [theme.breakpoints.between(399,450)]: {
            fontSize: "12px",
            width: "110px",
        },
        [theme.breakpoints.between(345,399)]: {
            fontSize: "12px",
            width: "100px",
        },
        [theme.breakpoints.between(0,345)]: {
            fontSize: "12px",
            width: "90px",
        },
    },
    buttonStyle_bn: {
        backgroundColor: "rgb(0,102,255)",
        color:"#FFF",
        border: "none",
        padding: "5px 5px",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        fontSize: "10px",
        margin: "2px 2px",
        cursor: "pointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "20px",
        wordBreak:" break-all",
        [theme.breakpoints.up(900)]: {
            fontSize: "16px",
            width: "140px",
        },
        [theme.breakpoints.between(800,900)]: {
            fontSize: "16px",
            width: "130px",
        },
        [theme.breakpoints.between(600,800)]: {
            fontSize: "16px",
            width: "120px",
        },
        [theme.breakpoints.between(450,600)]: {
            fontSize: "14px",
            width: "100px",
        },
        [theme.breakpoints.between(399,450)]: {
            fontSize: "12px",
            width: "90px",
        },
        [theme.breakpoints.between(345,399)]: {
            fontSize: "12px",
            width: "80px",
        },
        [theme.breakpoints.between(0,345)]: {
            fontSize: "12px",
            width: "70px",
        },
    },
    buttonStyle_cl: {
        backgroundColor: "#807f7f",
        color:"#FFF",
        border: "none",
        padding: "5px 5px",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        fontSize: "10px",
        margin: "2px 2px",
        cursor: "pointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "20px",
        wordBreak:" break-all",
        [theme.breakpoints.up(900)]: {
            fontSize: "16px",
            width: "140px",
        },
        [theme.breakpoints.between(800,900)]: {
            fontSize: "16px",
            width: "130px",
        },
        [theme.breakpoints.between(600,800)]: {
            fontSize: "16px",
            width: "120px",
        },
        [theme.breakpoints.between(450,600)]: {
            fontSize: "14px",
            width: "100px",
        },
        [theme.breakpoints.between(399,450)]: {
            fontSize: "12px",
            width: "90px",
        },
        [theme.breakpoints.between(345,399)]: {
            fontSize: "12px",
            width: "80px",
        },
        [theme.breakpoints.between(0,345)]: {
            fontSize: "12px",
            width: "70px",
        },
    },
    buttonStyle_all_s: {
        backgroundColor: "rgb(255,162,0)",
        color:"#FFF",
        border: "none",
        padding: "5px 5px",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        fontSize: "10px",
        margin: "2px 2px",
        cursor: "pointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "20px",
        wordBreak:" break-all",
        [theme.breakpoints.up(900)]: {
            fontSize: "16px",
            width: "140px",
        },
        [theme.breakpoints.between(800,900)]: {
            fontSize: "16px",
            width: "130px",
        },
        [theme.breakpoints.between(600,800)]: {
            fontSize: "16px",
            width: "120px",
        },
        [theme.breakpoints.between(450,600)]: {
            fontSize: "14px",
            width: "100px",
        },
        [theme.breakpoints.between(399,450)]: {
            fontSize: "12px",
            width: "90px",
        },
        [theme.breakpoints.between(345,399)]: {
            fontSize: "12px",
            width: "80px",
        },
        [theme.breakpoints.between(0,345)]: {
            fontSize: "12px",
            width: "70px",
        },
    },
    buttonStyle_s2: {
        backgroundColor: 'rgb(250,242,206)',
        border: "none",
        color: "rgb(101,101,101)",
        padding: "2px 2px",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        fontSize: "10px",
        margin: "2px 2px",
        cursor: "pointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "20px",
        [theme.breakpoints.up(900)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "280px",
        },
        [theme.breakpoints.between(800,900)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "240px",
        },
        [theme.breakpoints.between(600,800)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "200px",
        },
        [theme.breakpoints.between(450,600)]: {
            padding: "10px 20px",
            fontSize: "14px",
            width: "160px",
        },
        [theme.breakpoints.between(399,450)]: {
            padding: "5px 5px",
            fontSize: "12px",
            width: "130px",
        },
        [theme.breakpoints.between(345,399)]: {
            padding: "5px 5px",
            fontSize: "12px",
            width: "120px",
        },
        [theme.breakpoints.between(0,345)]: {
            padding: "5px 3px",
            fontSize: "12px",
            width: "100px",
        },
    },
    buttonStyle_s2_non: {
        backgroundColor: "#FFF",
        border: "none",
        color: "#000",
        padding: "2px 2px",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        fontSize: "10px",
        margin: "2px 2px",
        cursor: "pointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "20px",
        [theme.breakpoints.up(900)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "280px",
        },
        [theme.breakpoints.between(800,900)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "240px",
        },
        [theme.breakpoints.between(600,800)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "200px",
        },
        [theme.breakpoints.between(450,600)]: {
            padding: "10px 20px",
            fontSize: "14px",
            width: "160px",
        },
        [theme.breakpoints.between(399,450)]: {
            padding: "5px 5px",
            fontSize: "12px",
            width: "130px",
        },
        [theme.breakpoints.between(345,399)]: {
            padding: "5px 5px",
            fontSize: "12px",
            width: "120px",
        },
        [theme.breakpoints.between(0,345)]: {
            padding: "5px 3px",
            fontSize: "12px",
            width: "100px",
        },
    },
    buttonStyle_general: {
        backgroundColor: "rgba(1,73,255,0.57)",
        border: "none",
        color: "white",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        cursor: "p5ointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "10px",
        [theme.breakpoints.up(900)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "140px",
        },
        [theme.breakpoints.between(800,900)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "120px",
        },
        [theme.breakpoints.between(600,800)]: {
            padding: "10px 22px",
            fontSize: "16px",
            width: "100px",
        },
        [theme.breakpoints.between(399,600)]: {
            padding: "10px 20px",
            fontSize: "14px",
            width: "90px",
        },
        [theme.breakpoints.between(0,399)]: {
            padding: "5px 5px",
            fontSize: "12px",
            width: "60px",
        },
    },
    buttonStyle_general_r: {
        backgroundColor: "rgba(1,73,255,0.57)",
        border: "none",
        color: "white",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        cursor: "p5ointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "10px",
        [theme.breakpoints.up(900)]: {
            width: "145px",
            fontSize: "18px",
        },
        [theme.breakpoints.between(800,900)]: {
            fontSize: "16px",
            width: "130px",
        },
        [theme.breakpoints.between(600,800)]: {
            fontSize: "16px",
            width: "120px",
        },
        [theme.breakpoints.between(500,600)]: {
            fontSize: "14px",
            width: "120px",
        },
        [theme.breakpoints.between(400,500)]: {
            fontSize: "14px",
            width: "100px",
        },
        [theme.breakpoints.between(350,400)]: {
            fontSize: "12px",
            width: "80px",
        },
        [theme.breakpoints.between(0,350)]: {
            fontSize: "12px",
            width: "70px",
        },
    },
    buttonStyle_general_o: {
        backgroundColor: "rgba(1,73,255,0.57)",
        border: "none",
        color: "white",
        textAlign: "center",
        textDecoration: "none",
        display: "inline-block",
        cursor: "p5ointer",
        "-webkit-transition-duration":" 0.4s",
        transitionDuration: "0.4s",
        boxShadow: "0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19)",
        borderRadius: "10px",
        [theme.breakpoints.up(900)]: {
            width: "100px",
            fontSize: "18px",
        },
        [theme.breakpoints.between(800,900)]: {
            fontSize: "16px",
            width: "90px",
        },
        [theme.breakpoints.between(600,800)]: {
            fontSize: "16px",
            width: "80px",
        },
        [theme.breakpoints.between(500,600)]: {
            fontSize: "14px",
            width: "70px",
        },
        [theme.breakpoints.between(400,500)]: {
            fontSize: "14px",
            width: "60px",
        },
        [theme.breakpoints.between(350,400)]: {
            fontSize: "12px",
            width: "50px",
        },
        [theme.breakpoints.between(0,350)]: {
            fontSize: "12px",
            width: "40px",
        },
    },
    weekDetailHead:{
        fontSize: "18px",
        color:"#FFF",
        textAlign: "center",
        backgroundColor:"rgba(255, 140, 177,1)",
    },
    gameDetailHead:{
        marginTop:"15px",
        fontWeight:"bold",
        textAlign: "center",
        [theme.breakpoints.up(900)]: {
            fontSize: "20px",
        },
        [theme.breakpoints.between(800,900)]: {
            fontSize: "18px",
        },
        [theme.breakpoints.between(600,800)]: {
            fontSize: "16px",
        },
        [theme.breakpoints.between(399,600)]: {
            fontSize: "16px",
        },
        [theme.breakpoints.between(0,399)]: {
            fontSize: "16px",
        },
    }
}));


export const stepStyles = makeStyles((theme) => ({
    button: {
        marginRight: theme.spacing(1)
    },
    backButton: {
        marginRight: theme.spacing(1)
    },
    completed: {
        display: "inline-block"
    },
    instructions: {
        marginTop: theme.spacing(1),
        marginBottom: theme.spacing(1)
    },
    overFlow: {
        width: "100%",
        overflow: "auto"
    }
}));

export const Accordion = withStyles({
    root: {
        border: "1px solid rgba(0, 0, 0, .125)",
        boxShadow: "none",
        "&:not(:last-child)": {
            borderBottom: 0
        },
        "&:before": {
            display: "none"
        },
        "&$expanded": {
            margin: "auto"
        }
    },
    expanded: {}
})(MuiAccordion);

export const AccordionSummary = withStyles({
    root: {
        backgroundColor: "rgba(0, 0, 0, .03)",
        borderBottom: "1px solid rgba(0, 0, 0, .125)",
        marginBottom: -1,
        minHeight: 56,
        "&$expanded": {
            minHeight: 56
        }
    },
    content: {
        "&$expanded": {
            margin: "12px 0"
        }
    },
    expanded: {}
})(MuiAccordionSummary);

export const AccordionDetails = withStyles((theme) => ({
    root: {
        padding: theme.spacing(2)
    }
}))(MuiAccordionDetails);