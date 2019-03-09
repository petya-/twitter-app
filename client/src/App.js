import React, { Component } from 'react';
import './App.css';
import TwitterPost from './twitter/TwitterPost';

class App extends Component {
  render() {
    return (
      <div className="App">
        <TwitterPost/>
      </div>
    );
  }
}

export default App;
