import { useContext, useEffect, useState } from "react"
import { CourseFocusContext } from "../../context/courseFocusContext"
import { fetchAuthorDetails } from "../../services/api"

export const Reply = ({ replyDetails }) => {

  const [author, setAuthor] = useState({})
  const { courseFocus } = useContext(CourseFocusContext)

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
      <div className="w-fit h-10 ml-4 flex justify-center items-center rounded-xl bg-slate-800 p-3 mb-4 text-white font-semibold">
        <p className="text-left">{`${author.firstName} ${author.lastName}`}</p>
      </div>
    </div>
  )
}
