import React from 'react'
import {View, Text, StyleSheet, FlatList} from 'react-native'

// 출력할 데이터를 todos라는 이름으로 넘겨받는다
function ToDoList({todos}){
    return (
       <FlatList style={styles.list} data={todos} renderItem={({item})=>{
            <View>
                <Text>{item.next}</Text>
            </View>
       }} keyExtractor={item=> item.id.toString()} />
    );
}

const styles = StyleSheet.create({
    list :{
        flex:1
    }
});

export default ToDoList;