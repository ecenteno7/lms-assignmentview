
import { Header } from '../components/layout/header'
import { Sidebar } from '../components/layout/sidebar'
import { Body } from '../components/layout/body';

export const CourseCockpit = () => {
  return (
    <div className="h-screen">
      <div className="flex h-5/6 m-4">
        <Sidebar />
        <Body />
      </div>
    </div >

  )
}
