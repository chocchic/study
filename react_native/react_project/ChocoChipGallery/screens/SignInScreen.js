import React, { useState, useRef } from 'react'

import { StyleSheet, Text, View, Keyboard, KeyboardAvoidingView, Platform } from 'react-native'

import { SafeAreaView } from 'react-native-safe-area-context'

import BorderedInput from '../components/Borderedinput'
import CustomButton from '../components/CustomButton'

function SignInScreen( {navigation, route}){
    // 로그인인지 회원 가입인지 구분하기 위한 변수 생성
    const {isSignUp} = route.params ?? {};

    // 속성과 속성을 수정하는 함수 그리고 기본값을 설정
    const [form, setForm] = useState({
        email:'',
        password:'',
        confirmPassword:''
    });

    // form에 데이터를 설정하는 함수 - BorderedInput에 연결
    const createChangeTextHandler = name => value => {
        // name하고 value가 들어오면
        // form 속성 안에서 name에 value를 설정
        setForm({...form, [name]:value});
    }

    // 버튼을 눌렀을 때 호출될 함수
    const onSubmit = () =>{
        Keyboard.dismiss();
        console.log(form);
    }

    const passwordRef = useRef();
    const confirmPasswordRef = useRef();

    return(
        <KeyboardAvoidingView style={styles.keyboardAvoidingView} 
        behavior={Platform.select({iod:'padding'})}>
        <SafeAreaView style={styles.fullscreen}>
            <Text style={styles.text}>ChocoChip Gallery</Text>
            <View style={styles.form}>
                <BorderedInput hasMarginBottom placeholder="이메일"
                value={form.email} onChangeText={createChangeTextHandler('email')}
                autoCapitalize="none" autoCorrect={false} autoCompleteType="email" keyboardType="email-address"
                returnKeyType="next" onSubmitEditing= {()=> passwordRef.current.focus()} // returnKeyType은 안드로이드보단 ios에서 의미가 있음
                />

                <BorderedInput placeholder="비밀번호" hasMarginBottom={isSignUp}
                value={form.password} onChangeText={createChangeTextHandler('password')}
                secureTextEntry ref={passwordRef} 
                returnKeyType={isSignUp ? 'next' : 'done'} onSubmitEditing = {() => {
                    if(isSignUp) {confirmPasswordRef.current.focus()} 
                    else {onSubmit();}
                }}/>

                {isSignUp && <BorderedInput placeholder="비밀번호 확인" 
                value={form.confirmPassword} onChangeText={createChangeTextHandler('confirmPassword')} 
                secureTextEntry ref={confirmPasswordRef} returnKeyType="done" onSubmitEditing={onSubmit}/>}
                <View style={styles.buttons}>
                    {isSignUp ? (
                        <>
                            <CustomButton title="회원가입" hasMarginBottom />
                            <CustomButton title="로그인" theme="secondary" onPress={()=>{
                                navigation.goBack();
                            }}/>
                        </>
                    ) : (
                        <>
                            <CustomButton title="로그인" hasMarginBottom />
                            <CustomButton title="회원가입" theme="secondary" onPress={()=>{
                                navigation.push("SignIn", {isSignUp:true});
                            }}/>
                        </>
                    )}
                    
                </View>
            </View>
        </SafeAreaView>
        </KeyboardAvoidingView>
    )
}

const styles = StyleSheet.create({
    keyboardAvoidingView:{flex:1},
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