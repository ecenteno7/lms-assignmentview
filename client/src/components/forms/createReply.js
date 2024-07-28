import { useStompClient } from "react-stomp-hooks"
import { createDiscussionPostReply } from "../../services/api"
import { useEffect, useState } from "react"


export const CreateReply = ({ authorId, courseId, discussionPostId, refreshReplies, mode }) => {
  const [reply, setReply] = useState("")
  const stompClient = useStompClient();

  const handleSubmit = () => {
    if (mode === 'socket') {
      if (stompClient) {
        stompClient.publish({
          destination: "/app/chatter/course-id",
          body: JSON.stringify({'authorID': 'test', 'content': 'test'})
        })
      }
    }
    else {
      createDiscussionPostReply(authorId, courseId, discussionPostId, reply)
      .then(res => {
        if (res.status == 200) {
          refreshReplies()
          setReply("")
        }
      })
    }
  }

  useEffect(() => {
    // TODO dynamically re-render the component to change the size of the text box
  }, [reply])

  return (
    <div className="pb-4 pt-4 h-fit flex flex-row">
      <textarea onChange={e => setReply(e.target.value)} value={reply} type="text" id="search" class="bg-slate-200 text-gray-900 text-sm outline-none rounded-xl w-full h-full p-2.5 " placeholder="Type your response here..." />
      <button onClick={handleSubmit} className="ml-2 bg-slate-800 rounded-xl text-white font-bold p-2">Submit</button>
    </div>
  )
}
