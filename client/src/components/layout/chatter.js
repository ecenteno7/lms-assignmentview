import { useContext } from "react";
import { GrStatusGoodSmall } from "react-icons/gr";
import { CourseFocusContext } from "../../context/courseFocusContext";


export const Chatter = () => {
  const { courseFocus, setChatterActive } = useContext(CourseFocusContext)

  const handleClick = () => {
    setChatterActive(true);
  }

  return (
    <div onClick={handleClick} className="bg-slate-400 w-full rounded-lg row-span-1 grid grid-cols-12 justify-center items-center cursor-pointer">
      <GrStatusGoodSmall className="text-2xl text-green-500 col-span-1 ml-4" />
      <p className="text-xl text-slate-800 font-bold col-span-10">Chatter</p>
    </div>

  )
}
