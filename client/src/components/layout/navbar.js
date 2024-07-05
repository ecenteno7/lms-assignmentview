import { FaHome } from "react-icons/fa";
import { CourseFocusContext } from "../../context/courseFocusContext.js"
import { useContext } from "react"

export const Navbar = () => {

  const {courseFocus, setSelectedNav} = useContext(CourseFocusContext)

  return (
    <div className="grid grid-cols-7 h-full rounded-xl p-2 cursor-pointer">
      <div onClick={()=>setSelectedNav("home")} className="flex col-span-1 bg-slate-600 text-white font-bold rounded-xl justify-center items-center">
        <FaHome className="text-2xl"/>
      </div>
      <div onClick={()=>setSelectedNav("content")} className="col-span-2 flex text-slate-800 font-bold rounded-xl justify-center items-center">
        <p className="text-center">Content</p>
      </div>
      <div onClick={()=>setSelectedNav("practice")} className="col-span-2 flex text-slate-800 font-bold rounded-xl justify-center items-center">
        Practice
      </div>
      <div onClick={()=>setSelectedNav("discussion")} className="col-span-2 flex text-slate-800 font-bold rounded-xl justify-center items-center">
        Discussion
      </div>
    </div >
  )
}
