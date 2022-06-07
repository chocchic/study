# ToDo 애플리케이션  
## 1. 프로젝트 생성 및 실행  
* npx react-native init ToDoApp  
* Visual Code에서 프로젝트를 열기  
*  안드로이드 빌드 및 실행  
    yarn android

## 2. 기본화면에 ToDoApp이라는 텍스트를 출력
* App.js 파일을 수정 
```javascript
import React from 'react';
import {
  SafeAreaView,
  ScrollView,
  Text,
  View,
} from 'react-native';

function App(){
  return(
    <SafeAreaView>
      <View>
        <Text>ToDoApp</Text>
      </View>
    </SafeAreaView>
  )
}

const styles = StyleSheet.create({})

export default App;
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

### 2) App.js 파일에서 DateHead.js파일에 작성된 컴포넌트 출력하기  
```javascript
import React from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';

import DateHead from './components/DateHeadDateHead';

function App(){
  const today = new Date();
  return(
    <SafeAreaView>
      <View>
        <Text>ToDoApp</Text>
        <DateHead date ={today}/>
      </View>
    </SafeAreaView>
  )
}

const styles = StyleSheet.create({});

export default App;
```  

### 3) StatusBar(상태표시줄)의 스타일 변경 - DateHead.js파일을 수정  
```javascript
import React from 'react'
import {View, Text, StyleSheet, StatusBar} from 'react-native'

function DateHead({data}){
    const year = date.getFullYear();
    const month = date.getMonth + 1;
    const day = date. getDate();

    const formatted = `${year}년 ${month}월 ${day}일`

    return (
        <>
            <StatusBar backgroundColor={"#26a69a"}/>
            <View style={styles.block}>
                <Text style={styles.dateText}>{formatted}</Text>
            </View>
        </>
    );
}
// .. 뒤에 생략
```  
-> Android와 iOS는 상태표시줄의 영역이 다름  

### 4) 특정 여백을 비활성화하고자 할 때 사용하는 react-native-safe-area-context  
yarn add react-native-safe-area-context  

* 외부 라이브러리를 설치했을 때 상황에 따라서 아래 작업을 수행해야 하는 경우가 있음  
* ios
  iOS 디렉터리로 이동해서 pod install  
  pod은 Xcode의 의존성 관리자로 react-native가 기본 의존성 관리자로 pod을 사용하고있어서 pod으로 의존성을 설정해야하는 경우가 있습니다.  
  iOS 디렉터리의 info.plist파일을 수정해야하는 경우도 발생합니다.  

* android  
  build.gradle파일에서 의존성을 설정해야하는 경우가 있습니다.  