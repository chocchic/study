import React from "react";
import {View, Button, Text} from 'react-native'
import {SafeAreaView} from 'react-native-safe-area-context'

function HomeScreen({navigation}){
    return (
        <SafeAreaView>
            <View>
                <Text>HeaderLess</Text>
                <Button onPress={()=>{navigation.pop()}} title='뒤로'/>
            </View>
        </SafeAreaView>
    );
}

export default HomeScreen