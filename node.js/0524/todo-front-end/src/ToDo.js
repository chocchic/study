import React from "react"

class ToDo extends React.Component{
    render(){
        return(
            <div className="ToDo">
                <input type="checkbox" 
                    id="todo0" name="todo0" value="todo0"/>
                <label for="todo0">ToDo 컴포넌트 만들기</label>
            </div>
        )
    }
}

export default ToDo;