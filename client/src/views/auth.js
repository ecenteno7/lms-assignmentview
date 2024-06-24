import { useState, useEffect, useContext } from "react";
import { AuthContext } from "../context/authContext";
import { userLogin } from "../services/api"


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
    userLogin(user, pwd).then((res) => {
      if (res.status == 200) {
        console.log(res.data)
        setAuth(res.data)
        setSuccess(res.status)
      } else {
        setErrMsg(`ERR: ${res.status} - Error logging in.`)
        console.log("err logging in")
      }
    });
  }

  return (
    <div className="w-full h-full flex flex-col items-center justify-center">
      <div className="pb-10 w-2/5">
        <p className="text-3xl font-bold text-slate-800 ">Login</p>
      </div>
      <div className="h-2/3 grid grid-rows-10 w-2/5 gap-y-4 flex justify-center items-center">
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
          className="bg-slate-800 text-white font-bold rounded-xl h-full row-span-1"
        >
          Login
        </button>
      </div>
    </div >
  )
}
