import React from 'react'
import { ActivityIndicator, StyleSheet, View } from 'react-native'
import CustomButton from './CustomButton'
import { useNavigation } from '@react-navigation/native'

function SignButton({isSignUp, onSubmit}){
    const navigation = useNavigation()
    const primaryTitle = isSignUp ? '회원가입' : '로그인'
    const secondaryTitle = isSignUp ? '로그인' : '회원가입'

    const onSecondaryButtonPress =() =>{
        if(isSignUp){
            navigation.goBack();
        }else{
            navigation.push('SignIn', {isSignUp:true})
        }
    }

    return (
        <View style={styles.buttons}>
            <CustomButton title={primaryTitle} hasMarginBottom onPress={onSubmit}/>
            <CustomButton title={secondaryTitle} hasMarginBottom onPress={onSecondaryButtonPress}/>
        </View>
    )
}

const styles = StyleSheet.create({
    buttons:{
        marginTop:64
    }
})