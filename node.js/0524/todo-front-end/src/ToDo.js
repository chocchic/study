import React from "react"

import {
    ListItem,
    ListItemText,
    InputBase,
    Checkbox,
    ListItemSecondaryAction,
    IconButton
}from "@material-ui/core";

import DeleteOutlined from "@material-ui/icons/DeleteOutlined";

class ToDo extends React.Component{
    constructor(props){
        super(props);
        this.state = {item:props.item, readOnly:true};
        this.delete = props.delete;
        this.update = props.update;
    }

    //삭제 이벤트 처리를 위한 함수
    deleteEventHandler = () => {
        this.delete(this.state.item);
    }

    //readOnly 가 false 가 될 때 호출될 함수
    offReadOnlyMode = () => {
        console.log("이벤트:", this.state.readOnly);
        this.setState({readOnly:false}, () => {
            console.log("읽기 전용?", this.state.readOnly);
        })
    }

    //Enter를 누르면 호출될 함수
    enterKeyEventHandler = (e) => {
        if(e.key === "Enter"){
            this.setState({readOnly:true});
            this.update(this.state.item);
        }
    }

    //수정시 호출될 함수
    editEventHandler = (e)=>{
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({item:thisItem});
    }

    //체크박스를 클릭했을 때 호출될 함수
     checkboxEventHandler = (e) => {
         const thisItem = this.state.item;
         thisItem.done = !thisItem.done;
         this.setState({item:thisItem});
         this.update(this.state.item);
     }
   

    render(){
        const item = this.state.item;
        return(
           <ListItem>
               <Checkbox checked={item.done} 
                    onChange={this.checkboxEventHandler}/>
               <ListItemText>
                   <InputBase
                    inputProps={{"aria-label":"naked", 
                        readOnly:this.state.readOnly}}
                    type="text"
                    id={item.id}
                    name={item.id}
                    value={item.title}
                    multiline={true}
                    fullWidth={true}
                    onClick={this.offReadOnlyMode}
                    onChange={this.editEventHandler}
                    onKeyPress={this.enterKeyEventHandler}
                    />
               </ListItemText>

               <ListItemSecondaryAction>
                   <IconButton aria-label="Delete ToDo" 
                   onClick={this.deleteEventHandler}>
                       <DeleteOutlined />
                   </IconButton>
               </ListItemSecondaryAction>
           </ListItem>
        )
    }
}

export default ToDo;