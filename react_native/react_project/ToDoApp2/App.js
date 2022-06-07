import React from 'react';
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

function App(){
  const today = new Date();
  
  return (
    <SafeAreaProvider>
    <SafeAreaView edges={['bottom']} style={styles.block} >
        <KeyboardAvoidingView 
      behavior={Platform.OS === 'ios' ? 'padding' : undefined} style={styles.avoid}>
        <DateHead date={today} />
        <Empty />
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