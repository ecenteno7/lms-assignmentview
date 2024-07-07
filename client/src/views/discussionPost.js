import { useContext, useEffect, useState } from "react"
import { CourseFocusContext } from "../context/courseFocusContext"
import { AuthContext } from "../context/authContext"
import { CreatePost } from "../components/forms/createPost";
import { getDiscussionPostDetails } from "../services/api";
import { Post } from "../components/layout/post";
import { Reply } from "../components/layout/reply";
import { CreateReply } from "../components/forms/createReply";

export const DiscussionPostModule = () => {

  const { courseFocus } = useContext(CourseFocusContext);
  const { auth } = useContext(AuthContext);

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

  const refreshReplies = () => {
    getDiscussionPostDetails(courseFocus.courseId, courseFocus.discussionPostFocus)
      .then((post) => {
        if (post.responses.length != postDetails.responses.length) {
          setPostDetails(post)
        }
      })
      .catch(err => {
        console.log(`${err.message}: ${err.config.url}`)
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
      <div className="w-full">
        {postDetails && postDetails.responses.length > 0 && <p className="font-bold text-left text-xl">Accepted Answer</p>}
        {postDetails && postDetails.responses.length > 0 && <Reply replyDetails={{...postDetails.responses[0], discussionPostID: postDetails.discussionPostID}} />}
        {postDetails && postDetails.responses.length > 1 && <p className="font-bold text-left text-xl pt-2">Discussion</p>}
        {
          postDetails && postDetails.responses.length > 1 &&
          postDetails.responses.slice(1).map(reply => {
            const replyDetails = {
                ...reply,
                discussionPostID: postDetails.discussionPostID
              }
              return <Reply replyDetails={replyDetails} />
          })
        }
        <p className="font-bold text-left text-xl mt-2 pt-2">Reply</p>
        {postDetails && <CreateReply refresh={refreshReplies} authorId={auth.userDetails.userID} courseId={courseFocus.courseId} discussionPostId={postDetails.discussionPostID}/>}
      </div>
    </div>
  )
}
