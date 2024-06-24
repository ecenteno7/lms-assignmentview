import { AuthContext } from "../../context/authContext"
import { useContext } from "react"
import { GrStatusGoodSmall } from "react-icons/gr";

export const Header = () => {
  const { auth } = useContext(AuthContext);

  return (
    <div className="bg-slate-800 text-white font-bold text-3xl grid grid-cols-6 pt-4 pb-4">
      <div className="col-span-1 text-left pl-10 flex items-center" >
        <p className="">Blink</p>
      </div>
      <div className="col-span-4 text-left align-middle flex justify-center items-center">
        {auth && (<p className="">{auth.course.coursePrefix}{auth.course.courseNumber}: {auth.course.courseName}</p>)}
      </div>
      <div className="w-full col-span-1 flex justify-end items-end pr-10">
        {auth && <div className="text-center w-12 h-12 text-2xl rounded-[50%] bg-slate-400 flex justify-center items-center cursor-pointer">
          <p className="text-white">{auth.userDetails.firstName[0]}{auth.userDetails.lastName[0]}</p>
        </div>}
      </div>
    </div >
  )
}

