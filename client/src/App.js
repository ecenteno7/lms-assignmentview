import './App.css'
import { Landing } from './views/landing';
import { Header } from './components/layout/header';

function App() {
  return (
    <div className="App h-screen">
      <Header />
      <Landing />
    </div >
  );
}

export default App;



