import {createDiscussionPostReply} from "../../services/api"
import {useState} from "react"


export const CreateReply = ({authorId, courseId, discussionPostId, refresh}) => {
  const [reply, setReply] = useState("")
  
  const handleSubmit = () => {
    createDiscussionPostReply(authorId, courseId, discussionPostId, reply)
      .then(res => {
        if (res.status == 200){
          refresh()
        }
      })   
  }

  return (
    <div className="pb-4 pt-4 h-60 flex flex-row">
      <textarea onChange={e=>setReply(e.target.value)} type="text" id="search" class="bg-slate-200 text-gray-900 text-sm outline-none rounded-xl w-full h-full p-2.5 " placeholder="Type your response here..." />
      <button onClick={handleSubmit} className="ml-2 bg-slate-800 rounded-xl text-white font-bold p-2">Submit</button> 
      </div>
  )
}
