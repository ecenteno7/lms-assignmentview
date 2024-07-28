import { useSubscription } from "react-stomp-hooks"

export const MessageFeed = () => {

  const addMessage = (message) => {
    console.log(message.content);
  }

  useSubscription("/topic/chatter/course-id", (message) => addMessage(message));
  

  return (
    <div className="w-full h-5/6 bg-slate-200 rounded-lg flex flex-row justify-start p-4">Message Feed</div>
  )
}
