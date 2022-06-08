import React from 'react'
import {View, Text, StyleSheet, Image, TouchableOpacity} from 'react-native'

import Icon from 'react-native-vector-icons/MaterialIcons'

function ToDoItem({id, text, done, onToggle}){
    return (
       <View style={styles.item}>
           <TouchableOpacity onPress={()=>onToggle(id)}>
            <View style={[styles.circle, done && styles.filled]}>
                {done && (<Image source={require('../assets/icons/check_white/check_white.png')}/>)}
            </View> 
            </TouchableOpacity>
            <Text style={[styles.text, done && styles.lineThrough]}>{text}</Text>
            {done?(
                <Icon name="delete" size={32} color="red"/>
            ):(
                <View style={styles.removePlaceholder}/>
            )}
       </View>
    );
}

const styles = StyleSheet.create({
    item:{
        flexDirection:"row",
        padding:16,
        borderBottomColor:"#e0e0e0",
        alignItems:'center'
    },
    circle:{
        width:24,
        height:24,
        borderRadius:12,
        borderColor:'#26a69a',
        borderWidth:1,
        marginRight:16
    },
    text:{
        flex:1,
        fontSize:16,
        color:'#212121'
    },
    filled:{
        justifyContent:'center',
        alignItems:'center',
        backgroundColor:'#26a69a'
    },
    lineThrough:{
        color:'#9e9e9e',
        textDecorationLine:'line-through'
    },
    removePlaceholder:{
        width:32,
        height:32
    }
});

export default ToDoItem;