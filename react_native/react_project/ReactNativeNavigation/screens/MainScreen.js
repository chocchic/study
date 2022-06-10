import React from 'react'
import {createBottmTabNavigator} from '@react-navigation/bottom-tabs'
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs'
import { createMaterialBottomTabNavigator } from '@react-navigation/material-bottom-tabs'
import {Text, Button, View} from 'react-native'
import Icon from 'react-native-vector-icons/MaterialIcons'
import { NavigationContainer } from '@react-navigation/native'

// 탭으로 사용할 컴포넌트 생성
function HomeScreen({navigation}){
    return (
        <View>
            <Text>Home</Text>
            <Button title="Detail 열기" onPress={() => navigation.push("Detail", {id:1})}/>
        </View>
    )
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
const Tab = createMaterialBottomTabNavigator();

function MainScreen(){
    return (
//        <NavigationContainer> 밖에서 만들기 때문에 여기서 있으면 오류남
//  screenOptions={{tabBarActiveTintColor:'#fb8c00', tabBarIndicatorStyle:{backgroundColor:'#009688'}}} 제거
            <Tab.Navigator initailRouteName='Home'>
                <Tab.Screen name='Home' component={HomeScreen} options={{title:"홈", tabBarIcon: ({color})=>(
                    <Icon name='home' color={color} size ={24} />
                ), tabBarColor:'black', tabBarBadge:'new'}}/>
                <Tab.Screen name='Search' component={SearchScreen} options={{tabBarLabel:"검색", tabBarIcon: ({color})=>(
                    <Icon name='search' color={color} size ={24} />
                ), tabBarColor:'green', tabBarBadge:30}}/>
                <Tab.Screen name='Notification' component={NotificationScreen} options={{tabBarLabel:"알림", tabBarIcon: ({color})=>(
                    <Icon name='notification' color={color} size ={24} />
                )}}/>
                <Tab.Screen name='Message' component={MessageScreen} options={{tabBarLabel:"메세지", tabBarIcon: ({color})=>(
                    <Icon name='message' color={color} size ={24} />
                )}}/>
            </Tab.Navigator>
//        </NavigationContainer>
    );
}

export default MainScreen