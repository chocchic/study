import React from 'react'
import { NavigationContainer} from '@react-navigation/native';
import {createBottmTabNavigator} from '@react-navigation/bottom-tabs'
import {Text} from 'react-native'

import Icon from 'react-native-vector-icons/MaterialIcons'

// 탭으로 사용할 컴포넌트 생성
function HomeScreen(){
    return <Text>Home</Text>
}

function SearchScreen(){
    return <Text>Search</Text>
}

function NotificationScreen(){
    return <Text>Notification</Text>
}

function MessageScreen(){
    return <Text>Message</Text>
}

// 탭을 생성
const Tab = createBottmTabNavigator();

function App(){
    return (
        <NavigationContainer>
            <Tab.Navigator initailRouteName='Home'>
                <Tab.Screen name='Home' component={HomeScreen} options={{title:"홈", tabBarIcon: ({color, size})=>(
                    <Icon name='home' color={color} size ={size} />
                )}}/>
                <Tab.Screen name='Search' component={SearchScreen} options={{title:"검색", tabBarIcon: ({color, size})=>(
                    <Icon name='search' color={color} size ={size} />
                )}}/>
                <Tab.Screen name='Notification' component={NotificationScreen} options={{title:"알림", tabBarIcon: ({color, size})=>(
                    <Icon name='notification' color={color} size ={size} />
                )}}/>
                <Tab.Screen name='Message' component={MessageScreen} options={{title:"메세짖", tabBarIcon: ({color, size})=>(
                    <Icon name='message' color={color} size ={size} />
                )}}/>
            </Tab.Navigator>
        </NavigationContainer>
    );
}

export default App