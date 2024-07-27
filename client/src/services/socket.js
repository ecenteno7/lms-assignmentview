import SockJs from "sockjs"
import Stomp from "stompjs"
import axios from "./axios"

var stompClient = null;
var courseId = null;
var onMessageReceived = null;

export const connect = (courseId, onMessageReceived) => {
  this.courseId = courseId;
  this.onMessageReceived = onMessageReceived;
  var socket = new SockJs(`http://${process.env.REACT_APP_HOST_IP}:${axios.PORT}/websocket`)
  stompClient = Stomp.over(socket);
  stompClient.connect({}, subscribe);
}

export const sendMessage = (payload) => {
  stompClient.send(`/app/chatter/${courseId}`, {}, JSON.stringify(payload))
}

const subscribe = () => {
  stompClient.subscribe(`/topic/chatter/${courseId}`, onMessageReceived)
}
