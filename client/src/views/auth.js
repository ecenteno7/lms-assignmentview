import { useState, useEffect, useContext } from "react";
import { AuthContext } from "../context/authContext";
import { login } from "../services/api"
export const Login = () => {
  const [user, setUser] = useState("");
  const [pwd, setPwd] = useState("");
  const [errMsg, setErrMsg] = useState("");
  const [success, setSuccess] = useState(false);

  const { setAuth } = useContext(AuthContext);

  useEffect(() => {
    setErrMsg("");
  }, [user, pwd]);

  const handleSubmit = () => {
    login(user, pwd).then((res) => {
      if (res.status == 200) {
        setAuth(res.user)
        setSuccess(res.status)
      } else {
        setErrMsg(`ERR: ${res.status} - Error logging in.`)
        console.log("err logging in")
      }
    });
  }

  return (
    <div className="w-full h-full flex justify-center items-center">
      <div className="grid grid-rows-12 w-1/5 h-2/3 gap-y-4">
        <input
          type="text"
          id="username"
          className="bg-slate-200 text-gray-900 outline-none text-sm rounded-lg block p-2.5 row-span-1"
          placeholder="Username"
          onChange={(e) => setUser(e.target.value)}
          value={user}
          required
          autoComplete="off"
        />
        <input
          type="password"
          id="password"
          className="bg-slate-200 text-gray-900 outline-none text-sm rounded-lg block p-2.5 row-span-1"
          placeholder="Password"
          onChange={(e) => setPwd(e.target.value)}
          value={pwd}
          required
          autoComplete="off"
        />
        <button
          onClick={handleSubmit}
          className="bg-slate-800 text-white font-bold rounded-xl row-span-1"
        >
          Login
        </button>
      </div>
    </div >
  )
}
