// AuthProvider.js
import { createContext, useState } from "react";

export const CourseFocusContext = createContext({});

export const CourseFocusProvider = ({ children }) => {
  const [courseFocus, setState] = useState({
    discussionPostFocus: null,
    courseId: null,
    selectedNav: 'home'
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

  return (
    <CourseFocusContext.Provider value={{ courseFocus, setDiscussionPostFocus, setCourseId, setSelectedNav }}>
      {children}
    </CourseFocusContext.Provider>
  );
};

