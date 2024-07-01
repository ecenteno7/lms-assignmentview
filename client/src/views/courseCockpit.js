import { Sidebar } from '../components/layout/sidebar'
import { Body } from '../components/layout/body';
import { AuthContext } from '../context/authContext';
import { useContext, useEffect } from 'react';
import { CourseFocusContext } from '../context/courseFocusContext';

export const CourseCockpit = () => {
  const { auth } = useContext(AuthContext);
  const { setCourseId } = useContext(CourseFocusContext);

  useEffect(() => {
    setCourseId(auth.course.courseID)
  }, [])

  return (
    <div className="h-screen">
      <div className="flex h-5/6 m-4">
        <Sidebar />
        <Body />
      </div>
    </div >

  )
}
