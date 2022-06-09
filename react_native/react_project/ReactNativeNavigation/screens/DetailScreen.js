import React, {useEffect} from "react";
import {View, Text, StyleSheet, Button} from 'react-native'

function HomeScreen({route, navigation}){
    return (
        <View style={styles.block}>
            <Text style={styles.text}>id:{route.param.id}</Text>
            <View style={styles.buttons}>
                <Button title ="다음" onPress={()=>navigation.push("Detail", {id:route.param.id+1})}/>
                <Button title ="뒤로" onPress={()=>navigation.pop()}/>
                <Button title ="처음" onPress={()=>navigation.popToTop()}/>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    block:{
        flex:1,
        alignItems: 'center',
        justifyContent:'center'
    },
    text:{
        fontSize:48
    },
    buttons:{
        flexDirection:'row'
    }
})

export default DetailScreen