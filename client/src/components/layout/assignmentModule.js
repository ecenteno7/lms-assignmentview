import { SelectableText } from "../forms/selectableText"
import { CourseFocusContext } from "../../context/courseFocusContext"
import { useContext, useState } from "react"

export const AssignmentModule = ({ module, setSelection }) => {
  const { setModuleHighlight } = useContext(CourseFocusContext)

  const setActiveModule = () => {
    setModuleHighlight(module)
  }

  return (
    <div onMouseEnter={setActiveModule} className="pb-2">
      <p className="text-slate-800 font-bold text-2xl text-left my-2">Module {module.moduleNumber}</p>
      <div className="rounded-xl bg-slate-200 w-full h-max max-h-80 overflow-auto">
        <p className="font-bold text-2xl text-left p-4">{module.title}</p>
        <SelectableText tag={module.tagID} module={module} setSelection={setSelection} />
      </div>
    </div>
  )
}
