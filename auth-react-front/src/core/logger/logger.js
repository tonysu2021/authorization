import log from "loglevel";

export const logger = {
    info(message){
        log.info(message);
    },
    debug(message){
        log.debug(message);
    },
    error(message){
        log.error(message);
    },
    warn(message){
        log.warn(message);
    },
}