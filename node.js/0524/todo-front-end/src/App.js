import logo from './logo.svg';
import React from "react";
import ToDo from "./ToDo";
import AddToDo from "./AddToDO";

import './App.css';
import {Paper, List, Container, ThemeProvider} from "@material-ui/core";

import {call} from './service/Api-Service'

class App extends React.Component{
  constructor(props){
      super(props);
      this.state = {items:[]};
  }

  // 컴포넌트가 마운트된 후 호출되는 함수
  componentDidMount(){
    call("/todo", "GET", null).then((response)=> this.setState({items:response.data}))
  }

  add = (item) =>{
    // // 데이터 배열 가져오기
    // const thisItems = this.state.items;
    // // 새로운 item의 id 설정
    // item.id = "ID-"+thisItems.length;
    // // done 설정
    // item.done = false;
    // // 배열에 추가
    // thisItems.push(item);
    // // 원본 데이터 변경
    // this.setState({items:thisItems});
    call("/todo", "POST", item).then((response)=>this.setState({items:response.data}))
  }

  delete = (item)=>{
    // const thisItems = this.state.items;
    // const newItems = thisItems.filter((e)=>e.id !== item.id);
    // this.setState({items:newItems}, ()=>{
    //   console.log("데이터 삭제");
    // });
    call("/todo", "DELETE", item).then((response)=>this.setState({items:response.data}))
  }

  update = (item)=>{
    call("/todo", "PUT", item).then((response)=>this.setState({items:response.data}))
  }
  render(){
    var todoItems= this.state.items.length > 0 && (
      <Paper style={{margin:16}}>
        <List>
          {this.state.items.map((item)=>{
              return <ToDo item = {item} key={item.id} delete={this.delete} update={this.update}/>
            })
          }
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
