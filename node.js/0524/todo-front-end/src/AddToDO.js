import React from "react";

import { TextField, Paper, Button, Grid } from "@material-ui/core";

class AddToDo extends React.Component{
    constructor(props){
        super(props);
        this.state = {item:{title:""}};
    }

    render(){
        return(
            <Paper style={{margin:16,padding:16}}>
                <Grid container>
                    <Grid xs={11} item style={{paddingRight:16}}>
                        <TextField placeholder="텍스트를 입력하세요" fullWidth />
                    </Grid>
                    <Grid xs={1} md={1} item>
                        <Button fullWidth color="secondary" variant="outllined">
                            +
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        )
    }
}

export default AddToDo;