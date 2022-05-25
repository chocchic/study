import React from "react"

import{
    ListItem,
    ListItemText,
    InputBase,
    Checkbox,
    ListItemSecondaryAction,
    IconButton
}from "@material-ui/core";

import DeleteOutlined  from "@material-ui/icons/DeleteForeverOutlined";
class ToDo extends React.Component{
    constructor(props){
        super(props);
        this.state = {item:props.item, readOnly:true};
        this.delete = props.delete;
    }

    /*
    함수 만드는 방법
    1.
    function 이름(매개변수){
        내용
    }

    2. 
    var 또는 let,const 이름 = function(매개변수){
        내용
    }
    3. 에로우 함수 : 메모리 효율때문에 많이 사용
    (매개변수) => {
        내용
    }

    javascript에서 변수 만들 때 
    var 변수이름 = 값; 해도 되지만
    변수이름 = 값; 해도 됨. 대신 이 경우는 global됨
    */
    // 삭제 이벤트 처리를 위한 함수
    deleteEventHandler = ()=>{
        this.delete(this.state.item);
    }

    // readonly가 false가 될 때 호출될 함수
    offReadOnlyMode=()=>{
        console.log("event : ", this.state.readOnly);
        this.setState({readOnly:false},()=>{
            console.log("readonly? : ", this.state.readOnly);
        })
    }
    // 엔터를 누르면 호출될 함수
    enterKeyEventHandler = (e)=>{
        if(e.key === "Enter"){
            this.setState({readOnly:true});
        }
    }

    // 수정시 호출될 함수
    editEventHandler = (e)=>{
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({item:thisItem});
    }

    // 체크박스를 클릭햇을 때 호출될 함수 
    checkboxEventHandler = (e)=>{
        const thisItem = this.state.item;
        thisItem.done = !this.item.done;
        this.setState({item:thisItem});
    }

    render(){
        const item = this.state.item;
        return(
            <ListItem>
                <Checkbox checked={item.done} onChange={this.checkboxEventHandler}/>
                <ListItemText>
                    <InputBase
                        inputProps={{"aria-label":"naked", readOnly:this.state.readOnly}}
                        type="text"
                        id={item.id}
                        name={item.name}
                        value={item.title}
                        multiline={true}
                        fullWidth={true}
                        onClick={this.offReadOnlyMode}
                        onChange={this.editEventHandler}
                        onKeyPress={this.enterKeyEventHandler}
                        />
                </ListItemText>
                <ListItemSecondaryAction>
                    <IconButton aria-label="Delete ToDo" onClick={this.deleteEventHandler}>
                        <DeleteOutlined/>
                    </IconButton>
                </ListItemSecondaryAction>
            </ListItem>
        )
    }
}

export default ToDo;