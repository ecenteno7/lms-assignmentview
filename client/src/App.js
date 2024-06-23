import './App.css'
import { Header } from './components/layout/header'
import { Sidebar } from './components/layout/sidebar'
import { Body } from './components/layout/body';

function App() {
  return (
    <div className="App h-screen">
      <Header />
      <div className="flex h-5/6 m-4">
        <Sidebar />
        <Body />
      </div>
    </div >
  );
}

export default App;
