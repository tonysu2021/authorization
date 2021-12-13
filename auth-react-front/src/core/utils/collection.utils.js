/** 
 * 陣列常用的工具
 * 
 * @author Tony.su
*/
export const CollectionUtil = {

    /**
     * Check for null Array
     * @param {any} list
     * @returns {boolean} 
     */
    isNotEmpty(list) {
        return (list !== undefined && list !== null && Array.isArray(list)) && (list.length > 0);
    },

    isEmpty(list) {
        return (list === undefined || list === null || !Array.isArray(list)) || (list.length == 0);
    },
    /**
     * Remove Element from an Array
     * @param {*} array 
     * @param {*} elem 
     */
    removeElement(array, elem) {
        var index = array.indexOf(elem);
        if (index > -1) {
            array.splice(index, 1);
        }
    },
}