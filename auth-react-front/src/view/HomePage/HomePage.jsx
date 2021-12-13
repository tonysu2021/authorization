import React, { useEffect } from 'react';
import UserService from "@core/services/user.service";

/**
 * 首頁
 * 
 * @author Tony.su
 */
export const HomePage = () => {


    useEffect(() => {
        let users = UserService.findAll();
    }, []);

    return (
        <div>

        </div>
    );
}