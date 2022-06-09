import React, {useEffect} from "react";
import {View, Button} from 'react-native'

function HomeScreen({navigation}){
    // useEffect(()=>{
    //     navigation.setOptions({title:'함수를 이용한 변경'})
    // }, [navigation])

    return (
        <View>
            <Button title="Detail 1 Open" onPress={()=> navigation.naviate("Detail", {id:1})} />
            <Button title="Detail 2 Open" onPress={()=> navigation.naviate("Detail", {id:2})} />
            <Button title="Detail 3 Open" onPress={()=> navigation.naviate("Detail", {id:3})} />
            <Button title="Headerless" onPress={()=> navigation.naviate("Headerless")}/>
        </View>
    );
}

export default HomeScreen