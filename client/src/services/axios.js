// axios.js
import axios from "axios";

const HOSTNAME = "localhost"
const PORT = 8080

export default axios.create({
  baseURL: `http://${HOSTNAME}:${PORT}`,
});
