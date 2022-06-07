import React from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';

import DateHead from './components/DateHeadDateHead';

function App(){
  const today = new Date();
  return(
    <SafeAreaView>
      <View>
        <Text>ToDoApp</Text>
        <DateHead date ={today}/>
      </View>
    </SafeAreaView>
  )
}

const styles = StyleSheet.create({});

export default App;