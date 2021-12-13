/** 
 * 常用的工具
 * 
 * @author Tony.su
*/
export const StringUtils = {

    /**
     * Parse JSON string
     * @param data json string
     * @returns json
     */
    parseJSON(data) {
        return JSON.parse(data);
    },


    isNotEmpty(data) {
        return data !== undefined && data !== null && data !== '';
    },

    isEmpty(data) {
        return data !== undefined || data !== null || data !== '';
    }
}