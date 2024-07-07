import logo from './logo.svg';
import './App.css';
import {useEffect, useRef} from "react";

function TestFetch(textRef) {
    let query = fetch('http://localhost:8000/hello')
        .then((res) => {
            if (!res.ok) {
                res = "Error";
                throw new Error(res.statusText);
            }
            return res.text();
        })
        .then(text => {
            if( textRef.current )
                textRef.current.innerText = text;
        })
        .catch(error => {
            textRef.current.innerText = `Problem fetching`;
        });
}

const text_app = document.querySelector(".App-text");

function App() {
    const textRef = useRef(null);
    useEffect(() => {
        TestFetch(textRef);
    }, []);
    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo"/>
                <p className="App-text" ref={textRef}>

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