import React from 'react'

import {View, Text} from 'react-native'

function Greeting(props){
    return(
        <>
        <View>
            <View>
                <Text>안녕하세요 {props.name}님. 반갑습니다.</Text>
            </View>
            <Text>보조 텍스트 밖에 View로 한번더 감싸서 오류 X</Text>
        </View>
        </>
    );
}

Greeting.defaultProps = {
    name:"홍길동"
}

export default Greeting