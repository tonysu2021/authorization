import i18n from "i18next";
import {initReactI18next} from "react-i18next";

/**
 * 多語系resource
 * 
 * @author Tony.su
 */
export const locale = {
    en: {
        translation: {
            "LOGIN": "Login",
            "USER_NAME": "Username",
            "PASSWORD": "Password"
        }
    },
    cn: {
        translation: {
            "LOGIN": "登入",
            "USER_NAME": "用户帐户",
            "PASSWORD": "用户密码"
        }
    },
    tw: {
        translation: {
            "LOGIN": "登入",
            "USER_NAME": "使用者帳號",
            "PASSWORD": "使用者密碼"
        }
    },
}

i18n
    .use(initReactI18next) // passes i18n down to react-i18next
    .init({
        resources: locale,
        lng: "en",
        fallbackLng: "en",

        interpolation: {
            escapeValue: false
        }
    });

export default i18n;
