// AuthProvider.js
import { createContext, useState } from "react";

export const CourseFocusContext = createContext({});

export const CourseFocusProvider = ({ children }) => {
  const [courseFocus, setState] = useState({
    discussionPostFocus: null,
    courseId: null
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


  return (
    <CourseFocusContext.Provider value={{ courseFocus, setDiscussionPostFocus, setCourseId }}>
      {children}
    </CourseFocusContext.Provider>
  );
};

