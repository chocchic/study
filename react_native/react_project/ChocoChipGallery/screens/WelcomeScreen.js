import React from 'react'
import { KeyboardAvoidingView, Platform, StyleSheet, Text } from 'react-native'
import { SafeAreaView } from 'react-native-safe-area-context'

function WelcomeScreen(){
    return (
        <KeyboardAvoidingView style = {styles.KeyboardAvoidingView}
        behavior ={Platform.select({ios:'padding'})}>
            <SafeAreaView style={styles.block}>
                <Text style={styles.title}>환영합니다</Text>
                <Text style={styles.description}>ㄴ프로필을 설정하세요</Text>
            </SafeAreaView>
        </KeyboardAvoidingView>
    )
}

const styles = StyleSheet.create({
    KeyboardAvoidingView:{
        flex:1
    },
    block:{
        flex:1,
        alignItems:'center',
        justifyContent:'center'
    },
    description:{
        marginTop: 16,
        fontSize: 21,
        color: '#757575'
    }
})

export default WelcomeScreen