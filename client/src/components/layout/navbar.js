import { FaHome } from "react-icons/fa";

export const Navbar = () => {
  return (
    <div className="grid grid-cols-7 h-full rounded-xl p-2">
      <div className="flex col-span-1 bg-slate-600 text-white font-bold rounded-xl justify-center items-center">
        <FaHome className="text-2xl"/>
      </div>
      <div className="col-span-2 flex text-slate-800 font-bold rounded-xl justify-center items-center">
        <p className="text-center">Content</p>
      </div>
      <div className="col-span-2 flex text-slate-800 font-bold rounded-xl justify-center items-center">
        Practice
      </div>
      <div className="col-span-2 flex text-slate-800 font-bold rounded-xl justify-center items-center">
        Discussion
      </div>
    </div >
  )
}
