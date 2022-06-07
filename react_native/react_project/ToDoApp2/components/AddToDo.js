import React from "react";
import {View, StyleSheet,TextInput} from 'react-native'

function AddToDo(){
    return (
        <View style = {styles.block}>
            <TextInput placeholder="수행할 내용을 입력하세요" style={styles.input}/>
        </View>
    )
}

const styles = StyleSheet.create({
    block:{
        backgroundColor:'red',
        height:64,
        paddingHorizontal:16,
        borderColor:"#bdbdbd",
        borderBottomWidth:1,
        borderTopWidth:1,
        justifyContent:'center'
    },
    input:{
        fontSize:16,
        paddingVertical:8
    }
})

export default AddToDo;