import { createContext, useState } from "react";

export const CourseFocusContext = createContext({});

export const CourseFocusProvider = ({ children }) => {
  const [courseFocus, setState] = useState({
    discussionPostFocus: null,
    courseId: null,
    selectedNav: 'home',
    assignmentFocus: null,
    assignmentInsightFocus: null,
    chatterActive: false,
  })

  const setDiscussionPostFocus = (id) => {
    setState(prevState => ({
      ...prevState,
      discussionPostFocus: id
    }))
  }

  const setCourseId = (id) => {
    setState(prevState => ({
      ...prevState,
      courseId: id
    }))
  }

  const setSelectedNav = (id) => {
    setState(prevState => ({
      ...prevState,
      selectedNav: id
    }))
  }

  const setAssignmentFocus = (id) => {
    setState(prevState => ({
      ...prevState,
      assignmentFocus: id
    }))
  }

  const setAssignmentInsightFocus = (id) => {
    setState(prevState => ({
      ...prevState,
      assignmentInsightFocus: id
    }))
  }

  const setChatterActive = (id) => {
    setState(prevState => ({
      ...prevState,
      chatterActive: id
    }))
  }

  return (
    <CourseFocusContext.Provider value={{ courseFocus, setDiscussionPostFocus, setCourseId, setSelectedNav, setAssignmentFocus, setAssignmentInsightFocus, setChatterActive }}>
      {children}
    </CourseFocusContext.Provider>
  );
};

