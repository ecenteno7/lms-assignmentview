import { Searchbar } from "../components/forms/searchbar"
import { IoCreate } from "react-icons/io5";
import { useState, useEffect, useContext } from "react"
import { CourseFocusContext } from "../context/courseFocusContext"
import { getDiscussionPostList } from "../services/api"

export const DiscussionPostSidebar = () => {
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
    <>
      <div className="w-full flex flex-row flex flex-row justify-center items-center h-[12%]">
        <Searchbar />
        <div className="w-1/6 pt-4 pb-4 pr-4 pl-4 h-full">
          <button onClick={() => setDiscussionPostFocus("CREATE")} className="bg-slate-800 text-white font-bold rounded-xl p-2.5 w-full h-full text-center flex flex-row justify-center items-center">
            <IoCreate className="text-3xl text-center w-full h-full" />
          </button>
        </div>
      </div>
      <div className="overflow-y-scroll h-5/6 ml-2 mr-2">
        {discussionPostList}
      </div>
    </>
  )
}

