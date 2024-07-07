export const ReplyMenu = ({handleMarkAccepted}) => {
  return (
    <div className="bg-slate-400 w-1/3 h-full rounded-xl mr-2">
      <div onClick={handleMarkAccepted} className="p-2 hover:bg-slate-300 rounded-md cursor-pointer">
        <p className="text-slate-800 font-semibold text-xs">Mark Accepted Answer</p> 
      </div>
      <div className="p-2 hover:bg-slate-300 rounded-md cursor-pointer">
        <p className="text-slate-800 font-semibold text-xs">Delete</p> 
      </div>
    </div>
  )
}
