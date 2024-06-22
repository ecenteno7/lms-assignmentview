import logo from './logo.svg';
import './App.css'

function App() {
  return (
    <div className="App">
        <div className="bg-slate-800 text-white font-bold text-3xl justify-items-start">
          <div className="max-w-sm text-left">
            <p className="pl-10 pt-3 pb-3">Blink</p>
          </div>
        </div>
        <div className="bg-slate-300 max-w-2xl min-h-screen grid grid-rows-12">
          <div className="p-3 mg-slate-500 w-full row-span-2">
            <p>Insights</p>
          </div> 
          <div className="row-span-8">
            <p>Body</p>
          </div>
          <div className="row-span-2">
            <p>Chatter</p>
          </div>
        </div>
    </div >
  );
}

export default App;
