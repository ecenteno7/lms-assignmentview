import { useContext, useState, useEffect } from "react"
import { CourseFocusContext } from "../../context/courseFocusContext"

export const InsightView = () => {
  const { courseFocus } = useContext(CourseFocusContext)


  return (
    <div className="w-full h-full overflow-auto bg-slate-200 rounded-xl p-4 text-left">

      <div>
        <p className="font-semibold text-xl pb-2">{courseFocus.assignmentInsightFocus.title}</p>
        <p className="font-semibold text-xl">Question</p>
        <p>{courseFocus.assignmentInsightFocus.question}</p>
        <p className="font-semibold text-xl">Answer</p>
        <p>{courseFocus.assignmentInsightFocus.acceptedAnswer}</p>
      </div>
    </div>
  )
}
