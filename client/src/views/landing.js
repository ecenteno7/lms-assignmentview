import { Login } from "./auth"
import { CourseCockpit } from "./courseCockpit"
import { useContext } from "react";
import { AuthContext } from "../context/authContext";

export const Landing = () => {

  const { auth } = useContext(AuthContext);

  return (
    auth === null ?
      (
        <Login />
      ) :
      (
        <CourseCockpit />
      )

  )
}
