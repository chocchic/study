import React from "react";
import {View, StyleSheet} from 'react-native'

function AddToDo(){
    return (
        <View style = {styles.block}>
        </View>
    )
}

const styles = StyleSheet.create({
    block:{
        backgroundColor:'red',
        height:64
    }
})

export default AddToDo;