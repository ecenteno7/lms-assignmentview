import { useState, useEffect, useContext } from "react"
import { CourseFocusContext } from "../../context/courseFocusContext"
import { getAssignmentDetails } from "../../services/api"
import { InsightView } from "./insightView"

export const InsightList = () => {
  const [insightList, setInsightList] = useState([])
  const { courseFocus, setAssignmentFocus, setAssignmentInsightFocus } = useContext(CourseFocusContext)

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

  const setInsight = post => {
    const insight = {
      title: post.title,
      question: post.content,
      acceptedAnswer: post.responses[0].content
    }

    setAssignmentInsightFocus(insight)
  }

  const createListElements = (els) => {
    if (els && els.length == 0) {
      return (<></>)
    }

    const elements = els.map((el) => {
      console.log(el)
      return (
        <div onClick={() => setInsight(el)} className="bg-slate-200 rounded-lg m-2 p-4 text-left font-bold cursor-pointer">
          <p>{el.content}</p>
        </div>
      )
    })

    return elements
  }

  const handleBack = () => {
    if (courseFocus.assignmentInsightFocus) {
      setAssignmentInsightFocus(null)
      return
    }

    if (courseFocus.assignmentFocus) {
      setAssignmentFocus(null)
      return
    }
  }

  return (
    <div className="w-full">
      <div className="grid grid-cols-12 items-center pb-4">
        <p className="col-span-8 font-bold text-left text-slate-800 text-xl">Assignment Insights</p>
        <div className="bg-slate-800 p-2 col-span-4 text-white font-bold rounded-xl flex flex-col items-center cursor-pointer" onClick={() => handleBack()}>
          <p className="text-right justify-center items-center">Back</p>
        </div>
      </div >
      {courseFocus.assignmentInsightFocus == null ? insightList : (<InsightView />)}
    </div >
  )
}
