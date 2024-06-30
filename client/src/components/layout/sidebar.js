import { Searchbar } from "../forms/searchbar"
import { Navbar } from "./navbar"
import { Chatter } from "./chatter"
import { useState, useEffect, useContext } from "react"
import { getDiscussionPostList } from "../../services/api"
import { CourseFocusContext } from "../../context/courseFocusContext"

export const Sidebar = () => {
  const [discussionPostList, setDiscussionPostList] = useState([])

  const { courseFocus, setDiscussionPostFocus } = useContext(CourseFocusContext)

  const fetchCockpitInit = () => {
    if (courseFocus.courseId == null) {
      return
    }

    getDiscussionPostList(courseFocus.courseId).then((res) => {
      const discussionPostListElements = createPostListElements(res.data.discussionPosts)
      setDiscussionPostList(discussionPostListElements)
    })
  }

  const createPostListElements = (posts) => {
    if (posts && posts.length == 0) {
      return (<></>)
    }

    const postElements = posts.map((post) => {
      return (
        <div onClick={() => setDiscussionPostFocus(post.discussionPostID)} className="bg-slate-200 rounded-lg m-2 p-4 text-left font-bold cursor-pointer">
          <p>{post.title}</p>
        </div>
      )
    })

    return postElements
  }

  useEffect(() => {
    fetchCockpitInit()
    console.log(discussionPostList)
    console.log(courseFocus)
  }, [courseFocus])

  return (
    <div className="w-1/3 grid grid-rows-12 space-y-3 mr-4">
      <div className="bg-slate-400 w-full row-span-1 rounded-lg">
        <Navbar />
      </div>
      <div className="bg-slate-400 w-full row-span-10 rounded-lg">
        <div className="w-full p-1 flex flex-row">
          <Searchbar />
          <button onClick={() => setDiscussionPostFocus("CREATE")} className="w-1/3 bg-slate-800 text-white font-bold rounded-xl p-1 mr-2 mt-2 mb-2">Create</button>
        </div>
        <div className="overflow-auto h-5/6">
          {discussionPostList}
        </div>
      </div>
      <Chatter />
    </div >
  )

}
