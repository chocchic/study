import React from 'react'
import { NavigationContainer, StackActions } from '@react-navigation/native';

import {createNativeStackNavigator} from '@react-navigation/native-stack'
import HomeScreen from './screens/HomeScreen';
import DetailScreen from './screens/DetailScreen';

const Stack = createNativeStackNavigator()

function App(){
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName='Home'>
        <Stack.Screen name='Home' component={HomeScreen}/>
        <Stack.Screen name='Detail' component={DetailScreen}/>
      </Stack.Navigator>
    </NavigationContainer>
  )
}
export default App;
