import { useContext, useEffect, useState, useRef } from "react"
import { CourseFocusContext } from "../../context/courseFocusContext"
import { fetchAuthorDetails, markAcceptedAnswer } from "../../services/api"
import { TfiLayoutMenuSeparated } from "react-icons/tfi"
import { ReplyMenu } from "../forms/replyMenu"

export const Reply = ({ refreshReplies, replyDetails, acceptedAnswerId }) => {
  const [author, setAuthor] = useState({})
  const [showMenu, setShowMenu] = useState(false)

  const { courseFocus } = useContext(CourseFocusContext)

  const useOutsideAlerter = (ref) => {
    useEffect(() => {
        const handleClickOut = (event) => ref.current && !ref.current.contains(event.target) ? setShowMenu(false) : null
        
        document.addEventListener("mousedown", handleClickOut)
        return () => document.removeEventListener("mousedown", handleClickOut)
        
    }, [ref])
  }
    
  const clickRef = useRef(null)
  useOutsideAlerter(clickRef)

  const handleMenu = () => {
    setShowMenu(!showMenu)
  }

  const handleMarkAccepted = () => {
    if (acceptedAnswerId){
      markAcceptedAnswer(acceptedAnswerId, false, courseFocus.courseId, replyDetails.discussionPostID)
      .then((res) => {
        if (res.status == 200){
          markAcceptedAnswer(replyDetails.discussionResponseID, true, courseFocus.courseId, replyDetails.discussionPostID) 
            .then((res) => {
              if (res.status == 200) {
                handleMenu()
                refreshReplies()
              }
            })
      }
    })
    } else {
      markAcceptedAnswer(replyDetails.discussionResponseID, true, courseFocus.courseId, replyDetails.discussionPostID)
        .then((res) => {
          if (res.status == 200){
            handleMenu()
            refreshReplies()
          }
        })
    }
  }

  const ReplyMenuComponent = () => {
      if (showMenu) {
       return <ReplyMenu clickRef={clickRef} handleMarkAccepted={handleMarkAccepted}/>
      } else {
       return (
        <div className="h-full w-fit flex flex-col justify-center cursor-pointer" onClick={handleMenu}>
          <TfiLayoutMenuSeparated />
        </div>
       )
    } 
  }
  
  useEffect(() => {
    fetchAuthorDetails(replyDetails.authorID, courseFocus.courseId).then((res) => {
      if (res.status == 200) {
        setAuthor(res.data)
      }
    })
  }, [])

  return (
    <div className="rounded-xl mt-2 bg-slate-200 w-full h-max max-h-80 overflow-auto">
      <p className="text-left p-4">{replyDetails.content}</p>
      <p className="text-left pl-4 pb-2 text-sm font-semibold">{new Date(replyDetails.createdOn).toUTCString()}</p>
      <div className="w-full flex flex-row">
        <div className="w-2/3 flex flex-row items-center">
          <div className="flex flex-row items-center justify-center pt-2 pb-2">
          <div className="w-fit h-10 ml-4 flex justify-center items-center rounded-xl bg-slate-800 p-3 text-white font-semibold">
            <p className="text-left">{`${author.firstName} ${author.lastName}`}</p>
          </div>
          </div>
        </div>
        <div className="w-full flex flex-row justify-end items-center pr-4 mb-2">
          <ReplyMenuComponent />
        </div>
      </div>
    </div>
  )
}
