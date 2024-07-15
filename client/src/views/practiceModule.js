import { useContext, useEffect, useState } from "react"
import { CourseFocusContext } from "../context/courseFocusContext"
import { getAssignmentDetails } from "../services/api";
import { AssignmentTitle } from "../components/layout/assignmentTitle"
import { AssignmentModule } from "../components/layout/assignmentModule";

export const PracticeModule = () => {

  const { courseFocus } = useContext(CourseFocusContext);

  const [assignment, setAssignment] = useState({})

  const fetchAssignment = () => {
    getAssignmentDetails(courseFocus.courseId, courseFocus.assignmentFocus).then(res => {
      if (res.status == 200) {
        return res.data
      }
      return res
    }).then(data => {
      setAssignment(data.assignments[0])
    })
  }

  const renderAssignment = () => {
    console.log(assignment.modules)
    let id = 0
    const modules = assignment.modules.map((module) => {
      id++
      return <AssignmentModule id={id} module={module} />
    })
    return modules
  }

  useEffect(() => {
    if (courseFocus.assignmentFocus == null) {
      return
    }
    fetchAssignment(courseFocus.courseId, courseFocus.assignmentId)
  }, [courseFocus])

  return (
    <div id="practice-module" className="flex flex-col w-full">
      {assignment && <AssignmentTitle id="assignment-title" assignment={assignment} />}
      {assignment.modules && renderAssignment()}
    </div>
  )
}
