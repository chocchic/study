import React from 'react'
import {View, Text, StyleSheet, FlatList} from 'react-native'

function ToDoItem({id, text, done}){
    return (
       <View style={styles.item}>
           <View style={styles.circle}/>
           <Text style={styles.text}>{text}</Text>
       </View>
    );
}

const styles = StyleSheet.create({
    item:{

    },
    circle:{

    },
    text:{
        
    }
});

export default ToDoItem;