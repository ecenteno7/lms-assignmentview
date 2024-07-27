import { useState, useEffect, useContext } from "react"
import { CourseFocusContext } from "../context/courseFocusContext"
import { getAssignmentList } from "../services/api"
import { InsightList } from "../components/layout/insightList"

export const PracticeSidebar = () => {
  const [assignmentList, setAssignmentList] = useState([])

  const { courseFocus, setAssignmentFocus } = useContext(CourseFocusContext)

  const fetchCockpitInit = () => {
    if (courseFocus.courseId == null) {
      return
    }

    getAssignmentList(courseFocus.courseId).then((res) => {
      const listElements = createListElements(res.data.assignments)
      setAssignmentList(listElements)
    })
  }

  const createListElements = (els) => {
    if (els && els.length == 0) {
      return (<></>)
    }

    const elements = els.map((el) => {
      console.log(el)
      return (
        <div onClick={() => setAssignmentFocus(el.assignmentID)} className="bg-slate-200 rounded-lg w-full mb-2 p-4 text-left font-bold cursor-pointer">
          <p>{el.title}</p>
        </div>
      )
    })

    return elements

  }

  useEffect(() => {
    fetchCockpitInit()
  }, [courseFocus])

  return (
    <>
      <div className="w-full h-full flex flex-col items-center p-4" >
        {courseFocus.assignmentFocus != null ? <InsightList /> : assignmentList}
      </div>
    </>
  )
}

