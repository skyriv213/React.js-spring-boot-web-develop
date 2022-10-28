import React from 'react';
import Todo from './Todo';
import {Paper,List} from "@material-ui/core"
import './App.css';

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      items: [
        { id: "0", title: "the world is awesome", done: true },
        { id: "1", title: "the world is awesome2", done: false },

      ],
    };
  }

  render() {
    var todoItems = this.state.items.length >0 &&(
      <Paper style = {{margin: 20}}>
        <List>
          {this.state.items.map((item,idx)=> (
            <Todo item = {item} key = {item.id} />
          ))}
        </List>
      </Paper>
    );
    return <div className="App">{todoItems}</div>;
  }
}

export default App;