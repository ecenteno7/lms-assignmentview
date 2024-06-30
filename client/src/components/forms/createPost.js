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
  const [submitted, setSubmitted] = useState(true)

  const { auth } = useContext(AuthContext)
  const { courseFocus, setDiscussionPostFocus } = useContext(CourseFocusContext)

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
          setDiscussionPostFocus("")
        } else {
          console.log(`Error creating post: ${res.status}`)
        }
      })
  }

  const renderTags = () => {
    getTags(courseFocus.courseId).then(res => {
      let count = 0
      const tagList = res.data.tags.map(tag => {
        if (count == 0) {
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
      <div id="create-post" className="w-full flex flex-col justify-center items-center">
        <p className="text-slate-800 text-left w-full pt-4 pl-4 font-bold text-3xl">Create Discussion Post</p>
        <p className="text-slate-800 text-left w-full pt-4 pl-4 font-bold text-xl">Title</p>
        <div className="p-4 w-full h-[15%]">
          <input type="text" id="title" onChange={e => setTitle(e.target.value)} class="h-full bg-slate-200 text-gray-900 outline-none text-sm rounded-lg block w-full p-2.5 " placeholder="Title" />
        </div>
        <p className="text-slate-800 text-left w-full pl-4 font-bold text-xl">Content</p>
        <div className="p-4 w-full h-[65%]">
          <textarea type="text" id="content" onChange={e => setContent(e.target.value)} class="text-start justify-start items-start h-full bg-slate-200 text-gray-900 outline-none text-sm rounded-lg block w-full p-2.5 " placeholder="Content" />
        </div>
        <p className="text-slate-800 text-left w-full pl-4 font-bold text-xl">Tag</p>
        <div className="p-4 h-[10%] w-full">
          {tags && <select onChange={e => setTag([{ tagID: e.target.value }])} name="Tags" className="rounded-xl p-2 mb-1 w-full ">
            {tags}
          </select>
          }
        </div>
        <div className="flex flex-row h-[10%] w-[25%] justify-center items-center pl-4 pb-4">
          <button onClick={handleSubmit} className="p-2 bg-slate-800 text-white font-bold rounded-xl h-full w-full">Submit</button>
        </div>
      </div>
      :
      <div id="submitted-post" className="w-full"></div>
  )
}
