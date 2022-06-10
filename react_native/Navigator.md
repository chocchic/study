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

* HomeScreen.js파일에 버튼을 누르면 DetailScreen으로 화면을 전환하도록 버튼의 클릭이벤트를 작성  
```javascript
<Button title="Detail Open" onPress={()=> navigation.naviate("Detail")} />
```  

### 3) Route Parameter  
* 상위 화면에서 하위 화면으로 데이터를 넘겨주기 위해 제공하는 기능  
* naviagee나 push함수로 전환을 할 때 두번째 매개변수로 객체를 생성해서 넘겨주면 됩니다.  
전환된 화면에서는 화면의 첫번째 매개변수.params.이름을 이용해서 접근합니다.  

* Home.Screen.js파일을 수정해서 DetailScreen에게 데이터를 넘겨주도록 하기  
```javascript
function HomeScreen({navigation}){
    return (
        <View>
            <Button title="Detail 1 Open" onPress={()=> navigation.naviate("Detail", {id:1})} />
            <Button title="Detail 2 Open" onPress={()=> navigation.naviate("Detail", {id:2})} />
            <Button title="Detail 3 Open" onPress={()=> navigation.naviate("Detail", {id:3})} />
        </View>
    );
}
```  

* DetailScreen.js파일에서 넘겨받은 파라미터를 출력하도록 작성  
```javascript
import React from "react";
import {View, Text, StyleSheet} from 'react-native'

function HomeScreen({route, navigation}){
    return (
        <View style={styles.block}>
            <Text style={styles.text}>id:{route.param.id}</Text>
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
    }
})

export default DetailScreen
```  

### +) 데이터 공유  
* 애플리케이션과 애플리케이션의 사이의 데이터  
서버를 이용해서 서버에 데이터를 저장하고 다른 애플리케이션의 서버의 데이터를 읽는 방법  

애플리케이션과 애플리케이션이 통신을 하는 형태로 데이터를 공유  

* 애플리케이션 내에서 데이터 공유를 위해서는 데이터를 공유하기 위한 것들의 관계를 파악해야 합니다.  

가장 단순하지만 권장하지 않는 방법은 전역변수를 생성하는 것입니다. 객체지향에서는 접근 권한을 가진 것만 데이터를 사용할 수 있도록 하는것을 권장하기 때문에 비추천  

객체와 객체가 동일한 클래스로부터 만들어진 경우에는 static 변수입니다.  

하나의 객체 안에서 다른 객체가 생성되는 경우는 생성자나 setter 또는 사용하고자 하는 매서드에 매개변수로 공유할 데이터를 넘겨줍니다.  
iOS에서는 데이터를 직접 넘겨야하지만 Android에서는 데이터를 넘겨주기 위한 Extras라는 객체가 제공됩니다.  

### 4) push  
* navigate는 현재 화면으로 전환하고자 하는 경우 현재 화면ㅇ르 새로 만들지 않고 파라미터만 변경  
* push는 현재 화면으로 전환하고자 하는 경우에도 화면을 새로 만들어서 스택에 저장합니다.  

* DetailScreen.js파일에 화면으로 이동하는 버튼을 추가하고 확인 - navigate와 push를 번갈아가면서 사용하고 상단의 뒤로 버튼을 확인  
```javascript
import React from "react";
import {View, Text, StyleSheet, Button} from 'react-native'

function HomeScreen({route, navigation}){
    return (
        <View style={styles.block}>
            <Text style={styles.text}>id:{route.param.id}</Text>
            <Button title ="다음" onPress={()=>navigation.navigate("Detail", {id:route.param.id+1})}/>
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
    }
})

export default DetailScreen
```  

### 5) 뒤로 가기  
* 안드로이드나 iOS 모두 뒤로가기 기능을 제공  
* 직접 코드로 뒤로가기를 구현하고자 하는 경우는 화면 전환시 넘겨받은 두번째 매개변수를 이용해서 pop()을 호출하면 자신을 호출한 곳으로 전환하고 popToTop()을 호출하면 맨 처음 슽택이 만들어진 곳으로 전환이 됩니다.  

* 뒤로가기를 직접 구현하는 코드를 detailScreen.js파일에 작성  
```javascript
import React from "react";
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
```  

### 6) 헤더(네비게이션 바) 커스터마이징 - navigation으로 이동하는 경우 상단에 만들어지는 바를 react-native에서는 header라고 합니다.  
* Stack.Screen의 Props로 설정  

* navigation.setOptions함수로 설정 - App.js파일 수정  
```javascript
<Stack.Screen name='Home' component={HomeScreen} options={{title:"홈"}}/>
```  

* HomeScreen.js파일을 수정해서 함수를 통해 헤더의 내용을 변경  
```javascript
import React, {useEffect} from "react";
import {View, Button} from 'react-native'
import { useEffect } from 'react/cjs/react.production.min';

function HomeScreen({navigation}){
    useEffect(()=>{
        navigation.setOptions({title:'함수를 이용한 변경'})
    }, [navigation])

    return (
        <View>
            <Button title="Detail 1 Open" onPress={()=> navigation.naviate("Detail", {id:1})} />
            <Button title="Detail 2 Open" onPress={()=> navigation.naviate("Detail", {id:2})} />
            <Button title="Detail 3 Open" onPress={()=> navigation.naviate("Detail", {id:3})} />
        </View>
    );
}

export default HomeScreen
```  

* DetailScreen의 헤더 타이틀을 파라미터로 넘겨받은 데이터로 설정하도록 App.js를 수정  
```javascript
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
```  

원하는 화면으로 전환 -> react-native나 native코드에서는 인덱스를 이용하는 방식으로는 안되고 이름을 이름을 이용해서는 가능  

* headerStyle 속성을 이용하면 header의 모양을 변경할 수 있음  

* HomeScreen.js 파일을 수정해서 HomeScreen의 헤더모양을 변경  
```javascript
import React, {useEffect} from "react";
import {View, Button} from 'react-native'
import { useEffect } from 'react/cjs/react.production.min';

function HomeScreen({navigation}){
    useEffect(()=>{
        navigation.setOptions({title:'함수를 이용한 변경'})
    }, [navigation])

    return (
        <View>
            <Button title="Detail 1 Open" onPress={()=> navigation.naviate("Detail", {id:1})} />
            <Button title="Detail 2 Open" onPress={()=> navigation.naviate("Detail", {id:2})} />
            <Button title="Detail 3 Open" onPress={()=> navigation.naviate("Detail", {id:3})} />
        </View>
    );
}

export default HomeScreen
```  

* DetailScreen 의 헤더의 타이틀을 파라미터로 넘겨받은 데이터로 설정하도록 App.js를 수정  
```javascript
import React from 'react';
import  {NavigationContainer, StackActions} from '@react-navigation/native'

import {createNativeStackNavigator} from '@react-navigation/native-stack'
import HomeScreen from './screens/HomeScreen';
import DetailScreen from './screens/DetailScreen';

//스택 생성
const Stack = createNativeStackNavigator();

function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home" component={HomeScreen} options={{title:"홈"}}/>
        <Stack.Screen name="Detail" component={DetailScreen} 
        options={({route}) => ({title: `상세 정보 - ${route.params.id}`})}/>
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;
```  

* headerStyle 속성을 이용하면 header 의 모양을 변경할 수 있음

* App.js 파일을 수정해서 HomeScreen의 헤더 모양을 변경  
```javascript
import React from 'react';
import  {NavigationContainer, StackActions} from '@react-navigation/native'

import {createNativeStackNavigator} from '@react-navigation/native-stack'
import HomeScreen from './screens/HomeScreen';
import DetailScreen from './screens/DetailScreen';

//스택 생성
const Stack = createNativeStackNavigator();

function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home" component={HomeScreen} options={{title:"홈",
      headerStyle:{
        backgroundColor: '#2966f6',
        headerTintColor: '#ffff00',
        headerTitleStyle:{fontWeight:'bold', fontSize:40}
      }}}/>
        <Stack.Screen name="Detail" component={DetailScreen} 
        options={({route}) => ({title: `상세 정보 - ${route.params.id}`})}/>
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;
```  

* header(Navigation Bar)에는 왼쪽 부분과 가운데 영역과 오른쪽 부분으로 나누어져 있습니다.  
왼쪽 영역에 뒤로가기를 배치하고 가운데 영역에는 현재 영역에 대한 설명을 나타내는 텍스트나 이미지를 배치하고 오른쪽은 편집할 수 있는 UI를 제공하는 경우가 많습니다.  

* 상세보기 화면인 DetailScreen 에 header 부분에 UI를 변경하기 위해서 App.js 파일을 수정  
```javascript
import React from 'react';
import  {NavigationContainer, StackActions} from '@react-navigation/native'

import {createNativeStackNavigator} from '@react-navigation/native-stack'
import HomeScreen from './screens/HomeScreen';
import DetailScreen from './screens/DetailScreen';

import {View, Text, TouchableOpacity} from 'react-native'
//스택 생성
const Stack = createNativeStackNavigator();

function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home" component={HomeScreen} options={{title:"홈",
      headerStyle:{
        backgroundColor: '#2966f6',
        headerTintColor: '#ffff00',
        headerTitleStyle:{fontWeight:'bold', fontSize:40}
      }}}/>
        <Stack.Screen name="Detail" component={DetailScreen} 
        options={{
          //뒤로가기 버튼 삭제
          headerBackVisible:false,
          //바의 왼쪽에 버튼 생성
          headerLeft:({onPress}) => (
            <TouchableOpacity onPress={onPress}>
              <Text>Left</Text>
            </TouchableOpacity>
          ),
          //바의 가운데에 텍스트 출력
          headerTitle:({children}) => (
            <View>
              <Text>{children}</Text>
            </View>
          ),
          //바의 오른쪽에 배치
          headerRight:() => (
          <View>
            <Text>오른쪽</Text>
          </View>
        )
        }}/>
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;
```  

### 7) 헤더 숨기기  
* 네비게이션 이동 중에 특정 화면에서 헤더를 숨기고자 하는 경우에는 options에 headerShown이라는 속성의 값을 false로 설정하면 됩니다.  
* 헤더가 없기 떄문에 뒤로가기를 위한 UI를 직접 생성해야 합니다.  
* 헤더가 없는 컴포넌트를 screens/HeaderlessScreen.js파일로 작성  
```javascript
import React from "react";
import {View, Button, Text} from 'react-native'
import {SafeAreaView} from 'react-native-safe-area-context'

function HeaderlessScreen({navigation}){
    return (
        <SafeAreaView>
            <View>
                <Text>HeaderLess</Text>
                <Button onPress={()=>{navigation.pop()}} title='뒤로'/>
            </View>
        </SafeAreaView>
    );
}

export default HeaderlessScreen
```  

* App.js에서 추가한 컴포넌트를 출력하기 위한 코드를 작성  
```javascript
// ... 위에 생략 ... 
        <Stack.Screen name='Headerless' component={HeaderlessScreen} options={{
          headerShown: false
        }}/>
      </Stack.Navigator>
    </NavigationContainer>
  )
}
export default App;
```  

* HomeScreen.js파일에 HeaderlessScreen으로 이동할 수 있는 UI를 추가  
```javascript
import React from "react";
import {View, Button} from 'react-native'

function HomeScreen({navigation}){
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
```  

## 4. 여러 형태의 화면 전환  
### 1) 하단 탭 네비게이터  
* 도큐먼트 : https://reactnavigation.org/docs/bottom-tab-navigator  
* 설치
    yarn add @react-navigation/bottom-tabs react-native-vector-icons  
* Mac 환경 추가 작업
    cd ios  
    pod install  
* 설치 후 실행  
    cd ..
    yarn android
    yarn ios

* 텍스트만 존재하는 하단 탭 생성하기 위해서 App.js 수정
```javascript
import React from 'react'
import { NavigationContainer} from '@react-navigation/native';
import {createBottmTabNavigator} from '@react-navigation/bottom-tabs'
import {Text} from 'react-native'

// 탭으로 사용할 컴포넌트 생성
function HomeScreen(){
    return <Text>Home</Text>
}

function SearchScreen(){
    return <Text>Search</Text>
}

function NotificationScreen(){
    return <Text>Notification</Text>
}

function MessageScreen(){
    return <Text>Message</Text>
}

// 탭을 생성
const Tab = createBottmTabNavigator();

function App(){
    return (
        <NavigationContainer>
            <Tab.Navigator initailRouteName='Home'>
                <Tab.Screen name='Home' component={HomeScreen}/>
                <Tab.Screen name='Search' component={SearchScreen}/>
                <Tab.Screen name='Notification' component={NotificationScreen}/>
                <Tab.Screen name='Message' component={MessageScreen}/>
            </Tab.Navigator>
        </NavigationContainer>
    );
}

export default App
```  

* 하단 탭에 아이콘을 배치하기 위한 설정  
    - ios/앱이름/info.plit파일에 추가	 
        <key>UIAppFonts</key>  
	    <array><string><MaterailIcons.ttf</string></array>  

    - android/app/build.gradle파일의 하단에 추가  
        project.ext.vectoricons = [ iconFontNames: ['MaterialIcons.ttf']]
        apply from:"../../node_modules/react-native-vector-icons/fonts.gradle"

* 빌드 다시 수행  

* App.js파일의 Tab을 생성하는 부분을 수정해서 아이콘을 출력  
```javascript
import React from 'react'
import { NavigationContainer} from '@react-navigation/native';
import {createBottmTabNavigator} from '@react-navigation/bottom-tabs'
import {Text} from 'react-native'

import Icon from 'react-native-vector-icons/MaterialIcons'

// 탭으로 사용할 컴포넌트 생성
function HomeScreen(){
    return <Text>Home</Text>
}

function SearchScreen(){
    return <Text>Search</Text>
}

function NotificationScreen(){
    return <Text>Notification</Text>
}

function MessageScreen(){
    return <Text>Message</Text>
}

// 탭을 생성
const Tab = createBottmTabNavigator();

function App(){
    return (
        <NavigationContainer>
            <Tab.Navigator initailRouteName='Home'>
                <Tab.Screen name='Home' component={HomeScreen} options={{title:"홈", tabBarIcon: ({color, size})=>(
                    <Icon name='home' color={color} size ={size} />
                )}}/>
                <Tab.Screen name='Search' component={SearchScreen} options={{title:"검색", tabBarIcon: ({color, size})=>(
                    <Icon name='search' color={color} size ={size} />
                )}}/>
                <Tab.Screen name='Notification' component={NotificationScreen} options={{title:"알림", tabBarIcon: ({color, size})=>(
                    <Icon name='notification' color={color} size ={size} />
                )}}/>
                <Tab.Screen name='Message' component={MessageScreen} options={{title:"메세짖", tabBarIcon: ({color, size})=>(
                    <Icon name='message' color={color} size ={size} />
                )}}/>
            </Tab.Navigator>
        </NavigationContainer>
    );
}

export default App
```  

* 하단 탭 커스터마이징을 위해서 App.js파일의 Tab.Navigator을 수정  
```javascript
// ... 생략
<Tab.Navigator initailRouteName='Home' screenOptions={{tabBarActiveTintColor:'#fb8c00', tabBarShowLabel:false}}>
// ... 생략
</Tab.Navigator>
// ... 생략
```  

### 2) HomeScreen(탭바 배치)과 DetailScreen(탭 바 배치하지 않음)을 활용한 탭
* screens 디렉터리에 MainScreen.js파일을 생성하고 작성  
```javascript
import React from 'react'
import {createBottmTabNavigator} from '@react-navigation/bottom-tabs'
import {Text, Button, View} from 'react-native'
import Icon from 'react-native-vector-icons/MaterialIcons'
import { NavigationContainer } from '@react-navigation/native'

// 탭으로 사용할 컴포넌트 생성
function HomeScreen({navigation}){
    return (
        <View>
            <Text>Home</Text>
            <Button title="Detail 열기" onPress={() => navigation.push("Detail", {id:1})}/>
        </View>
    )
}

function SearchScreen(){
    return <Text>Search</Text>
}

function NotificationScreen(){
    return <Text>Notification</Text>
}

function MessageScreen(){
    return <Text>Message</Text>
}

// 탭을 생성
const Tab = createBottmTabNavigator();

function MainScreen(){
    return (
//        <NavigationContainer> 밖에서 만들기 때문에 여기서 있으면 오류남
            <Tab.Navigator initailRouteName='Home' screenOptions={{tabBarActiveTintColor:'#fb8c00', tabBarShowLabel:false}}>
                <Tab.Screen name='Home' component={HomeScreen} options={{title:"홈", tabBarIcon: ({color, size})=>(
                    <Icon name='home' color={color} size ={size} />
                )}}/>
                <Tab.Screen name='Search' component={SearchScreen} options={{title:"검색", tabBarIcon: ({color, size})=>(
                    <Icon name='search' color={color} size ={size} />
                )}}/>
                <Tab.Screen name='Notification' component={NotificationScreen} options={{title:"알림", tabBarIcon: ({color, size})=>(
                    <Icon name='notification' color={color} size ={size} />
                )}}/>
                <Tab.Screen name='Message' component={MessageScreen} options={{title:"메세짖", tabBarIcon: ({color, size})=>(
                    <Icon name='message' color={color} size ={size} />
                )}}/>
            </Tab.Navigator>
//        </NavigationContainer>
    );
}

export default MainScreen
```  

* App.js 수정  
```javascript
import React from 'react'
import { NavigationContainer, StackActions} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack'
import {Text} from 'react-native'

import Icon from 'react-native-vector-icons/MaterialIcons'
import { Colors } from 'react-native/Libraries/NewAppScreen'

// 탭으로 사용할 컴포넌트 생성 - 이제 다른 파일로 분리해놨으므로 import해줌
import MainScreen from './screens/MainScreen';
import DetailScreen from './screens/DetailScreen'
import { createNativeStackNavigator } from '@react-navigation/native-stack';

// 탭을 생성
const Tab = createBottmTabNavigator();

const Stack = createNativeStackNavigator();

function App(){
    return (
        <NavigationContainer>
            <Stack.Navigator>
                <Stack.Screen name="Main" components={MainScreen} options={{headerShown:false}}/>
                <Stack.Screen name="Detail" components={DetailScreen}/>
            </Stack.Navigator>
        </NavigationContainer>
    );
}

export default App
```  

### 3) Material 상단 탭 네비게이터  
* 도큐먼트 : https://reactnavigation.org/docs/material-top-tab-navigator
* 설치 : yarn add @react-navigation/material-top-tabs react-native-tab-view react-native-pager-view

* MainScreen.js 파일을 수정해서 하단 탭을 상단 탭으로 변경  
```javascript
import React from 'react'
import {createBottmTabNavigator} from '@react-navigation/bottom-tabs'
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs'
import {Text, Button, View} from 'react-native'
import Icon from 'react-native-vector-icons/MaterialIcons'
import { NavigationContainer } from '@react-navigation/native'

// 탭으로 사용할 컴포넌트 생성
function HomeScreen({navigation}){
    return (
        <View>
            <Text>Home</Text>
            <Button title="Detail 열기" onPress={() => navigation.push("Detail", {id:1})}/>
        </View>
    )
}

function SearchScreen(){
    return <Text>Search</Text>
}

function NotificationScreen(){
    return <Text>Notification</Text>
}

function MessageScreen(){
    return <Text>Message</Text>
}

// 탭을 생성 - createBottmTabNavigator에서 createMaterialTopTabNavigator으로 변경
const Tab = createMaterialTopTabNavigator();

function MainScreen(){
    return (
//        <NavigationContainer> 밖에서 만들기 때문에 여기서 있으면 오류남
            <Tab.Navigator initailRouteName='Home' screenOptions={{tabBarActiveTintColor:'#fb8c00', tabBarIndicatorStyle:{backgroundColor:'#009688'}}}>
                <Tab.Screen name='Home' component={HomeScreen} options={{title:"홈", tabBarIcon: ({color})=>(
                    <Icon name='home' color={color} size ={24} />
                )}}/>
                <Tab.Screen name='Search' component={SearchScreen} options={{tabBarLabel:"검색", tabBarIcon: ({color})=>(
                    <Icon name='search' color={color} size ={24} />
                )}}/>
                <Tab.Screen name='Notification' component={NotificationScreen} options={{tabBarLabel:"알림", tabBarIcon: ({color})=>(
                    <Icon name='notification' color={color} size ={24} />
                )}}/>
                <Tab.Screen name='Message' component={MessageScreen} options={{tabBarLabel:"메세지", tabBarIcon: ({color})=>(
                    <Icon name='message' color={color} size ={24} />
                )}}/>
            </Tab.Navigator>
//        </NavigationContainer>
    );
}

export default MainScreen
```  

### 4) Material 하단 탭 네비게이터  
* 도큐먼트 : https://callstack.github.io/react-native-paper  
* 설치 : yarn add @react-navigation/material-bottom-tabs react-native-paper  

* MainScreen.js파일을 수정해서 하단 탭을 적용  
```javascript
import React from 'react'
import {createBottmTabNavigator} from '@react-navigation/bottom-tabs'
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs'
import { createMaterialBottomTabNavigator } from '@react-navigation/material-bottom-tabs'
import {Text, Button, View} from 'react-native'
import Icon from 'react-native-vector-icons/MaterialIcons'
import { NavigationContainer } from '@react-navigation/native'

// ... 생략 ... 
// 탭을 생성 
const Tab = createMaterialBottomTabNavigator();

function MainScreen(){
    return (
//  screenOptions={{tabBarActiveTintColor:'#fb8c00', tabBarIndicatorStyle:{backgroundColor:'#009688'}}} 제거
            <Tab.Navigator initailRouteName='Home'>
                <Tab.Screen name='Home' component={HomeScreen} options={{title:"홈", tabBarIcon: ({color})=>(
                    <Icon name='home' color={color} size ={24} />
                ), tabBarColor:'black', tabBarBadge:'new'}}/>
                <Tab.Screen name='Search' component={SearchScreen} options={{tabBarLabel:"검색", tabBarIcon: ({color})=>(
                    <Icon name='search' color={color} size ={24} />
                ), tabBarColor:'green', tabBarBadge:30}}/>
                <Tab.Screen name='Notification' component={NotificationScreen} options={{tabBarLabel:"알림", tabBarIcon: ({color})=>(
                    <Icon name='notification' color={color} size ={24} />
                )}}/>
                <Tab.Screen name='Message' component={MessageScreen} options={{tabBarLabel:"메세지", tabBarIcon: ({color})=>(
                    <Icon name='message' color={color} size ={24} />
                )}}/>
            </Tab.Navigator>
    );
}

export default MainScreen
```  
* 선택한 탭과 헤더의 타이틀을 동기화 시키기위해 App.js파일을 수정  
```javascript
```

### 5) DrawerNavigator
* 도큐먼트 : https://reactnavigation.org/docs/drawer-navigator/  
* 설치 : yarn add @react-navigation/drawer react-native-gesture-handler react-native-reanimated  

* App.js 수정 - 안쓰는 요소들은 지워주기  
```javascript
import React from 'react'
import { NavigationContainer} from '@react-navigation/native';

import { createDrawerNavigator } from '@react-navigation/drawer';
import { SafeAreaView } from 'react-native-safe-area-context';

import {Text, View , Button} from 'react-native'

const Drawer = createDrawerNavigator()

function HomeScreen({navigation}){
    return (
        <View>
            <Text>Home</Text>
            <Button title='Drawer 열기' onPress={()=> navigation.openDrawer()}/>
            <Button title='Setting 열기' onPress={()=> navigation.navigate("Setting")}/>
        </View>
    )
}

function SettingScreen({navigation}){
    return(
        <View>
            <Text>Setting</Text>
            <Button title='뒤로가기' onPress={()=>navigation.goBack()}/>
        </View>
    )
}

function App(){
    return(
        <NavigationContainer>
            <Drawer.Navigator initialRouteName='Home' drawerPosition='left' backBehavior='history' 
            drawerContent={({navigation})=> (
                <SafeAreaView>
                    <Text>A Custom Drawer</Text>
                    <Button onPress={()=>navigation.closeDrawer()} title='Drawer닫기'/>
                </SafeAreaView>
            )}>
                <Drawer.Screen name="Home" component={HomeScreen} 
                options={{title:"홈", headerLeft:()=> <Text>Left</Text>}}/>

                <Drawer.Screen name="Setting" component={SettingScreen}
                options={{title:"설정"}}/>
            </Drawer.Navigator>
        </NavigationContainer>
    )
}

export default App
```  
-> 이전의 App.js는 App_Tab.js로 이름 변경  

* babel.config.js 파일 수정  
```javascript
module.exports = {
  presets: ['module:metro-react-native-babel-preset'],
  plugins:['react-native-reanimated/plugin'],
};
```

* 이전에 실행한 내역을 전부 삭제 - 터미널에서 수행  
npx react-native start --reset-cache  