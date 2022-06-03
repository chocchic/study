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
### 3) Box.js파일에서 모서리를 둥글게 적용  
* Box.js파일을 수정  
```javascript
function Box(props){
    return (
        <View style={[styles.box, styles.rounded]}/>
    );
}
const styles = StyleSheet.create({
    box:{
        width:64,
        height:64,
        backgroundColor: 'black'
    },
    rounded:{
        borderRadius:16
    }
});
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

### 4) 조건 적용  
* Box.js 파일 수정  
```javascript
function Box(props){
    return (
        <View style={[styles.box, props.rounded ? styles.rounded : null]}/>
    );
}
```  

* App.js파일에서 Box를 가져다가 출력  
```javascript
const App = ()=>{
  const name = "JSX"
  return (
    <SafeAreaView>
      <Box rounded={true}/>
    </SafeAreaView>
  )
}
```  

### 5) 다양한 크기 적용  
* Box.js 수정  
```javascript
function Box(props){
    return (
        <View style={[styles.box, 
            props.rounded ? styles.rounded : null,
            sizes[props.size]
        ]}/>
    );
}

Box.defaultProps = {size:'medium'}

const styles = StyleSheet.create({
    box:{
        width:64,
        height:64,
        backgroundColor: 'black'
    },
    rounded:{
        borderRadius:16
    },
    small:{
        width:32,
        height:32
    },
    medium:{
        width:64,
        height:64
    },
    large:{
        width:128,
        height:128
    }
});

const sizes = {
    small:styles.small,
    medium:styles.medium,
    large:styles.large
}
```  

* App.js 수정  
```javascript
const App = ()=>{
  const name = "JSX"
  return (
    <SafeAreaView>
      <Box rounded={true} size="large"/>
    </SafeAreaView>
  )
}
```  

### 6) 색상 변경 
* Box.js 파일 수정  
```javascript
function Box(props){
    return (
        <View style={[styles.box, 
            props.rounded ? styles.rounded : null,
            sizes[props.size],
            {backgroundColor:props.color}
        ]}/>
    );
}

Box.defaultProps = {size:'medium', color:'black'}
```  

* App.js파일에서 Box를 호출할 때 Color값 설정  
```javascript
const App = ()=>{
  const name = "JSX"
  return (
    <SafeAreaView>
      <Box rounded={true} size="large" color="blue"/>
    </SafeAreaView>
  )
}
```  

### 7) 객체 구조 분해할당  
* 자바스크립트는 여러개의 데이터를 전달할 때 이를 분할해서 사용하는 것이 가능  
```javascript
function Box({rounded, size, color}){
    return (
        <View style={[styles.box, 
            rounded ? styles.rounded : null,
            sizes[size],
            {backgroundColor:color}
        ]}/>
    );
}
```  

## 6. useState Hook을 이용한 상태 관리  
### 1) 상태 관리  
* 속성의 값을 변경하는 것  
* 가장 기본적인 방법은 useState라는 함수를 이용  
* use로 시작하는 함수들을 Hook이라고 합니다.  

### 2) useState 함수  
* 상태값을 관리하는 함수  
const [visible, setVisible] = useState(true)  

visible이라는 상태의 값을 변경하는 함수는 setVisible이고 기본값은 true  

* useState를 호출하면 2개의 데이터가 배열로 반환되는데 첫번째 원소는 상태값을 그리고 두번째 원소는 상태값을 변경하는 함수  

### 3) App.js파일에 useState를 추가  
```javascript
 import React, {useState} from 'react';

 import {SafeAreaView} from 'react-native'
 import Greeting from './components/Greeting'
 import Box from './components/Box'
 
 const App = ()=>{
  const [visible, setVisible] = useState(true)
  const name = "JSX"
  return (
    <SafeAreaView>
      <Box rounded={true} size="large" color="blue"/>
      <Greeting name={name}/> // 한줄 주석
    </SafeAreaView>
  )
}

export default App;
```  

### 4) Hook의 규칙
* Hook은 컴포넌트의 최상위 레벨에서만 사용  
* 조건문이나 반복문 도는 중첩 함수에서 호출하면 안됨  
* 함수의 흐름 중간에 return 이 있는 겨웅 return 보다 먼저 등장해야 함  
* Hook은 react의 문법 - 일반 자바스크립트 구문에서 사용하면 에러  


### 5) Button을 눌러서 Box를 보이게 하고 보이지 않게 하기  
* App.js파일에 버튼을 추가하고 클릭 이벤트 처리를 수행  
```javascript
/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

 import React, {useState} from 'react';

 import {SafeAreaView} from 'react-native'
 import Greeting from './components/Greeting'
 import Box from './components/Box'
 
 const App = ()=>{
  const [visible, setVisible] = useState(true)
  const name = "JSX"
  const onPress = ()=>{
    setVisible(!visible);
  }
  return (
    <SafeAreaView>
      <Button title="버튼" onPress={onPress} />
      {visible  
        ? <Box rounded={true} size="large" color="blue"/>
        : null
      } 
      {/* 위와 같은 의미
      {visible && <Box rounded={true} size="large" color="blue"/>} */}
      <Greeting name={name}/> // 한줄 주석
    </SafeAreaView>
  )
}

export default App;
```  

* +) 연산자
    && 연산자 : 둘다 true일때만 true를 리턴하고 하나라도 false가 있으면 fasle 리턴, 앞의 결과가 false이면 뒤의 결과를 확인하지 않음    
    || 연산자 : 둘다 false일 때만 false를 리턴하고 하나라도 true가 있으면 true리턴, 앞의 결과가 true이면 뒤의 결과를 확인하지 않음  

## 7. Counter 만들기  
* 화면에 숫자를 출력하고 그 아래에 버튼을 2개 추가해서 위의 버튼이 눌러지면 숫자가 1증가하고 아래의 버튼이 눌러지면 숫자가 1 감소하도록 만들기  

### 1) Counter 화면 만들기 - components/Counter.js  
```javascript
import React from 'react'
import {View, Text, Button, StyleSheet} from 'react-native'

function Counter({count, onIncrease, onDecrease}){
    return (
        <View style={styles.wrapper}>
            <View style={styles.numberArea}>
                <Text style={styles.number}>{count}</Text>
            </View>
            <Button title='+1' onPress={onIncrease}/>
            <Button title='-1' onPress={onDecrease}/>
        </View>
    );
}

const styles = StyleSheet.create({
    wrapper : {
        flex:1
    },
    numberArea : {
        flex:1,
        alignItems:'center',
        justifyContent:'content'
    },
    number : {
        fontSize:72,
        fontWeight:'bold'
    }
});

export default Counter;
```  

### 2) App.js 수정하기
```javascript
/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

 import React, {useState} from 'react';

 import {SafeAreaView, StyleSheet, Button} from 'react-native'

 import Greeting from './components/Greeting'
 import Box from './components/Box'
 import Counter from './components/Counter';
 
 const App = ()=>{
   /*
  const [visible, setVisible] = useState(true)
  const name = "JSX"
  const onPress = ()=>{
    setVisible(!visible);
  }*/
  // count라는 상태를 생성하고 이 상태의 변경은 setCount함수를 이용
  // 기본값은 0
  const [count, setCount] = useState(0)

  // 버튼에 연결될 함수
  const onIncrease = () => setCount(count+1)
  const onDecrease = () => setCount(count -1)

  return (
    <SafeAreaView>
      <Counter count={count} onIncrease={onIncrease} onDecrease={onDecrease}/>
    </SafeAreaView>
  )
}

const styles = StyleSheet.create({
  full:{
    flex:1
  }
})
```  