import React from 'react'
import { StyleSheet, View, Pressable, Text, Platform } from 'react-native'

function CustomButton({onPress, title, hasMarginBottom, theme}){
    const isPrimary = theme === 'primary';

    return(
        <View style={[styles.block, hasMarginBottom && styles.margin]} >
            <Pressable onPress={onPress} style={({pressed})=> [styles.wrapper, 
                    Platform.OS === 'ios' && pressed && {opacity : 0.5}]}
                android_ripple={{color:isPrimary?'#ffffff':'#6200ee'}}>
                    <Text style={[styles.text]}>{title}</Text>
            </Pressable>

        </View>
    )
}

// 기본 값 설정
CustomButton.defaultProps = {theme:'primary'}

const styles = StyleSheet.create({
    overflow:{
        borderRadius:4,
        overflow:'hidden'
    },
    block:{

    },
    margin:{
        marginBottom:8
    },
    wrapper:{
        borderRadius:4,
        height:48,
        alignItems:'center',
        justifyContent:'center',
        backgroundColor:'#6200ee'
    },
    text:{
        fontWeight: 'bold',
        fontsize:24,
        color:'white'
    },
    primaryWrapper:{
        backgroundColor:'#6200ee'
    },
    primaryText:{
        color:'white'
    },
    secondaryText:{
        color:'#6200ee'
    }
})

export default CustomButton;