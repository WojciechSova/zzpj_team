import logo from './logo.svg';
import sound from './waiting.mp3';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <audio autoPlay loop hidden>
          <source src={sound}/>
        </audio>
          <img src={logo} className="App-logo" alt="logo"/>
        <p>
          Bank opening soon...
          <br/>Firefox recommended...
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"x
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
