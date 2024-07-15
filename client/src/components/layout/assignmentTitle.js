export const AssignmentTitle = ({ assignment }) => {
  return assignment.title && (
    <div className="mb-2 rounded-xl bg-slate-200 w-full">
      <p className="font-bold text-2xl text-left p-4">{assignment.title}</p>
      <p className="text-left px-4 pb-4">{assignment.description}</p>
    </div>
  )
}
