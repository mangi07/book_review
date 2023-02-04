import React from 'react';
//import logo from './logo.svg';
import './App.css';

import { Routes, Route } from 'react-router-dom';
import Registration from './registration';
import Welcome from './welcome';
import Login from './login';

class App extends React.Component {
  render(){
    return(
      <Routes>
        <Route exact path="/" element={<Registration />} />
        <Route exact path="/welcome" element={<Welcome />} />
        <Route exact path="/login" element={<Login />} />
      </Routes>
    )
  }
}

export default App;
