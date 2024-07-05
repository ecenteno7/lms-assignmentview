import { useContext, useEffect, useState } from "react"
import { CourseFocusContext } from "../context/courseFocusContext"
import { CreatePost } from "../components/forms/createPost";
import { getDiscussionPostDetails } from "../services/api";

export const PracticeSidebar = () => {

  const { courseFocus } = useContext(CourseFocusContext);

  useEffect(() => {

  }, [])
  
  return (
    <div className="p-4">
      <div className="rounded-xl p-4 bg-slate-200 font-bold w-full h-max max-h-80">
        Assignment 1 
      </div>
    </div>
  ) 
}
