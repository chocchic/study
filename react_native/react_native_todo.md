# ToDo 애플리케이션  
## 1. 프로젝트 생성 및 실행  
* npx react-native init ToDoApp  
* Visual Code에서 프로젝트를 열기  
* 
*  안드로이드 빌드 및 실행  
    yarn android

## 2. 기본화면에 ToDoApp이라는 텍스트를 출력
* App.js 파일을 수정 
```javascript
```

## 3. 상단에 현재 날짜를 출력
### 1) DateHead컴포넌트(components/DateHead.js)
```javascript
import React from 'react'
import {View, Text, StyleSheet} from 'react-native'

function DateHead({data}){
    const year = date.getFullYear();
    const month = date.getMonth + 1;
    const day = date. getDate();

    const formatted = `${year}년 ${month}월 ${day}일`

    return (
        <View style={styles.block}>
            <Text style={styles.dateText}>{formatted}</Text>
        </View>
    );
}

const styles = StyleSheet.create({
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
```javascript