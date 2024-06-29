import { useContext, useEffect, useState } from "react"
import { CourseFocusContext } from "../../context/courseFocusContext"
import { getDiscussionPostDetails } from "../../services/api";


export const Body = () => {
  const { courseFocus } = useContext(CourseFocusContext);

  const [postDetails, setPostDetails] = useState(null);

  useEffect(() => {
    if (courseFocus.courseId == null) {
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
      <div className="m-4 rounded-xl bg-slate-200 max-h-48 w-full">
        {postDetails &&
          (
            <>
              <p className="font-bold text-2xl text-left p-4">{postDetails.title}</p>
              <p className="font-semibold text-xl text-left pl-4">{`${postDetails.firstName} ${postDetails.lastName}`}</p>
              <p className="text-left p-4">{postDetails.content}</p>
            </>
          )
        }
      </div>
    </div>

  )

}
