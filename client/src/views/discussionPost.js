import { useContext, useEffect, useState } from "react"
import { CourseFocusContext } from "../context/courseFocusContext"
import { AuthContext } from "../context/authContext"
import { CreatePost } from "../components/forms/createPost";
import { getDiscussionPostDetails } from "../services/api";
import { Post } from "../components/layout/post";
import { Reply } from "../components/layout/reply";
import { CreateReply } from "../components/forms/createReply";
import { ReplyList } from "../components/layout/replyList"
export const DiscussionPostModule = () => {

  const { courseFocus } = useContext(CourseFocusContext);
  const { auth } = useContext(AuthContext);

  const [postDetails, setPostDetails] = useState(null);
  const [acceptedAnswer, setAcceptedAnswer] = useState(null);
  
  useEffect(() => {
    if (courseFocus.courseId == null || courseFocus.discussionPostFocus == 'CREATE') {
      return
    } else if (courseFocus.discussionPostFocus){
      refreshReplies()
    }
  }, [courseFocus])

  const refreshReplies = () => {
    setAcceptedAnswer(null)
    getDiscussionPostDetails(courseFocus.courseId, courseFocus.discussionPostFocus)
      .then(res => {
        if (res.status == 200) {
          return res.data.discussionPosts[0]
        }
      }).catch(err => {
        console.log(err)
      }).then(post => {
        setPostDetails(post)
          post.responses.map(reply => {
            if (reply.accepted) {
              setAcceptedAnswer(reply)
            }
          })
      })
  } 

  return (
    <div className="flex flex-col items-center w-full max-h-fit pl-4 pr-4 mb-2 overflow-y-auto">
      {
        courseFocus.discussionPostFocus == 'CREATE' ?
          <CreatePost />
          :
          postDetails &&
          <Post postDetails={postDetails} />
      }
      <ReplyList refreshReplies={refreshReplies} postDetails={postDetails} setPostDetails acceptedAnswer={acceptedAnswer} />
    </div>
  )
}
