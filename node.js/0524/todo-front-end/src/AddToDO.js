import React from "react";

import { TextField, Paper, Button, Grid } from "@material-ui/core";

class AddToDo extends React.Component{
    constructor(props){
        super(props);
        this.state = {item:{title:""}};
        this.add = props.add;
    }

    // Input의 내용이 변경될 때 호출될 함수
    onInputChange = (e) =>{
        // 입력한 내용을 title에 대체
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({item:thisItem});
    }

    // + 버튼을 눌렀을 때 호출될 함수
    onButtonClick = () =>{
        this.add(this.state.item);
        this.setState({item:{title:""}});
    }

    // Enter키를 눌렀을 때 호출될 함수
    enterKeyEventHandler = (e)=>{
        if(e.key === "Enter"){
            this.onButtonClick();
        }
    }

    render(){
        return(
            <Paper style={{margin:16,padding:16}}>
                <Grid container>
                    <Grid xs={11} item style={{paddingRight:16}}>
                        <TextField placeholder="텍스트를 입력하세요" fullWidth 
                        onChange={this.onInputChange} value={this.state.item.title} 
                        onKeyPress={this.enterKeyEventHandler}/>
                    </Grid>
                    <Grid xs={1} md={1} item>
                        <Button fullWidth color="secondary" variant="outllined" 
                        onClick={this.onButtonClick}>
                            +
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        )
    }
}

export default AddToDo;