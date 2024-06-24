import axios from "./axios"

export const userLogin = async (username, password) => {
  const res = await axios.post('/api/login', {
    username: username,
    password: password
  })
  return res
}
