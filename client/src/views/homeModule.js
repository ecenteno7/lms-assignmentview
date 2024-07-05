import { useContext, useEffect, useState } from "react" 
import { CourseFocusContext } from "../context/courseFocusContext"
import { CreatePost } from "../components/forms/createPost";
import { getDiscussionPostDetails } from "../services/api";

export const HomeModule = () => {

  const { courseFocus } = useContext(CourseFocusContext);

  useEffect(() => {

  }, [])
  
  return (
    <div className="m-4 rounded-xl bg-slate-200 w-full h-max max-h-80 overflow-auto">
      <p className="font-bold text-2xl text-left p-4">Whiteboard</p>
      <p className="font-semibold text-xl text-left pl-4">Welcome to class!</p>
      <p className="text-left p-4">Use the sidebar on your left to select a hot topic that I've got some important information listed out for.</p>
    </div>
  ) 
}
