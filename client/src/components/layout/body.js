import { useContext, useEffect, useState } from "react"
import { CourseFocusContext } from "../../context/courseFocusContext"
import { getDiscussionPostDetails } from "../../services/api";
import { CreatePost } from "../forms/createPost";

export const Body = () => {
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
    <div className="w-2/3 bg-slate-400 rounded-xl flex flex-row justify-center text-slate-800">
      {
        postDetails &&
        (
          courseFocus.discussionPostFocus == 'CREATE' ?
            <CreatePost />
            :
            <div className="m-4 rounded-xl bg-slate-200 max-h-48 w-full">
              <p className="font-bold text-2xl text-left p-4">{postDetails.title}</p>
              <p className="font-semibold text-xl text-left pl-4">{`${postDetails.firstName} ${postDetails.lastName}`}</p>
              <p className="text-left p-4">{postDetails.content}</p>
              {postDetails.tags.map(tag => {
                return (
                  <div className="w-fit h-1/6 ml-4 flex justify-center items-center rounded-xl bg-slate-800 p-3 text-white font-semibold">
                    <p className="text-left">{tag.name}</p>
                  </div>
                )
              })}
            </div>
        )
      }
    </div >

  )

}
