export const AssignmentModule = ({ module }) => {
  return (
    <div className="pb-2">
    <div className="rounded-xl bg-slate-200 w-full h-max max-h-80 overflow-auto">
      <p className="font-bold text-2xl text-left p-4">{module.title}</p>
      <p className="text-left px-4 pb-4">{module.description}</p>
    </div>
    </div>
  )
}
