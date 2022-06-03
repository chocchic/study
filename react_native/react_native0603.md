# Component  
=> 부품  
=> Component는 유저 인터페이스를 구성하는 요소로 일반적으로 인스턴스 생성성을 개발자가 직접 하지 않는 구성요소  

* Android의 4대 컴포넌트  
    Activity : 화면  
    Service : 작업  
    Broadcast : 알림  
    ContentProvider : 데이터 공유  

    이 4가지 요소는 클래스는 우리가 생성하고 AndroidManifest.xml에 등록을 해서 사용합니다. 직접 인스턴스 생성을 하지 않고 Android가 만들어준 요소를 우리가 사용합니다.  

* 이러한 프로그래밍 방식을 IoC(제어의 역전, 제어의 역흐름 - Inversion of Control)라고 합니다.  
CBD(Component Based Development - 컴포넌트 조합으로 애플리케이션을 개발하는 방식으로 개발 생산성과 품질을 높이는 방식)라고도 합니다.  

* react에서는 컴포넌트가 뷰의 역할과 사용자의 이벤트 처리를 수행  

## 1. 프로젝트 생성하고 안드로이드에서 실행  
### 1) 프로젝트 생성 - npx react-native init ReactNativeComponent  

### 2) Metro 빌드  
yarn start

### 3) 안드로이드 빌드 및 에뮬레이터 실행  
* 안드로이드 에뮬레이터를 실행  
* yarn android  

### 4) iOS 빌드 및 시뮬레이터에서 실행  

### 5) 실행 시 오류 
* yarn start 명령이 이미 포트를 사용하고 있다고 되지 않을 때
    터미널에서 nestat -ano로 오류메세지에서 나온 사용중인 포트번호를 찾는다. 
    pid 알아내서 taskkill /f /pid pid번호 로 그 프로세스 강제종료(/f)

* yarn android 명령을 사용하려고 했는데, sdk 경로를 인식할 수 없다고 에러가 발생  
    프로젝트의 android 디렉터리에 local.properties 파일이 생성되었는지 확인  
    프로젝트의 android 디렉터리에 local.prorperties 파일을 생성한 후 작성  
    sdk.dir = 자기 컴퓨터의 appdata\local\android\sdk 찾아서 입력(\는 \\로 바꾸어서 입력해주어야 함)

## 2. Component를 생성하고 화면에 출력  
* Component를 저장할 components디렉터리를 루트 디렉터리에 생성  

* components디렉터리에 컴포넌트를 작성할 Greeting.js파일을 생성
```javascript
import React from 'react'

import {View, Text} from 'react-native'

function Greeting(){
    return(
        <View>
            <Text>안녕하세요 반갑습니다.</Text>
        </View>
    );
}

export default Greeting
```  

### 3) App.js 파일의 내용을 수정해서 Greeting의 내용을 출력  
```javascript
/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * 
 * @format
 * @flow strict-local
 */

import React from 'react';

import {SafeAreaView} from 'react-native'
import Greeting from './components/Greeting';

const App = ()=>{
  return (
    <SafeAreaView>
      <Greeting/>
    </SafeAreaView>
  )
}

export default App;
```  

## 3. 매개변수를 받아서 내용을 출력  
* 속성을 이용하게 되는데 속성을 react에서는 props라는 단어로 많이 사용합니다.  

### 1) Greeting.js파일을 수정
```javascript
function Greeting(props){
    return(
        <View>
            <Text>안녕하세요 {props.name}님. 반갑습니다.</Text>
        </View>
    );
}
```  

### 2) App.js파일을 수정  
```javascript
const App = ()=>{
  return (
    <SafeAreaView>
      <Greeting name="초코칩"/>
    </SafeAreaView>
  )
}
```  

### 3) App.js 파일에서 name속성을 설정하지 않으면 prop.name자리에 아무것도 출력되지 않음  
* Greeting.js파일에 name의 속성의 기본값 설정  
```javascript
Greeting.defaultProps = {
    name:"홍길동"
}
```

* App.js파일에서 Greeting을 호출할 때 name 속성의 값을 생략하고 확인  

## 4. JSX 문법  
### 1) 태그는 열면 반드시 닫아야 합니다.  
### 2) 리턴을 할 때는 하나의 태그로 감싸야 합니다.  
    <> </> 태그(segment) 사용이 가능  

### 3) js파일 내에서 표현식을 사용할 때는 { }로 감싸야 합니다.  

### 4) 주석  
{/* 주석 */}로 주석을 만들 수 있습니다.  
//로도 주석을 만들 수 있는데 이 경우는 주석의 마지막에서 반드시 줄 바꿈이 되어야 합니다.  

## 5. StyleSheet  
* 스타일시트를 이용해서 디자인 적용이 가능  

### 1) Web에서와의 차이점  
* 별도의 css파일에 스타일을 작성하지 않고 자바스크립트 파일안에서 StyleSheet를 이용해서 작성  
* selector의 개념이 없음  
* 모든 스타일 속성은 camelCase(첫 글자는 소문자로 시작하고 두번째 단어부터는 첫글자를 대문자로 작성)로 작성  
* display속성은 기본적으로 flex이면 다른 값으로는 none만 존재  
* flexDirection 속성의 값은 column  
* 크기 단위는 dp(픽셀과는 상관없는 독립적인 단위)만 가능  
* Background대신에 backgroundcolor를 사용  
* border 대신에 borderwidth, borderstyle, borderColor들을 별도로 설정  

### 2) StyleSheet를 적용
* components 디렉터리에 컴포넌트로 사용할 Box.js파일을 생성  
```javascript
import React from 'react'
import {View, StyleSheet } from 'react-native'

function Box(props){
    return (
        <View style={styles.box}/>
    );
}
const styles = StyleSheet.create({
    box:{
        width:64,
        height:64,
        backgroundColor: 'black'
    }
});
export default Box;
```  

* App.js파일에서 Box를 가져다가 출력  
```javascript
const App = ()=>{
  const name = "JSX"
  return (
    <SafeAreaView>
      <Box/>
    </SafeAreaView>
  )
}
```  