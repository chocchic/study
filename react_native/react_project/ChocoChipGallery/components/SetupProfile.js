
import { useNavigation, useRoute } from "@react-navigation/native";
import React, {useState} from 'react';
import {StyleSheet, View, Pressable, Platform, Image} from 'react-native';
import {signOut} from '../lib/auth';
import {createUser} from '../lib/users';

import BorderedInput from "./BorderedInput";
import CustomButton from "./CustomButton";

import {useUserContext} from '../contexts/UserContext'

import {launchImageLibrary} from 'react-native-image-picker'

function SetupProfile(){
    //닉네임 변수
    const [displayName, setDisplayName] = useState("");
    //화면 전환을 수행하는 navigation 찾아오기
    const navigation = useNavigation();

    //파라미터 생성
    const {params} = useRoute();

    const {uid} = params || {};

    const {setUser} = useUserContext();
    const [response, setResponse] = useState(null);

    //버튼을 눌렀을 때 Firebase 의 Storage에 저장
    const onSubmit = async () => {
        const user = {
            id:uid,
            displayName,
            photoURL:null
        }
        createUser(user);
        setUser(user);
    }

    //취소를 누른 경우
    const onCancel = () => {
        //로그 아웃
        signOut();
        //이전 화면으로 돌아가기
        navigation.goBack();
    }
    // 원 부분을 눌렀을 때 호출될 함수  
    const onSelectImage = () => {
        launchImageLibrary({
            mediaType :'photo',
            maxWidth:512,
            maxHeight:512,
            includeBase64: Platform.OS === 'android'
        },
        (res) =>{
            if(res.didCancel){
                return;
            }
            setResponse(res)
            console.log({uri:response?.assets[0]?.uri})
        }
        )
    }
    return (
        <View style={styles.block}>
            <Pressable onPress={onSelectImage}>
                <Image style={styles.circle} 
                source={response?{uri:response?.assets[0]?.uri} : require('../assets/user.png')} / >
            </Pressable>
            <View style={styles.circle} />
            <View style={styles.form} >
                <BorderedInput
                    placeholder="닉네임"
                    value={displayName}
                    onChangeText={setDisplayName}
                    onSubmitEditing={onSubmit}
                    returnKeyType="next"/>
                <CustomButton title="다음" onPress={onSubmit} hasMarginBottom />
                <CustomButton title="취소" onPress={onCancel} theme="secondary" />
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    block:{
        alignItems:'center',
        marginTop: 24,
        paddingHorizontal: 16,
        width:'100%'
    },
    circle:{
        backgroundColor:'#cdcdcd',
        borderRadius: 64,
        width:128,
        height:128
    },
    form:{
        marginTop:16,
        width:'100%'
    },
    buttons:{
        marginTop:48
    }
})

export default SetupProfile;