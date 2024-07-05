import logo from './logo.svg';
import './App.css';
import {useEffect} from "react";

function TestFetch() {
  let query = fetch('http://localhost:4000')
      .then((res) => {
        if( !res.ok  ) {
          res = "Error";
          throw new Error(res.statusText);
        }
        return res.text();
      })
      .then(text => {
        text_app.innerText = text;
      })
      .catch(error => {
        text_app.innerText = `Problem fetching`;
      });
}
const text_app = document.querySelector(".App-text");
function App() {
  useEffect(() => {
    TestFetch();
  }, []);
  return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo"/>
          <p className="App-text">

          </p>
          <a
              className="App-link"
              href="https://reactjs.org"
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
import logo from './logo.svg';
import './App.css';
import {useEffect} from "react";

function TestFetch() {
  let query = fetch('http://localhost:4000')
      .then((res) => {
        if( !res.ok  ) {
          res = "Error";
          throw new Error(res.statusText);
        }
        return res.text();
      })
      .then(text => {
        text_app.innerText = text;
      })
      .catch(error => {
        text_app.innerText = `Problem fetching`;
      });
}
const text_app = document.querySelector(".App-text");
function App() {
  useEffect(() => {
    TestFetch();
  }, []);
  return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo"/>
          <p className="App-text">

          </p>
          <a
              className="App-link"
              href="https://reactjs.org"
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
