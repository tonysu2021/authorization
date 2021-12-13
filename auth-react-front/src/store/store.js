import React from 'react';
import { compose, createStore, applyMiddleware } from 'redux';
import {
    createStoreHook,
    createDispatchHook,
    createSelectorHook
} from 'react-redux'
import thunkMiddleware from 'redux-thunk';
import reduxMiddleware from "react-block-ui/lib/reduxMiddleware";
import { createLogger } from 'redux-logger';
import environments from 'environments';
import rootReducer from '@store/reducers';

// convert object to string and store in localStorage
function saveToLocalStorage(state) {
    try {
        const serialisedState = JSON.stringify(state);
        localStorage.setItem("persistantState", serialisedState);
    } catch (e) {
        console.warn(e);
    }
}

// load string from localStarage and convert into an Object
// invalid output must be undefined
function loadFromLocalStorage() {
    try {
        const serialisedState = localStorage.getItem("persistantState");
        if (serialisedState === null) return undefined;
        return JSON.parse(serialisedState);
    } catch (e) {
        console.warn(e);
        return undefined;
    }
}




const composeEnhancer = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

export const context = React.createContext(null)

// Export your custom hooks if you wish to use them in other files.
export const useStore = createStoreHook(context)
export const useDispatch = createDispatchHook(context)
export const useSelector = createSelectorHook(context)

const middlewares = [thunkMiddleware, reduxMiddleware];

if (!environments.production) {
    // options設定執行時間
    const loggerMiddleware = createLogger();
    middlewares.push(loggerMiddleware);
}

export const store = createStore(
    rootReducer,
    loadFromLocalStorage(),
    composeEnhancer(
        applyMiddleware(
            ...middlewares
        )
    )
);

// listen for store changes and use saveToLocalStorage to
// save them to localStorage
store.subscribe(() => saveToLocalStorage(store.getState()));