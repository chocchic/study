import React from 'react'
import { NavigationContainer, getFocusedRouteNameFromRoute} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack'
import {Text} from 'react-native'

import Icon from 'react-native-vector-icons/MaterialIcons'
import { Colors } from 'react-native/Libraries/NewAppScreen'

// 탭으로 사용할 컴포넌트 생성 - 이제 다른 파일로 분리해놨으므로 import해줌
import MainScreen from './screens/MainScreen';
import DetailScreen from './screens/DetailScreen'
import { createNativeStackNavigator } from '@react-navigation/native-stack';

// 탭을 생성
const Tab = createBottmTabNavigator();

const Stack = createNativeStackNavigator();

// 현재 선택된 화면에 해당하는 문자열을 리턴하는 함수
function getHeaderTitle(route){
    // 현재 선택된 화면의 이름을 가져오는데 선택된 화면이 없다면 첫번째 화면인 Home
    const routeName = getFocusedRouteNameFromRoute(route) ?? 'Home'
    const nameMap = {
        Home:"홈", Search:"검색", Notification:"알림", Messgae:"메시지"
    }
    return nameMap[routeName];
}
function App(){
    return (
        // <NavigationContainer>
        //     <Tab.Navigator initailRouteName='Home' screenOptions={{tabBarActiveTintColor:'#fb8c00', tabBarShowLabel:false}}>
        //         <Tab.Screen name='Home' component={HomeScreen} options={{title:"홈", tabBarIcon: ({color, size})=>(
        //             <Icon name='home' color={color} size ={size} />
        //         )}}/>
        //         <Tab.Screen name='Search' component={SearchScreen} options={{title:"검색", tabBarIcon: ({color, size})=>(
        //             <Icon name='search' color={color} size ={size} />
        //         )}}/>
        //         <Tab.Screen name='Notification' component={NotificationScreen} options={{title:"알림", tabBarIcon: ({color, size})=>(
        //             <Icon name='notification' color={color} size ={size} />
        //         )}}/>
        //         <Tab.Screen name='Message' component={MessageScreen} options={{title:"메세짖", tabBarIcon: ({color, size})=>(
        //             <Icon name='message' color={color} size ={size} />
        //         )}}/>
        //     </Tab.Navigator>
        // </NavigationContainer>
        <NavigationContainer>
            <Stack.Navigator>
                <Stack.Screen name="Main" components={MainScreen} options={({route})=> ({title.getHeaderTitle(route)})}/>
                <Stack.Screen name="Detail" components={DetailScreen}/>
            </Stack.Navigator>
        </NavigationContainer>
    );
}

export default App