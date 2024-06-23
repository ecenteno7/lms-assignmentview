import { useState, useEffect } from "react";


export const Login = () => {
  const [user, setUser] = useState("");
  const [pwd, setPwd] = useState("");
  const [errMsg, setErrMsg] = useState("");
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    setErrMsg("");
  }, [user, pwd]);

  const handleSubmit = () => {
    console.log(user, pwd);
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
          autocomplete="off"
        />
        <input
          type="password"
          id="password"
          className="bg-slate-200 text-gray-900 outline-none text-sm rounded-lg block p-2.5 row-span-1"
          placeholder="Password"
          onChange={(e) => setPwd(e.target.value)}
          value={pwd}
          required
          autocomplete="off"
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
