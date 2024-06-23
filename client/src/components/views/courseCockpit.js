
import { Header } from '../layout/header'
import { Sidebar } from '../layout/sidebar'
import { Body } from '../layout/body';

export const CourseCockpit = () => {
  return (
    <div className="h-screen">
      <Header />
      <div className="flex h-5/6 m-4">
        <Sidebar />
        <Body />
      </div>
    </div >

  )
}
