import { useStompClient } from "react-stomp-hooks"
import { createDiscussionPostReply } from "../../services/api"
import { useContext, useEffect, useState } from "react"
import { AuthContext } from "../../context/authContext"
import { CourseFocusContext } from "../../context/courseFocusContext"


export const CreateReply = ({ stompClient, authorId, courseId, discussionPostId, refreshReplies, mode }) => {
  const [reply, setReply] = useState("")

  const { auth } = useContext(AuthContext)
  const { courseFocus } = useContext(CourseFocusContext)

  const handleSubmit = (e) => {
    e.preventDefault()
    if (mode && mode === 'socket') {
      if (stompClient) {
        stompClient.publish({
          destination: `/app/chatter/${courseFocus.courseId}`,
          body: JSON.stringify({
            'authorID': auth.userDetails.userID, 'content': reply
          })
        })
      }
      setReply("")
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
    <form className="pb-4 pt-4 h-fit flex flex-row" onSubmit={handleSubmit}>
      {mode != "socket" ? <textarea onChange={e => setReply(e.target.value)} value={reply} type="text" id="search" class="bg-slate-200 text-gray-900 text-sm outline-none rounded-xl w-full h-full p-2.5 " placeholder="Type your response here..." />
        :
        <input onChange={e => setReply(e.target.value)} value={reply} type="text" id="search" class="bg-slate-200 text-gray-900 text-sm outline-none rounded-xl w-full h-full p-2.5 " placeholder="Type your response here..." />}
      <button type="submit" className="ml-2 bg-slate-800 rounded-xl text-white font-bold p-2">Submit</button>
    </form>
  )
}
