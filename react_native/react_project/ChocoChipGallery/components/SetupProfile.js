import { useNavigation, useRoute } from "@react-navigation/native";
import React, { useState } from 'react';
import { StyleSheet, View } from "react-native";
import { signOut } from "../lib/auth";
import { createUser } from "../lib/users";

import Borderedinput from "./Borderedinput";
import CustomButton from "./CustomButton";

function SetupProfile(){
    // 닉네임 변수
    const [displayName, setDisplayName] = useState("");
    // 화면전환을 수행하는 navigation 찾아오기
    const navigation = useNavigation();

    // 파라미터 생성
    const {params} = useRoute();
    const {uid} = params || {};
    
    // 버튼을 눌렀을 때 Firebase의 Storage에 저장
    const onSubmit = async function(){
        createUser({id:uid, displayName, photoURL:null})
    }

    // 취소를 누른 경우
    const onCancel = () =>{
        // 로그아웃
        signOut();
        // 이전 화면으로 돌아가기
        navigation.goBack();
    }
    return (
        <View style={styles.block}>
            <View style={styles.circle} />
            <View style={styles.form}>
                <Borderedinput placeholder="닉네임" value={displayName} onChnageText={setDisplayName} onSubmitEditing={onSubmit} returnKeyType="next"/>
                <CustomButton title="next" onPress={onSubmit} hasMarginBottom />
                <CustomButton title="cancel" onPress={onCancel} theme="secondary" />
            </View>
        </View>
    )
}

const styles = StyleSheet.create({

})

export default SetupProfile;