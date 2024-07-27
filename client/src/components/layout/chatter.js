import { useContext, useEffect, useState } from "react";
import { GrStatusGoodSmall } from "react-icons/gr";
import { CourseFocusContext } from "../../context/courseFocusContext";
// import { getCourseChatter } from "../../services/api";
// import { connect } from "../../services/socket";
import { CreateReply } from "../forms/createReply";
import { MessageFeed } from "./messageFeed";
import { StompSessionProvider, useSubscription } from "react-stomp-hooks";


export const Chatter = () => {
  const { courseFocus, setChatterActive } = useContext(CourseFocusContext)
  const [activeElement, setActiveElement] = useState(null)
  const [msgHistory, setMsgHistory] = useState(null)
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

  const ChatterWindow = () => {
    return (
      <div className="w-full p-4">
        <MessageFeed />
        <CreateReply mode="socket"/>
      </div>
    )
  }

  const handleMessageRx = (msg) => {
    setMsgHistory([...msgHistory, msg])
  }

  useEffect(() => {
    // getCourseChatter(courseFocus.courseId).then(res => {
    //   setMsgHistory(res.messages);
    // });
    // connect(courseFocus.courseId, handleMessageRx);
  }, [])

  useEffect(() => {
    if (!courseFocus.chatterActive) {
      setActiveElement(<ChatterButton />)
      return
    }

    setActiveElement(<ChatterWindow />)
  }, [courseFocus.chatterActive])

  return (
    <div className="w-full h-full rounded-lg bg-slate-400 flex justify-center">
      <StompSessionProvider url={"http://localhost:8080/websocket"}>
        {activeElement}
      </StompSessionProvider>
    </div >
  )
}
