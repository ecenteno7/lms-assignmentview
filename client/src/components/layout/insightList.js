import { useState, useEffect, useContext } from "react"
import { CourseFocusContext } from "../../context/courseFocusContext"
import { getAssignmentDetails } from "../../services/api"

export const InsightList = () => {
  const [insightList, setInsightList] = useState([])
  const { courseFocus } = useContext(CourseFocusContext)

  useEffect(() => {
    if (!courseFocus.assignmentFocus) {
      return
    }
    getAssignmentDetails(courseFocus.courseId, courseFocus.assignmentFocus).then(res => {
      if (res.status == 200) {
        return res.data
      }
    }).then(data => {
      const insightListElements = createListElements(data.assignments[0].insights)
      setInsightList(insightListElements)
    })
  }, [courseFocus])

  const createListElements = (els) => {
    if (els && els.length == 0) {
      return (<></>)
    }

    const elements = els.map((el) => {
      console.log(el)
      return (
        <div className="bg-slate-200 rounded-lg m-2 p-4 text-left font-bold cursor-pointer">
          <p>{el.title}</p>
        </div>
      )
    })

    return elements
  }

  return (
    <div className="w-full px-4">
      <p className="pt-4 pb-2 pl-2 font-bold text-left text-slate-800 text-xl">Assignment Insights</p>
      {insightList}
    </div>
  )
}
