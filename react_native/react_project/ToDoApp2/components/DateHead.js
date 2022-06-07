import React from 'react'
import {View, Text, StyleSheet, StatusBar} from 'react-native'
import {useSafeAreaInsets} from 'react-native-safe-area-context'

function DateHead({data}){
    const year = date.getFullYear();
    const month = date.getMonth + 1;
    const day = date. getDate();
    const formatted = `${year}년 ${month}월 ${day}일`

    // 상태 표시줄의 높이를 구함  
    const {top} = useSafeAreaInsets()

    return (
        <>
            <View style={[styles.StatusBarPlaceholder,{height:top}]}/>
            <StatusBar backgroundColor={"#26a69a"}/>
            <View style={styles.block}>
                <Text style={styles.dateText}>{formatted}</Text>
            </View>
        </>
    );
}

const styles = StyleSheet.create({
    StatusBarPlaceholder:{
        backgroundColor : '#26a69a'
    },
    block : {
        padding : 16,
        backgroundColor : '#26a69a'
    },
    dateText : {
        fontSize:24,
        color: 'white'
    }
});

export default DateHead;