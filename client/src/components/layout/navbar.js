import { FaHome } from "react-icons/fa";
import { CourseFocusContext } from "../../context/courseFocusContext.js"
import { useContext, useState } from "react"

export const Navbar = () => {

  const { setSelectedNav } = useContext(CourseFocusContext)
  const [discussionColor, setDiscussionColor] = useState("")
  const [practiceColor, setPracticeColor] = useState("")
  const [contentColor, setContentColor] = useState("")
  const [homeColor, setHomeColor] = useState("bg-slate-600 text-white")

  const handleClick = (nav, setComponentColor) => {
    // Reset navbar
    setDiscussionColor("")
    setPracticeColor("")
    setContentColor("")
    setHomeColor("")

    // Change selected nav component to dark
    setComponentColor("bg-slate-600 text-white")
    setSelectedNav(nav)
  }

  return (
    <div className="grid grid-cols-7 h-full rounded-xl p-2 cursor-pointer">
      <div onClick={() => handleClick("home", setHomeColor)} className={`flex col-span-1 text-slate-800 font-bold rounded-xl justify-center items-center ${homeColor}`}>
        <FaHome className="text-2xl" />
      </div>
      <div onClick={() => handleClick("content", setContentColor)} className={`col-span-2 flex text-slate-800 font-bold rounded-xl justify-center items-center ${contentColor}`}>
        <p className="text-center">Content</p>
      </div>
      <div onClick={() => handleClick("practice", setPracticeColor)} className={`col-span-2 flex text-slate-800 font-bold rounded-xl justify-center items-center ${practiceColor}`}>
        Practice
      </div>
      <div onClick={() => handleClick("discussion", setDiscussionColor)} className={`col-span-2 flex text-slate-800 font-bold rounded-xl justify-center items-center ${discussionColor}`}>
        Discussion
      </div>
    </div >
  )
}
