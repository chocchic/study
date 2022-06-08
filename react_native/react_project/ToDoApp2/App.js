import React, {useState} from 'react';
import {
  StyleSheet,
  Text,
  View,
  KeyboardAvoidingView,
  Platform
} from 'react-native';

import DateHead from './components/DateHead'
import {SafeAreaProvider, SafeAreaView} from 'react-native-safe-area-context'

import AddToDo from './components/AddToDo'
import Empty from './components/Empty'
import ToDoList from './components/ToDoList';

function App(){
  const today = new Date();
  const [todos, setTodos] = useState([
    {id : 1, text:'작업 환경 설정', done:true},
    {id : 2, text:'BackEnd - Spring Boot', done:true},
    {id : 3, text:'FrontEnd - ReactNative', done:false}
  ])
  return (
    <SafeAreaProvider>
    <SafeAreaView edges={['bottom']} style={styles.block} >
        <KeyboardAvoidingView 
      behavior={Platform.OS === 'ios' ? 'padding' : undefined} style={styles.avoid}>
        <DateHead date={today} />
        {todos.length === 0 ? <Empty /> : <ToDoList todos={todos}/>}
        <AddToDo />
        </KeyboardAvoidingView>
    </SafeAreaView>
    </SafeAreaProvider>
  );
};

const styles = StyleSheet.create({ 
  block:{
    flex:1
  },
  avoid:{
    flex:1
  }
});

export default App;