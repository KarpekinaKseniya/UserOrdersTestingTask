import './App.css';
import React from "react";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Orders from "./components/Orders";

function App() {
    return (
        <div>
            <Router>
                <Routes>
                    <Route path='/' exact={true} element={<Orders/>}/>
                </Routes>
            </Router>
        </div>
    );
}

export default App;
