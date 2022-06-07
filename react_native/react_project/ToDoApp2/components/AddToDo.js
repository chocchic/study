import React, {useState} from "react";
import {View, StyleSheet,TextInput, Image, TouchableOpacity, Platform, TouchableNativeFeedback, Keyboard} from 'react-native'

function AddToDo(){
    // text라는 속성을 생성하고 setText라는 함수로 수정. 기본값은 ''
    const [text, setText] = useState('');
    console.log(text); // metro에 찍힘

    const onPress = () =>{
        setText("");
        Keyboard.dismiss();
    }

    return (
        <View style = {styles.block}>
            <TextInput placeholder="수행할 내용을 입력하세요" style={styles.input}
            value={text} onChangeText={setText} returnKeyType="done" onSubmitEditing={onPress}/>

            {Platform.OS === 'ios' ? (
                <TouchableOpacity activeOpacity={0.5} onPress={onPress}>
                    <View style={styles.buttonStyle}>
                        <Image source={require('../assets/icons/add_white/add_white.png')} />
                    </View>
                </TouchableOpacity>
            ) : (
                <View style={styles.circleWrapper}>
                    <TouchableNativeFeedback>
                        <View style={styles.buttonStyle}>
                            <Image source={require('../assets/icons/add_white/add_white.png')} />
                        </View>
                    </TouchableNativeFeedback>
                </View>
            )}
        </View>
    )
}

const styles = StyleSheet.create({
    block:{
        backgroundColor:'white',
        height:64,
        paddingHorizontal:16,
        borderColor:"#bdbdbd",
        borderBottomWidth:1,
        borderTopWidth:1,
        justifyContent:'center',
        flexDirection:'row' // 옆으로 매치하기 위해
    },
    input:{
        flex:1,
        fontSize:16,
        paddingVertical:8
    },
    buttonStyle:{
        alignItems:'center',
        justifyContent:'center',
        width:48,
        height:48,
        backgroundColor:'#26a69a',
        borderRadius:24
    },
    circleWrapper: {
        overflow: 'hidden',
        borderRadius:24
    }
})

export default AddToDo;