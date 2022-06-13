import React from 'react'
import { StyleSheet, TextInput } from 'react-native'

function BorderedInput({hasMarginBottom, ...rest}){
    return (<TextInput style={[styles.input, hasMarginBottom && styles.margin]} 
        {...rest} />)
        //onChangeText={onChangeText} value={value} placeholder={placeholder}/>);
}

const styles = StyleSheet.create({
    input:{
        borderColor:"#bdbdbd",
        borderWidth:1,
        padding:16,
        borderRadius:4,
        height:48,
        backgroundColor:'white'
    },
    margin:{
        marginBottom:16
    }
})

export default BorderedInput