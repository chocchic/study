import React from 'react'

import { getFocusedRouteNameFromRoute, NavigationContainer} from '@react-navigation/native'
import { createNativeStackNavigator } from '@react-navigation/native-stack'

import MainScreen from './screens/MainScreen'
import DetailScreen from './screens/DetailScreen'

const Stack = createNativeStackNavigator()

// 현재 선택한 탭의 이름을 리턴하는 함수
function getHeaderTitle(route){
    const routeName = getFocusedRouteNameFromRoute(route) ?? "Home"
    const nameMap={
        Home:"홈", Search:"검색", Notification:"알림", Messgae:"메시지"
    }
    return nameMap[routeName]
}

function App(){
    return (
        <NavigationContainer>
            <Stack.Navigator>
                <Stack.Screen name="Home" component={MainScreen} options={({route})=> ({
                    title:getHeaderTitle(route)
                })}/>
                <Stack.Screen name="Detail" component={DetailScreen} />
            </Stack.Navigator>
        </NavigationContainer>
    )
}

export default App;