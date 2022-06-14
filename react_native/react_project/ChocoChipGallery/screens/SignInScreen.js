import React, { useState, useRef } from 'react'

import { StyleSheet, Text, View, Keyboard, KeyboardAvoidingView, Platform, Alert } from 'react-native'

import { SafeAreaView } from 'react-native-safe-area-context'

import SignButton from '../components/SignButton'

import {signIn, signUp} from '../lib/auth'

function SignInScreen( {navigation, route}){
    // 로그인인지 회원 가입인지 구분하기 위한 변수 생성
    const {isSignUp} = route.params ?? {};

    // 속성과 속성을 수정하는 함수 그리고 기본값을 설정
    const [form, setForm] = useState({
        email:'',
        password:'',
        confirmPassword:''
    });

    const [loading, setLoading] = useState();

    // form에 데이터를 설정하는 함수 - BorderedInput에 연결
    const createChangeTextHandler = name => value => {
        // name하고 value가 들어오면
        // form 속성 안에서 name에 value를 설정
        setForm({...form, [name]:value});
    }

    // 버튼을 눌렀을 때 호출될 함수
    // async()는 이 함수를 비동기적으로 수행
    const onSubmit = async() =>{
        Keyboard.dismiss();
        console.log(form);

        // 입력한 내용 가져오기
        const {email, password} = form;
        const info = {email, password};
        // 스피너를 화면에 출력
        setLoading(true);

        // await가 붙으면 이 동작이 완료될 때까지 대기
        try{
            const {user} = isSignUp ? await signUp(info) : await signIn(info)
            console.log(user)
        }catch(e){
            console.log(e);

            const messages = {
                'auth/email-already-in-use' : '이미 가입된 이메일입니다.',
                'auth/wrong-password' : '잘못된 비밀번호입니다.',
                'auth/user-not-found' : '존재하지 않는 계정입니다.',
                'auth/invalid-email' : '유효하지 않은 이메일입니다.'
            }

            const msg = messages[e.code] || `${isSignUp ? '가입' : '로그인'} 실패`;
            Alert.alert('실패', msg);
        }
    }

    const passwordRef = useRef();
    const confirmPasswordRef = useRef();

    return(
        <KeyboardAvoidingView style={styles.keyboardAvoidingView} 
        behavior={Platform.select({iod:'padding'})}>
        <SafeAreaView style={styles.fullscreen}>
            <Text style={styles.text}>ChocoChip Gallery</Text>
            <View style={styles.form}>
                <View style={styles.form} isSignUp={isSignUp} onSubmit={onSubmit} createChangeTextHandler={createChangeTextHandler}>
                    <SignButton isSignUp={isSignUp} onSubmit={onSubmit} loading={loading}/>
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