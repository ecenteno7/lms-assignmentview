import { useContext, useEffect, useState } from "react"
import { CourseFocusContext } from "../context/courseFocusContext"
import { getAssignmentDetails } from "../services/api";
import { AssignmentTitle } from "../components/layout/assignmentTitle"
import { AssignmentModule } from "../components/layout/assignmentModule";
import { SelectableText } from "../components/forms/selectableText";
import { CreatePost } from "../components/forms/createPost";
import { Modal } from "../components/forms/modal";
export const PracticeModule = () => {

  const { courseFocus, setChatterActive } = useContext(CourseFocusContext);

  const [assignment, setAssignment] = useState({})
  const [isModalOpen, setModalOpen] = useState(false);
  const [selectedText, setSelectedText] = useState('');

  const [buttonPos, setButtonPos] = useState({
    top: 0, left: 0
  })

  const handleOpenModal = () => {
    setModalOpen(true);
  }

  const handleCloseModal = () => {
    setModalOpen(false);
  }

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
      return <AssignmentModule id={id} module={module} setSelection={setSelectedText} />
    })
    return modules
  }

  useEffect(() => {
    if (courseFocus.assignmentFocus == null) {
      return
    }
    fetchAssignment(courseFocus.courseId, courseFocus.assignmentId)
  }, [courseFocus])


  useEffect(() => {
    if (selectedText) {
      setButtonPos({
        top: selectedText.top, left: selectedText.left
      })
    } else {
      setButtonPos({
        top: 0, left: 0
      })
    }
  }, [selectedText])

  useEffect(() => {
    setChatterActive(false);
  }, [])

  return (
    <div id="practice-module" className="flex flex-col w-full overflow-auto">
      {assignment && <AssignmentTitle id="assignment-title" assignment={assignment} />}
      {assignment.modules && renderAssignment()}
      {selectedText && <button onClick={handleOpenModal} className="fixed rounded-xl bg-slate-400 px-2" style={{ top: buttonPos.top - 15, left: buttonPos.left - 15 }}>+</button>}
      {isModalOpen && <Modal onClose={handleCloseModal} isOpen={isModalOpen}><CreatePost setModalOpen={setModalOpen} selection={selectedText} assignmentTagId={assignment.tagID} /></Modal>}
    </div>
  )
}
