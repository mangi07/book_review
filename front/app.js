
import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Registration from './Registration';
import Welcome from './Welcome';

class App extends React.Component {
  render(){
    return(
      <Router>
        <Route exact path="/" component={Registration} />
        <Route exact path="/welcome" component={Welcome} />
        <Route exact path="/search" component={Search} />
      </Router>
    )
  }
}

export default App;
