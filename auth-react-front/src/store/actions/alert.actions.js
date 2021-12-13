import { AlertActionType } from '../types/alert-action.type';

const success = (message) => {
    return { type: AlertActionType.SUCCESS, payload: { message } };
}

const error = (message) => {
    return { type: AlertActionType.ERROR, payload: { message } };
}

const clear = () => {
    return { type: AlertActionType.CLEAR, payload: {} };
}

const apiLoading = () => {
    return { type: AlertActionType.API_LOADING, payload: {} };
}

const apiSuccess = () => {
    return { type: AlertActionType.API_SUCCESS, payload: {} };
}

const apiError = (error) => {
    return { type: AlertActionType.API_ERROR, payload: { error } };
}

export const alertActions = {
    success,
    error,
    clear,
    apiLoading,
    apiSuccess,
    apiError,
};