import React from 'react';
import { Link } from 'react-router-dom';

export const AccessDeniedPage = () => {
    return (
        <div>
            <h1>Page Not Found</h1>
            <p>Sorry, there is nothing to see here.</p>
            <p><Link to="/login">Back to Login</Link></p>
        </div>
    )
}
