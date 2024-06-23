import axios from "./axios"

export const login = (username, password) => {
  console.log(username, password)
  axios.post('/api/login', {
    username: username,
    password: password
  }).then((res) => {
    console.log(res.body.user)
    return res
  }).catch((err) => {
    console.log(err)
    return err
  })
}
