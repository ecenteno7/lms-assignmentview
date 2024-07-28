import { useContext, useEffect, useState } from "react"
import { CourseFocusContext } from "../../context/courseFocusContext"
import { DiscussionPostModule } from "../../views/discussionPost.js"
import { HomeModule } from "../../views/homeModule"
import { PracticeModule } from "../../views/practiceModule"

export const Body = () => {
  const { courseFocus } = useContext(CourseFocusContext);

  const selectActiveModule = () => {
    if(!courseFocus) {
      return
    }

    if (courseFocus.selectedNav == "home") {
      return (
        <HomeModule />
      )
    }

    if (courseFocus.selectedNav == "content") {
      return (
        <div className="bg-slate-200 text-slate-800 p-4 m-4 rounded-xl font-bold text-2xl w-full">
          Content
        </div>
      )
    }

    if (courseFocus.selectedNav == "practice") {
      return <PracticeModule />
    }

    if (courseFocus.selectedNav == "discussion") {
      return <DiscussionPostModule />
    }
  }


  return (
    <div className="w-2/3 p-4 bg-slate-400 rounded-xl flex flex-row justify-center text-slate-800">
      { selectActiveModule() }
    </div >

  )

}
