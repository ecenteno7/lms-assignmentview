import { PORT } from "./axios"

export const socketUrl = `http://${process.env.REACT_APP_HOST_IP}:${PORT}/websocket`

