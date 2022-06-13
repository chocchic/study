import React, { useState } from 'react'

import { StyleSheet, Text, View } from 'react-native'

import { SafeAreaView } from 'react-native-safe-area-context'

import BorderedInput from '../components/Borderedinput'
import CustomButton from '../components/CustomButton'

function SignInScreen( {navigation, route}){
    return(
        <SafeAreaView style={styles.fullscreen}>
            <Text style={styles.text}>ChocoChip Gallery</Text>
            <View style={styles.form}>
                <BorderedInput hasMarginBottom placeholder="이메일"/>
                <BorderedInput placeholder="비밀번호"/>
                <View style={styles.buttons}>
                    <CustomButton title="로그인" hasMarginBottom />
                    <CustomButton title="회원가입" />
                </View>
            </View>
        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
    fullscreen:{
        flex:1,
        alignItems:"center",
        justifyContent:"center"
    },
    text:{
        fontSize:32,
        fontWeight:"bold"
    },
    form:{
        marginTop:64,
        width:'100%',
        paddingHorizontal:26
    },
    buttons:{
        marginTop:64
    }
})

export default SignInScreen