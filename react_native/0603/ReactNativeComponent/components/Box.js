import React from 'react'
import {View, StyleSheet } from 'react-native'

function Box(props){
    return (
        <View style={styles.box}/>
    );
}
const styles = StyleSheet.create({
    box:{
        width:64,
        height:64,
        backgroundColor: 'black'
    }
});
export default Box;