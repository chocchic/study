import React from 'react'
import { NavigationContainer} from '@react-navigation/native';
// 하단 탭 생성에서는 스택 안 사용함
/*
import {createNativeStackNavigator} from '@react-navigation/native-stack'
import HomeScreen from './screens/HomeScreen';
import DetailScreen from './screens/DetailScreen';
import HeaderlessScreen from './screens/HeaderlessScreen';

import {View, Text, TouchableOpacity} from 'react-native'
*/

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
        <Stack.Screen name='Detail' component={DetailScreen} options={{
          // 뒤로가기 버튼 삭제
          headerBackVisible:false,
          // 바의 왼쪽에 버튼 생성
          headerLeft:({onPress}) =>{
            <TouchableOpacity onPress={onPress}>
              <Text>Left</Text>
            </TouchableOpacity>
          },
          // 바의 가운데에 텍스트 출력
          headerTitle:({children})=>{
            <View>
              <Text>{children}</Text>  
            </View>
          },
          // 바의 오른쪽에 배치
          headerRight:()=>{
            <View>
              <Text>오른쪽</Text>
            </View>
          }
        }}/>
        <Stack.Screen name='Headerless' component={HeaderlessScreen} options={{
          headerShown: false
        }}/>
      </Stack.Navigator>
    </NavigationContainer>
  )
}
export default App;
