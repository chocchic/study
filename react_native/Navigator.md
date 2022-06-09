# Navigator  
* 여러 화면을 좌우나 상하로 슬라이딩해서 전환하거나 탭을 이용해서 화면을 전환하는 것  

## 1. react-native navigation 라이브러리  
### 1) react-navigation
리액크 공식 매뉴얼에서 이용, 네비게이션 기능이 자바스크립트 구현  
### 2) react-native-navigation  
* Wix(웹 애플리케이션 제작 회사)에서 관리  
* 네비게이션 기능이 네이티브로 구현  
* 네이티브스러운 사용 경험을 제공  

## 2. 네비게이션 기능을 사용하기위한 설정  
* @react-navigation/native, react-native-screens, react-native-safe-area-context 라이브러리를 설치해야 합니다.  

### 1) 프로젝트 생성
npx react-native init ReactNativeNavigation  

### 2) 필요한 라이브러리 설치  
cd ReactNativeNavigation  
yarn add @react-navigation/native react-native-screens react-native-safe-area-context  

* Mac에 Xcode와 cocoa pods가 설치된 경우만 수행
 cd ios
 pod install

### 3) 앱을 다시 빌드  
yarn android
yarn ios  

### 4) 네비게이션을 전체 영역에 설정 -  NavigationContainerr로 컴포넌트를 감싸면 됩니다.  
* App.js 수정
```javascript
import React from 'react'
import { NavigationContainer } from '@react-navigation/native';

function App(){
  return (
    <NavigationContainer>
      
    </NavigationContainer>
  )
}
export default App;
```  

## 3. 기본적인 사용법  
* Android와 iOS에서는 화면전환에 Stack과 Array(List)를 이용합니다.
네비게이션(화면이 좌우나 상하로 슬라이딩)의 형태는 Stack을 이용  
탭(하단이나 상단에 여러 개의 아이콘을 배치해서 화면 전환)의 경우는 Array를 이용  

### 1) 네이티브 스택 네비게이터  
* 안드로이드에서는 Fragment를 이용하고 iOS에서는 UINavigationController를 이용해서 구현
* 설치 : @react-navigation/native-stack  
* 도큐먼트 : https://reactnavigation.org/docs/native-stack-navigator  

### 2) 화면 전환  
* 이동할 화면에 해당하는 컴포넌트들을 저장할 디렉터리를 생성 - screens  

* Main화면으로 사용할 화면을 screens디렉터리에 생성 - HomeScreen.js  
```javascript
import React from "react";
import {View, Button} from 'react-native'

function HomeScreen({navigation}){
    return (
        <View>
            <Button title="Detail Open" />
        </View>
    );
}

export default HomeScreen
```  

* Detail화면으로 사용할 화면을 screens디렉터리에 생성 - DetailScreen.js
```javascript
import React from "react";
import {View, Text} from 'react-native'

function HomeScreen({navigation}){
    return (
        <View>
            <View>
                <Text>Detail</Text>
            </View>
        </View>
    );
}

export default DetailScreen
```  

* App.js파일을 수정해서 네비게이터 배치  
```javascript
import React from 'react'
import { NavigationContainer, StackActions } from '@react-navigation/native';

import {createNativeStackNavigator} from '@react-navigation/native-stack'
import HomeScreen from './screens/HomeScreen';
import DetailScreen from './screens/DetailScreen';

const Stack = createNativeStackNavigator()

function App(){
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName='Home'>
        <Stack.Screen name='Home' component={HomeScreen}/>
        <Stack.Screen name='Detail' component={DetailScreen}/>
      </Stack.Navigator>
    </NavigationContainer>
  )
}
export default App;
```  

### 4) 