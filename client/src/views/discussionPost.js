import { useContext, useEffect, useState } from "react"
import { CourseFocusContext } from "../context/courseFocusContext"
import { CreatePost } from "../components/forms/createPost";
import { getDiscussionPostDetails } from "../services/api";

export const DiscussionPostModule = () => {

  const { courseFocus } = useContext(CourseFocusContext);
  const [postDetails, setPostDetails] = useState(null);

  useEffect(() => {
    if (courseFocus.courseId == null || courseFocus.discussionPostFocus == 'CREATE') {
      return
    }

    getDiscussionPostDetails(courseFocus.courseId, courseFocus.discussionPostFocus)
      .then((post) => {
        console.log(post)
        setPostDetails(post);
      })
      .catch((err) => {
        console.log(`${err.message}: ${err.config.url}`)
      })
  }, [courseFocus])
  
  return (
    courseFocus.discussionPostFocus == 'CREATE' ?
    <CreatePost />
        :
        postDetails &&
        <div className="m-4 rounded-xl bg-slate-200 w-full h-max max-h-80 overflow-auto">
          <p className="font-bold text-2xl text-left p-4">{postDetails.title}</p>
          <p className="font-semibold text-xl text-left pl-4">{`${postDetails.firstName} ${postDetails.lastName}`}</p>
          <p className="text-left p-4">{postDetails.content}</p>
          {postDetails.tags.map(tag => {
            return (
              <div className="w-fit h-10 ml-4 flex justify-center items-center rounded-xl bg-slate-800 p-3 mb-4 text-white font-semibold">
                <p className="text-left">{tag.name}</p>
              </div>
            )
          })}
        </div>
  ) 
}
