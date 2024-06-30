import { useContext, useEffect, useState } from "react"
import { createDiscussionPost } from "../../services/api"
import { AuthContext } from "../../context/authContext"
import { CourseFocusContext } from "../../context/courseFocusContext"
import { getTags } from "../../services/api"

export const CreatePost = () => {
  const [title, setTitle] = useState("")
  const [content, setContent] = useState("")
  const [tag, setTag] = useState([])
  const [tags, setTags] = useState([])
  const { auth } = useContext(AuthContext)
  const { courseFocus } = useContext(CourseFocusContext)
  const [submitted, setSubmitted] = useState(true)

  const handleSubmit = () => {
    createDiscussionPost(courseFocus.courseId,
      {
        discussionPosts: [
          {
            authorID: auth.userDetails.userID,
            title: title,
            content: content,
            tags: tag
          }
        ]
      }).then(res => {
        if (res.status == 200) {
          console.log("Post created succesfully!")
          setSubmitted(false)
        } else {
          console.log(`Error creating post: ${res.status}`)
        }
      })
  }

  const renderTags = () => {
    getTags(courseFocus.courseId).then(res => {
      let count = 0
      const tagList = res.data.tags.map(tag => {
        if (count == 0){
          setTag([
            { tagID: tag.tagID }
          ])
        }
        count++
        return (
          <option value={`${tag.tagID}`}> {tag.name}</option >
        )
      })
      setTags(tagList)
    })
  }

  useEffect(() => {
    renderTags()
  }, [])



  return (
    submitted ?
      <div id="create-post" className="w-full">
        <div className="p-4 w-full h-[15%]">
          <input type="text" id="title" onChange={e => setTitle(e.target.value)} class="h-full bg-slate-200 text-gray-900 outline-none text-sm rounded-lg block w-full p-2.5 " placeholder="Title" />
        </div>
        <div className="p-4 w-full h-[65%]">
          <input type="text" id="content" onChange={e => setContent(e.target.value)} class="text-start justify-start items-start h-full bg-slate-200 text-gray-900 outline-none text-sm rounded-lg block w-full p-2.5 " placeholder="Content" />
        </div>
        <div className="p-4 h-[10%]">
          {tags && <select onChange={e => setTag([{ tagID: e.target.value }])} name="Tags" className="rounded-xl p-2 mb-1 w-full ">
            {tags}
          </select>
          }
        </div>
        <div className="flex flex-col h-[10%] p-4">
          <button onClick={handleSubmit} className="p-2 bg-slate-800 text-white font-bold rounded-xl h-full">Submit</button>
        </div>
      </div>
      :
      <div id="submitted-post" className="w-full"></div>
  )
}
