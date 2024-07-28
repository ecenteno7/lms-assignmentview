import { Searchbar } from "../forms/searchbar"
import { Navbar } from "./navbar"
import { Chatter } from "./chatter"
import { useState, useEffect, useContext } from "react"
import { getDiscussionPostList } from "../../services/api"
import { CourseFocusContext } from "../../context/courseFocusContext"
import { IoCreate } from "react-icons/io5";
import { DiscussionPostSidebar } from "../../views/discussionPostSidebar"
import { HomeSidebar } from "../../views/homeSidebar"
import { PracticeSidebar } from "../../views/practiceSidebar"

export const Sidebar = () => {
  const { courseFocus, setDiscussionPostFocus, setChatterActive } = useContext(CourseFocusContext)

  useEffect(() => {
    if (courseFocus.chatterActive) { }
  }, [courseFocus])

  const selectActiveModule = () => {
    if (!courseFocus) {
      return
    }

    if (courseFocus.selectedNav == "home") {
      return <HomeSidebar />
    }

    if (courseFocus.selectedNav == "content") {
      return (
        <div className="w-full p-4 text-slate-800 font-bold">
          <div className="w-full p-4 bg-slate-200 rounded-xl text-left">Content</div>
        </div>
      )
    }

    if (courseFocus.selectedNav == "practice") {
      return <PracticeSidebar />
    }

    if (courseFocus.selectedNav == "discussion") {
      return <DiscussionPostSidebar />
    }
  }


  return (
    <div className="w-1/3 grid grid-rows-12 space-y-3 mr-4">
      <div className="bg-slate-400 w-full row-span-1 rounded-lg">
        <Navbar />
      </div>
      {courseFocus.chatterActive ?
        <div onClick={() => setChatterActive(false)} className="hover:cursor-pointer bg-slate-400 rounded-lg flex justify-center items-center"><p className="text-xl text-slate-800 font-bold">Insights</p></div>
        :
        <div className="bg-slate-400 w-full row-span-10 rounded-lg">
          {selectActiveModule()}
        </div>}
      <div className="bg-slate-400 w-full row-span-10 rounded-lg">
        <Chatter />
      </div>
    </div >
  )

}
