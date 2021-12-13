/** 
 * 常用的工具
 * 
 * @author Tony.su
*/
export const CommonUtils = {

    /**
     * Check for null Object
     * @param {any} val
     * @returns {boolean} 
     */
    isObject(val) {
        if (val === null) {
            return false;
        }
        return ((typeof val === 'function') || (typeof val === 'object'));
    },

     /**
     * Check empty object
     * @param {Object} obj
     * @returns {boolean} 
     */
    checkEmptyObject(obj) {
        for (var key in obj) {
            if (Object.prototype.hasOwnProperty.call(obj,key))
                return false;
        }
        return true;
    },
    isNotEmptyObject(obj) {
        return obj !== undefined && obj !== null;
    },
    isEmptyObject(obj) {
        return obj === undefined || obj === null;
    },

    str2Number(str){
        return parseFloat(str);
    }
}