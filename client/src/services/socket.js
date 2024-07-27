import SockJs from "sockjs"
import Stomp from "stompjs"
import axios from "./axios"

var stompClient = null;
var courseId = null;
var onMessageReceived = null;

const connect = (courseId, onMessageReceived) => {
    this.courseId = courseId;
    this.onMessageReceived = onMessageReceived;
    var socket = new SockJs(`http://${process.env.REACT_APP_HOST_IP}:${axios.PORT}/websocket`)
    stompClient = Stomp.over(socket);
    stompClient.connect({}, subscribe);
}

const subscribe = () => {
    stompClient.subscribe(`/topic/chatter/${courseId}`, onMessageReceived)
}

const sendMessage = (payload) => {
    stompClient.send(`/app/chatter/${courseId}`, {}, JSON.stringify(payload))
}
