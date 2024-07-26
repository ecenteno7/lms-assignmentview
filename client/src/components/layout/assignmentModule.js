import {SelectableText} from "../forms/selectableText"

export const AssignmentModule = ({ module, setSelection }) => {
  return (
    <div className="pb-2">
    <p className="text-slate-800 font-bold text-2xl text-left my-2">Module {module.moduleNumber}</p>
    <div className="rounded-xl bg-slate-200 w-full h-max max-h-80 overflow-auto">
      <p className="font-bold text-2xl text-left p-4">{module.title}</p>
      <SelectableText description={module.description} setSelection={setSelection}/>
    </div>
    </div>
  )
}
