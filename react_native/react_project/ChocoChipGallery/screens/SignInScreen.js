import React, {useRef, useState } from 'react';
import {
    Alert,
    StyleSheet,
    Text,
    View,
    Keyboard,
    KeyboardAvoidingView,
    Platform
} from 'react-native';
import {SafeAreaView} from 'react-native-safe-area-context'


import SignForm from '../components/SignForm';
import SignButtons from '../components/SignButtons';

import {signIn, signUp} from '../lib/auth'
import {getUser} from '../lib/users'

import {useUserContext} from '../contexts/UserContext'

function SignInScreen({ navigation, route }) {
    //로그인인지 회원 가입인지 구분하기 위한 변수를 생성
    const {isSignUp} = route.params ?? {};

    //속성 과 속성을 수정하는 함수 그리고 기본값을 설정
    const [form, setForm] = useState({
      email:'',
      password:'',
      confirmPassword:''
    });

    const [loading, setLoading] = useState();
    const {setUser} = useUserContext();
    //form 에 데이터를 설정하는 함수 - BorderedInput에 연결
    const createChangeTextHandler = name => value => {
      //form 속성 안에서 name에 value를 설정
      setForm({...form, [name]:value});
    };

    //버튼을 눌렀을 때 호출될 함수
    const onSubmit = async() => {
      Keyboard.dismiss();
      console.log(form);

      //입력한 내용 가져오기
      const {email, password} = form;
      const info = {email, password};
      //스피너를 화면에 출력
      setLoading(true);

      try{
        //isSignUp 에 따라 파이어베이스에 저장하던가 로그인을 수행
        const {user} = isSignUp? await signUp(info) : await signIn(info);
        //작업이 성공적으로 완료되면 uid를 이용해서 user의 정보를 가져오기
        const profile = await getUser(user.uid);
        if(!profile){
          navigation.navigate('Welcome', {uid:user.uid});
        }else{
            setUser(profile)
        }
        console.log(user);
      }catch(e){
        console.log(e);

        const messages = {
          'auth/email-already-in-use':'이미 가입된 이메일입니다.',
          'auth/wrong-password': '잘못된 비밀번호입니다.',
          'auth/user-not-found': '존재하지 않는 계정입니다.',
          'auth/invalid-email': '유효하지 않은 이메일 입니다.'
        };

        const msg = messages[e.code] || `${isSignUp ? '가입' : '로그인'} 실패`;
        Alert.alert('실패', msg);
      }

    }

    //password 와 confirmPassword에 대한 참조를 가져온 것입니다.
    const passwordRef = useRef();
    const confirmPasswordRef = useRef();

    return (
        <KeyboardAvoidingView style={styles.KeyboardAvoidingView} 
        behavior={Platform.select({ios:'padding'})}>

        <SafeAreaView style={styles.fullscreen}>
            <Text style={styles.text}>PublicGallery</Text>
            <View style={styles.form}>
              <SignForm style={styles.form}
                isSignUp={isSignUp}
                onSubmit = {onSubmit}
                form={form}
                createChangeTextHandler = {createChangeTextHandler} />

              <SignButtons isSignUp={isSignUp} onSubmit={onSubmit} loading={loading}/>
            </View>  
        </SafeAreaView>
        </KeyboardAvoidingView>
    );
}

const styles = StyleSheet.create({
  KeyboardAvoidingView:{
    flex:1
  },
    fullscreen: {
      flex: 1,
      alignItems: 'center',
      justifyContent: 'center',
    },
    text: {
      fontSize: 32,
      fontWeight: 'bold',
    },
    form:{
      marginTop:64,
      width:'100%',
      paddingHorizontal:16
    },
    buttons:{
      marginTop:64
    }
  });
  
  export default SignInScreen;
  