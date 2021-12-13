import { AlertActionType } from '../types/alert-action.type';

const initialState = {
  type: '',
  message: '',
  loading: false,
  error: '',
  blockers: 0,
};

export function alertReducer(state = initialState, action) {
  switch (action.type) {
    case AlertActionType.SUCCESS:
      return {
        type: 'alert-success',
        message: action.payload.message
      };
    case AlertActionType.ERROR:
      return {
        type: 'alert-danger',
        message: action.payload.message
      };
    case AlertActionType.CLEAR:
      return {
        type: '',
        message: ''
      };
    case AlertActionType.API_LOADING: {
      return {
        ...state,
        loading: true,
        error: ''
      };
    }
    case AlertActionType.API_SUCCESS: {
      return {
        ...state,
        loading: false
      }
    }
    case AlertActionType.API_ERROR: {
      return {
        ...state,
        loading: false,
        error: action.payload.error
      };
    }
    default:
      return state
  }
}