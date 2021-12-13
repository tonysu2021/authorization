
export const DateUtils = {
    getTimeEpoch(currentTime) {
        return Math.floor((currentTime).getTime() / 1000);
    },

    getTimeEpochMS(currentTime) {
        return Math.floor((currentTime).getTime());
    },

    getYesterdayTEMs() {
        var d = new Date();
        d.setDate(d.getDate());
        d.setTime(d.getTime() - d.getHours() * 3600 * 1000 - d.getMinutes() * 60 * 1000 - d.getSeconds() * 1000);
        return Math.floor((d).getTime() / 1000);
    },

    getTimeIso(currentTime) {
        return currentTime.toISOString();
    }
}