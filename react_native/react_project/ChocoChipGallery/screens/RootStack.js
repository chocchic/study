import React, { useState } from 'react'
import { StyleSheet, Text} from 'react-native'
import { SafeAreaView } from 'react-native-safe-area-context'
import {createNativeStackNavigator} from '@react-navigation/native-stack'

import SignInScreen from './SignInScreen'

const Stack = createNativeStackNavigator()

function RootStack( {navigation, route}){
    return(
        <Stack.Navigator>
            <Stack.Screen name="SignIn" component={SignInScreen} options={{headerShown:false}} />
        </Stack.Navigator>
    )
}

const styles = StyleSheet.create({
    
})

export default RootStack