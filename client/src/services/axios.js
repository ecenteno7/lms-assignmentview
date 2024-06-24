// axios.js
import axios from "axios";

const PORT = 8080

export default axios.create({
  baseURL: `http://${process.env.REACT_APP_HOST_IP}:${PORT}`,
});
