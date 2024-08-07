import { useContext, useEffect, useState } from "react";
import { GrStatusGoodSmall } from "react-icons/gr";
import { useStompClient } from "react-stomp-hooks";
import { CourseFocusContext } from "../../context/courseFocusContext";
import { CreateReply } from "../forms/createReply";
import { MessageFeed } from "./messageFeed";


export const Chatter = () => {
  const { courseFocus, setChatterActive } = useContext(CourseFocusContext)
  const [activeElement, setActiveElement] = useState(null)

  const handleClick = () => {
    setChatterActive(true);
  }

  const ChatterButton = () => {
    return (
      <div onClick={handleClick} className="row-span-1 grid grid-cols-12 justify-center items-center cursor-pointer">
        <GrStatusGoodSmall className="text-2xl text-green-500 col-span-1 ml-4" />
        <p className="text-xl text-slate-800 font-bold col-span-10">Chatter</p>
      </div>
    )
  }

  const stompClient = useStompClient();

  const ChatterWindow = () => {
    return (
      <div className="w-full pb-4 px-4">
        <p className="text-left text-xl text-slate-800 font-bold h-[10%] flex flex-row items-center">Hallway Chatter</p>
        <MessageFeed />
        <CreateReply stompClient={stompClient} mode="socket" />
      </div>
    )
  }

  useEffect(() => {
    if (!courseFocus.chatterActive) {
      setActiveElement(<ChatterButton />)
      return
    }

    setActiveElement(<ChatterWindow />)
  }, [courseFocus.chatterActive])

  return (
    <div className="w-full h-full rounded-lg bg-slate-400 flex justify-center">
      {activeElement}
    </div >
  )
}
