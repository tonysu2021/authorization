import environments from 'environments';

/**
 * server-sent events管理
 * 
 * @author Tony.su
 * 
 */
class SseService {

    /**
     * 建立一個SSE 連線
     * @param {*} path 
     * @param {*} callbackMethod 
     */
    constructor(path, callbackMethod) {
        this.eventSource = new EventSource(`${environments.sseUrl}/${path}`);

        this.eventSource.onopen =(event)=>{
            console.debug("EventSource is opened:",event);
        }

        this.eventSource.onmessage = (event) => {
            callbackMethod(event);
        }
        this.eventSource.onerror = (err) => {
            console.error("EventSource failed:", err);
            this.eventSource.close();
        }
    }

    sseClose() {
        console.debug("EventSource is closed");
        this.eventSource.close();
    }
}

export default SseService;
