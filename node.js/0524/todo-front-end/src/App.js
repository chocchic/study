import logo from './logo.svg';
import React from "react";
import ToDo from "./ToDo";
import AddToDo from "./AddToDO";

import './App.css';
import {Paper, List, Container} from "@material-ui/core";

class App extends React.Component{
  constructor(props){
      super(props);
      this.state = {items:[
      {id:0, title:"Hello React", done:true},
      {id:1, title:"Hello React2", done:false},
      {id:2, title:"Hello React", done:true}]};
  }
  add = (item) =>{
    // 데이터 배열 가져오기
    const thisItems = this.state.items;
    // 새로운 item의 id 설정
    item.id = "ID-"+thisItems.length;
    // done 설정
    item.done = false;
    // 배열에 추가
    thisItems.push(item);
    // 원본 데이터 변경
    this.setState({items:thisItems});
  }

  delete = (item)=>{
    const thisItems = this.state.item;
    const newItems = thisItems.filter((e)=>e.id !== item.id);
    this.setState({items:newItems}, ()=>{
      console.log("데이터 삭제");
    });
  }

  render(){
    var todoItems= this.state.items.length > 0 && (
      <Paper style={{margin:16}}>
        <List>
          {this.state.items.map((item)=>{
            return <ToDo item = {item} key={item.id} delete={this.delete}/>
          })};
        </List>
      </Paper>
    )

    return(
      <div className='App'>
        <Container maxWidth="md">
          <AddToDo add={this.add}/>
          <div className="ToDoList">{todoItems}</div>
        </Container>
      </div>
    );
  }
}

export default App;
