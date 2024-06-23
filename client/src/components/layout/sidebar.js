import { Searchbar } from "../forms/searchbar"
import { Navbar } from "./navbar"
import { Chatter } from "./chatter"


export const Sidebar = () => {
  return (
    <div className="w-1/3 grid grid-rows-12 space-y-3 mr-4">
      <div className="bg-slate-400 w-full row-span-1 rounded-lg">
        <Navbar />
      </div>
      <div className="bg-slate-400 w-full row-span-10 rounded-lg">
        <Searchbar />
      </div>
    <Chatter />
    </div >
  )

}
