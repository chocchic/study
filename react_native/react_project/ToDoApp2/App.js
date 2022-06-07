import React from 'react';
import {
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';

import DateHead from './components/DateHead';
import { SafeAreaView, SafeAreaProvider} from 'react-native-safe-area-context'

import AddToDo from './components/AddToDo'
import Empty from './components/Empty'

function App(){
  const today = new Date();
  return(
    <SafeAreaProvider>
      <SafeAreaView edges={['bottom']} style={styles.block}>
        <View>
          <DateHead date ={today}/>
          <Empty/>
          <AddToDo/>
        </View>
      </SafeAreaView>
    </SafeAreaProvider>
  )
}

const styles = StyleSheet.create({
  block:{
    flex:1
  }
});

export default App;