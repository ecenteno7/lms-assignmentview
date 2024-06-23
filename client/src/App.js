import './App.css'
import { CourseCockpit } from './components/views/courseCockpit';
import { Login } from './components/views/auth';
import { Header } from "./components/layout/header"

function App() {
  return (
    <div className="App h-screen">
      <Header />
      <Login />
    </div >
  );
}

export default App;
