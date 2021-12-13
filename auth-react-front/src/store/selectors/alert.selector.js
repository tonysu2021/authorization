
/**
 * 目前Request數量
 * @param {*} state 
 */
export const queryBlockers = (state) => {
    return state?.alert?.blockers ?? 0;
}