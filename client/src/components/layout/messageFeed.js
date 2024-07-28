import { useState, useEffect, useContext, useRef } from "react";
import { useSubscription } from "react-stomp-hooks"
import { CourseFocusContext } from "../../context/courseFocusContext";
import { getCourseChatter } from "../../services/api";

export const MessageFeed = () => {
  const { courseFocus } = useContext(CourseFocusContext)
  const messageRef = useRef();

  const msgHistory = useRef([])
  const [messageFeed, setMessageFeed] = useState([])

  const addMessage = (message) => {
    const newMessage = JSON.parse(message.body)
    setMessageFeed([...messageFeed, newMessage])
  }

  useSubscription(`/topic/chatter/${courseFocus.courseId}`, addMessage);

  useEffect(() => {
    if (msgHistory.current.length == 0) {
      getCourseChatter(courseFocus.courseId).then(res => {
        msgHistory.current = res.data.messages
        setMessageFeed(res.data.messages)
      })
    }
  }, [messageFeed])

  useEffect(() => {
    if (messageRef.current) {
      messageRef.current.scrollIntoView(
        {
          behavior: 'smooth',
          block: 'end',
          inline: 'nearest'
        })
    }
  }, [messageFeed])

  const MessageList = () => {
    return (
      <div>
        {messageFeed.map((msg) => {
          return <div className="flex flex-row">
            <p className="text-slate-400 mr-2">{`${new Date(msg.messageDateTime).getHours()}:${new Date(msg.messageDateTime).getMinutes()}`}</p>
            <p className="text-slate-800 font-bold">{msg.firstName} {msg.lastName.slice(0, 1)}:</p>
            <p className="ml-2">{msg.content}</p>
          </div>
        })} < div ref={messageRef}></div></div >
    )
  }

  return (
    <div className="w-full h-[80%] bg-slate-200 rounded-lg flex flex-col justify-start px-4 overflow-y-scroll text-left">
      <MessageList />
    </div>
  )
}
