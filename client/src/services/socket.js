import { PORT } from "./axios"

const protocal = process.env.REACT_APP_HOST_IP === "localhost" ? "http" : "ws";

export const socketUrl = `${protocal}://${process.env.REACT_APP_HOST_IP}:${PORT}/websocket`

