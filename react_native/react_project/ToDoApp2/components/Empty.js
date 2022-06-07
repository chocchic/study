import React from 'react'
import {View, Text, StyleSheet, Image} from 'react-native'
import {useSafeAreaInsets} from 'react-native-safe-area-context'

function Empty({data}){
    return (
        <View style = {styles.block}>
            <Image source={require('../assets/images/young_and_happy.png')} style={styles.image} resizeMode="stretch"/>
            <Text style={styles.description}>현재는 할 일이 없습니다.</Text>
        </View>
    );
}

const styles = StyleSheet.create({
    block:{
        flex:1,
        alignItems:'center',
        justifyContent:'center'
    },
    description:{
        fontSize:24,
        color:'#9e9e9e'
    },
    image:{
        width:240,
        height:179,
        marginBottom:16
    }
});

export default Empty;