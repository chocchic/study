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
* iOS - 이 작업은 Mac에서만 수행하면 됩니다.  
  iOS 디렉터리로 이동해서 pod install  
  pod은 Xcode의 의존성 관리자로 react-native가 기본 의존성 관리자로 pod을 사용하고있어서 pod으로 의존성을 설정해야하는 경우가 있습니다.  
  iOS 디렉터리의 info.plist파일을 수정해야하는 경우도 발생합니다.  

* android  
  build.gradle파일에서 의존성을 설정해야하는 경우가 있습니다.  

### 5) App.js 수정  
```javascript
import { SafeAreaView, SafeAreaProvider} from 'react-native-safe-area-context'

function App(){
  const today = new Date();
  return(
    <SafeAreaProvider>
      <SafeAreaView>
        <View>
          <Text>ToDoApp</Text>
          <DateHead date ={today}/>
        </View>
      </SafeAreaView>
    </SafeAreaProvider>
  )
}
```  

### 6) DateHead.js파일 수정 - StatusBar의 높이를 구해서 여백을 수정  
```javascript
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
```  

## 4. 하단영역을 생성  
### 1) Components 디렉터리에 하단 영역에 해당하는 컴포넌트를 위한 AddToDO.js파일을 생성하고 작성 
```javascript
import react from "react";
import {View, StyleSheet} from 'react-native'

function AddToDo(){
    return (
        <View style = {styles.block}>
        </View>
    )
}

const styles = StyleSheet.create({
    block:{
        backgroundColor:'red',
        height:64
    }
})

export default AddToDo;
```  

## 2) App.js파일을 수정해서 AddToDo컴포넌트를 배치  
```javascript
import AddToDo from './components/AddToDo'

function App(){
  const today = new Date();
  return(
    <SafeAreaProvider>
      <SafeAreaView edges={['bottom']} style={styles.block}>
        <View>
          <DateHead date ={today}/>
          <AddToDo/>
        </View>
      </SafeAreaView>
    </SafeAreaProvider>
  )
}

const styles = StyleSheet.create({
  block:{
    flex:1
  }
});
```  

## 5. 데이터가 없을 때

### 1)
```javascript
import React from 'react'
import {View, Text, StyleSheet, StatusBar} from 'react-native'
import {useSafeAreaInsets} from 'react-native-safe-area-context'

function Empty({data}){
    return (
        <View style = {styles.block}>
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
    }
});

export default Empty;
```

### 2) App.js파일 수정  
```javascript
import Empty from './components/Empty'

function App(){
  const today = new Date();
  return(
    <SafeAreaProvider>
      <SafeAreaView edges={['bottom']} style={styles.block}>
        <View>
          <DateHead date ={today}/>
          <Empty/>
          <AddToDo/>
        </View>
      </SafeAreaView>
    </SafeAreaProvider>
  )
}
```  

### 3) 출력할 이미지를 루트디렉터리에 복사 - assets/images 디렉터리에 복사 - circle.png, circle@2x.png, circle@3x.png, young_and_happy.png파일을 복사  
  스마트 디바이스는 디바이스마다 해상도가 다른데, 이때 해상도에 따라 다른 이미지를 출력하고자 하는 경우 기본이름@2x.확장자, 기본이름@3x.확장자의 형태로 이미지를 만들어주면 됩니다.  
  고해상도의 경우는 @3x파일을 출력하고 저해상도인 경우는 기본이름으로 만들어진 파일을 출력합니다.  
  자신의 해상도에 맞는 파일이 없어도 기본 이름으로 만들어진 파일을 출력합니다.  
