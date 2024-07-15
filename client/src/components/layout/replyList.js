import { useContext } from "react"

import { CourseFocusContext } from "../../context/courseFocusContext"
import { AuthContext } from "../../context/authContext"
import { Reply } from "./reply"
import { CreateReply } from "../forms/createReply"
import { getDiscussionPostDetails } from "../../services/api"

export const ReplyList = ({refreshReplies, postDetails, setPostDetails, acceptedAnswer}) => {
      
  const { courseFocus } = useContext(CourseFocusContext);
  const { auth } = useContext(AuthContext);
      
  return (
    <div className="w-full">
      {acceptedAnswer && 
          <>
            <p className="font-bold text-left text-xl">Accepted Answer</p>
            <Reply refreshReplies={refreshReplies} replyDetails={{...acceptedAnswer, discussionPostID: postDetails.discussionPostID}} />
          </>
      }
      {postDetails && postDetails.responses.length >= 1 && <p className="font-bold text-left text-xl pt-2">Discussion</p>}
      {
        postDetails && postDetails.responses.length >= 1 &&
        postDetails.responses.map(reply => {
          const replyDetails = {
             ...reply,
             discussionPostID: postDetails.discussionPostID
            }
            return acceptedAnswer ? 
              <Reply refreshReplies={refreshReplies} replyDetails={replyDetails} acceptedAnswerId={acceptedAnswer.discussionResponseID}/>
              :
              <Reply refreshReplies={refreshReplies} replyDetails={replyDetails} acceptedAnswerId={acceptedAnswer}/>
        })
      }
      <p className="font-bold text-left text-xl mt-2 pt-2">Reply</p>
      {postDetails && <CreateReply refreshReplies={refreshReplies} authorId={auth.userDetails.userID} courseId={courseFocus.courseId} discussionPostId={postDetails.discussionPostID}/>}
    </div>
  )
}
