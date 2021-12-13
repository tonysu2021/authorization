
export const queryAuth = (state) =>{
    return state.authentication;
}

export const getLanguage = (state) => {
    return state.authentication?.language ?? 'en';
}
