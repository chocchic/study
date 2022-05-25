import React from "react"

import{
    ListItem,
    ListItemText,
    InputBase,
    Checkbox
}from "@material-ui/core";

class ToDo extends React.Component{
    constructor(props){
        super(props);
        this.state = {item:props.item};
    }

    render(){
        const item = this.state.item;
        return(
            <ListItem>
                <Checkbox checked={item.done}/>
                <ListItemText>
                    <InputBase
                        inputProps={{"aria-label":"naked"}}
                        type="text"
                        id={item.id}
                        name={item.name}
                        value={item.title}
                        multiline={true}
                        fullWidth={true}
                        />
                </ListItemText>
            </ListItem>
        )
    }
}

export default ToDo;