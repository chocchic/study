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

function App(){
  const today = new Date();
  return(
    <SafeAreaProvider>
      <SafeAreaView>
        <View>
          <Text>ToDoApp</Text>
          <DateHead date ={today}/>
        </View>
      </SafeAreaView>
    </SafeAreaProvider>
  )
}

const styles = StyleSheet.create({});

export default App;