import React from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';

import { store, context } from '@store/store';
import { App } from '@view/App';

import { ErrorFallback } from '@core/hander'
import { ErrorBoundary } from 'react-error-boundary'

render(
    <Provider context={context} store={store}>
        <ErrorBoundary FallbackComponent={ErrorFallback}>
            <App />
        </ErrorBoundary>
    </Provider>,
    document.getElementById('app')
);
