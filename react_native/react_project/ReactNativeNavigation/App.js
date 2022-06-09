import React from 'react'
import { NavigationContainer, StackActions } from '@react-navigation/native';

import {createNativeStackNavigator} from '@react-navigation/native-stack'
import HomeScreen from './screens/HomeScreen';
import DetailScreen from './screens/DetailScreen';

// 스택생성
const Stack = createNativeStackNavigator()

function App(){
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName='Home'>
        <Stack.Screen name='Home' component={HomeScreen} options={{title:"홈", headerStyle: {
          backgroundColor:'#2966f6',
          headerTintColor:'#ffff00',
          headerTitleStyle:{fontweight:'bold', fontSize:20}

        }}}/>
        <Stack.Screen name='Detail' component={DetailScreen} options={({route})=> ({title:`상세 정보 - ${route.params.id}`})}/>
      </Stack.Navigator>
    </NavigationContainer>
  )
}
export default App;
