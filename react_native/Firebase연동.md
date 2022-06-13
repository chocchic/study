# 여러 사용자 사이의 데이터 공유 및 클라이언트 애플리케이션이 삭제되더라도 데이터를 유지  
* BaaS(Backend as a Service)가 필요  
* BaaS를 구현하는 방법  
    - Application Server를 직접 구현해서 사용자의 요청을 직접 처리하는 방식 : 일반적인 방식으로 여러거지 언어로 구현된 프레임워크를 이용해서 구현하는 것이 일반적  
        언어나 프레임워크의 제약은 없는데 현재 국내 중견기업이상에서는 Spring Framework로 구현하는 경우가 많고, 중견 기업이나 스타트업은 node.js로 구현하는 경우가 많다고 알려져 있습니다.  
        블럭체인 쪽에서는 GO로 구현하는 경우도 종종 있습니다.  
        최근에는 Micro Service형태로 많이 구현되며, 기술적으로는 **Web 3.0**의 형태가 주목받고 있습니다.  
        + Web 3.0 : 인터넷 상에서 발생하는 데이터를 사용자가 직접 소유하고 관리하는 개념으로 탈중앙화나 투명성이라는 가치를 강조 - Block Chian, Defi, - 탈중앙 가치를 더한 금융 서비스, NFT, DAO(Decentralized Autonomous Organization - 탈중앙 자율 조직) 등의 기술  
    - Serverless라고 해서 저장소만 구현하고 ApplicationServer는 직접 구현하지 않고 제공해주는 API를 이용하는 방식으로 MVP(Minimum Value Product - 최소 기능 제품)를 만들거나 간단한 앱을 만드는 경우 권장하며 사용자가 늘어나고 데이터의 처리량이 커지면 API 서버를 별도로 구현하는 것이 일반적  

# Firebase를 활용한 사진 공유 앱  
## 1. Firebase
* 구글에서 제공하는 BaaS  
* 회원 인증(직접 인증하는 것과 소셜 로그인 인증 - 네이버나 카카오는 제공하지 않음), 데이터 읽고 쓰기, 이미지 업르도 등의 기능을 제공  
* 기본적으로는 무료지만 데이터 사용량이 늘어나면 유료 결제를 고려 : https://firebase.google.com/pricing  

## 2. 프로젝트 준비  
### 1) 프로젝트 생성 - ChocochipGallery  
npx react-native init ChocoChipGallery  

### 2) 필요한 라이브러리 설치  
* @react-navigation/native, react-native-screens, react-native-safe-area-context, @react-navigation/native-stack, @react-navigation/bottom-tabs, react-native-safe-area-context, react-native-vector-icons

### 3) vector-icon 사용을 위한 설정  
* Mac에서 iOS개발을 같이 하는 경우에는 ios/앱이름/info.plist파일 하단에 추가  
    <key>UIAppFonts</key>
	<array>
		<string>MaterialIcons.ttf</MaterialIcons>
	</array>

* Android의 경우에는 android/app/build.gradle이라는 파일의 최하단에 작성  
project.ext.vectoricons = [ iconFontNames: ['MaterialIcons.ttf'] ]
apply from:"../../node_modules/react-native-vector-icons/fonts.gradle"  

### 4) 빌드하고 실행  

### 5) 화면에 사용될 컴포넌트들을 배치할 screens 디렉터리를 생성  

## 3. Navigation을 이용한 메인 화면을 생성  
### 1) 로그인 화면으로 사용할 컴포넌트를 screens 디렉터리에 SignInScreen.js파일로 생성하고 작성  
```javascript
import React, { useState } from 'react'

import { StyleSheet, Text} from 'react-native'

import { SafeAreaView } from 'react-native-safe-area-context'

function SignInScreen( {navigation, route}){
    return(
        <SafeAreaView style={styles.fullscreen}>
            <Text style={styles.text}>ChocoChip Gallery</Text>
        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
    fullscreen:{
        flex:1,
        alignItems:"center",
        justifyContent:"center"
    },
    text:{
        fontSize:32,
        fontWeight:"bold"
    }
})

export default SignInScreen
```  

### 2) Main Stack으로 사용할 컴포넌트를 RootStack.js파일에 작성  
```javascript
import React, { useState } from 'react'
import { StyleSheet, Text} from 'react-native'
import { SafeAreaView } from 'react-native-safe-area-context'
import {createNativeStackNavigator} from '@react-navigation/native-stack'

import SignInScreen from './SignInScreen'

// 스택 생성
const Stack = createNativeStackNavigator()

function RootStack( {navigation, route}){
    return(
        <Stack.Navigator>
            <Stack.Screen name="SignIn" component={SignInScreen} options={{headerShown:false}} />
        </Stack.Navigator>
    )
}

const styles = StyleSheet.create({
    
})

export default RootStack
```  

### 3) App.js파일에 RootStack을 출력하는 코드를 작성  
```javascript
import React from 'react';
import {NavigatorContainer} from '@react-navigation/native'

import RootStack from './screens/RootStack'

function App() {
    return(
        <NavigatorContainer>
            <RootStack />
        </NavigatorContainer>
    )
};

const styles = StyleSheet.create({

});

export default App;
```  

## 4. Firebase 설정  
### 1) Firebase 사이트에 접속해서 로그인을 한 후 [콘솔로 이동]을 클릭
* https://firebase.google.com  

### 2) 프로젝트 생성 - 프로젝트 1개에 여러 앱 가능  
* 프로젝트 이름을 입력하고 애널리틱스 사용 여부를 설정하는데, 애널리틱스는 앱의 사용자 데이터를 저장하고  분석하기 위한 라이브러리입니다.  

### 3) Android 설정  
* 콘솔 화면에서 안드로이드 아이콘 클릭  
* 앱 등록 : 패키지 이름과 앱 이름, SHA-1 지문 등록  
    패키지 이름은 android/app/build.gradle 파일에서 applicationId를 복사  
    앱 이름은 아무거나 OK  
    SHA-1은 터미널에서 아래 명령어를 입력해서 확인  
        Keytool -J-Duser.language=en -list -v -alias androiddebugkey -keystore ./android/app/debug.keystore  
       
        -> 비밀번호 입력란이 나오면 enter  
        * Windows의 경우  
            C:\programfiles\Java\{자신의 jdk 버전에 맞춰 수정}\bin의 경로로 이동하여 
            keytool -J-Duser.language=en -list -v -alias androiddebugkey -keystore {자신의 keystore파일의 경로에 맞게 수정}/android/app/debug.keystore을 실행  

* 앱 등록 전 나오는 설정 화면 다 따라하기  
    - google-service.json 파일을 다운로드 받아서 ANDROID/APP디렉터리에 저장  

    - android 디렉터리의 build.gradle에 의존성 설정  
    ```
    dependencies {
            classpath("com.android.tools.build:gradle:7.0.4")
            classpath("com.facebook.react:react-native-gradle-plugin")
            classpath("de.undercouch:gradle-download-task:4.1.2")
            // NOTE: Do not place your application dependencies here; they belong
            // in the individual module build.gradle files
            classpath 'com.google.gms:google-services:4.3.10'
    }
    ```  

    - android/app 디렉터리의 build.gradle에 의존성 설정  
        최상단에 추가: apply plugin: 'com.google.gms.google-services'  
        defaultConfig를 찾아서 추가 : multiDexEnabled true  
        dependencies를 찾아서 추가 : implementation platform('com.google.firebase:firebase-bom:30.1.0')  

### 4) Firebase 공식 문서  
* https://rnfirebase.io  

### 5) Firebase 사용을 위한 라이브러리 설치  
yarn add @react-native-firebase/app @react-native-firebase/auth @react-native-firebase/storage @react-native-firebase/firestore  

## 5. Firebase를 이용한 회원 인증  
* Firebase 콘솔 사이트에서 인증을 사용하기 위해서 Authentication을 클릭한 후 시작하기 클릭  
    - 이메일 / 비밀번호를 선택해서 사용설정을 하고 저장을 클릭  

### 1) 회원 인증을 위한 UI를 저장할 components 디렉터리 생성  

### 2) components디렉터리에 Borderedinput.js파일 생성하고 작성  
```javascript
import React from 'react'
import { StyleSheet, TextInput } from 'react-native'

function BorderedInput({hasMarginBottom}){
    return (<TextInput style={[styles.input, hasMarginBottom && styles.margin]} />);
}

const styles = StyleSheet.create({
    input:{
        borderColor:"#bdbdbd",
        borderWidth:1,
        padding:16,
        borderRadius:4,
        height:48,
        backgroundColor:'white'
    },
    margin:{
        marginBottom:16
    }
})

export default BorderedInput
```  

### 3) 사용자 정의 버튼을 위한 CustomButton.js파일을 components 디렉터리에 생성하고 작성  
```javascript
import React from 'react'
import { StyleSheet, View, Pressable, Text, Platform } from 'react-native'

function CustomButton({onPress, title, hasMarginBottom}){
    return(
        <View style={[styles.block, hasMarginBottom && styles.margin]} >
            <Pressable onPress={onPress} style={({pressed})=> [styles.wrapper, Platform.OS === 'ios' && pressed && {opacity : 0.5}]}
                android_ripple={{color:'#ffffff'}}>
                    <Text style={[styles.text]}>{title}</Text>
            </Pressable>

        </View>
    )
}

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
    }
})

export default CustomButton;
```  

### 4) SignInScreen.js파일에서 생성한 컴포넌트를 이용해 화면 디자인  
```javascript
import React, { useState } from 'react'

import { StyleSheet, Text, View } from 'react-native'

import { SafeAreaView } from 'react-native-safe-area-context'

import BorderedInput from '../components/Borderedinput'
import CustomButton from '../components/CustomButton'

function SignInScreen( {navigation, route}){
    return(
        <SafeAreaView style={styles.fullscreen}>
            <Text style={styles.text}>ChocoChip Gallery</Text>
            <View style={styles.form}>
                <BorderedInput hasMarginBottom />
                <BorderedInput />
                <View style={styles.buttons}>
                    <CustomButton title="로그인" hasMarginBottom />
                    <CustomButton title="회원가입" />
                </View>
            </View>
        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
    fullscreen:{
        flex:1,
        alignItems:"center",
        justifyContent:"center"
    },
    text:{
        fontSize:32,
        fontWeight:"bold"
    },
    form:{
        marginTop:64,
        width:'100%',
        paddingHorizontal:26
    },
    buttons:{
        marginTop:64
    }
})

export default SignInScreen
```  

### 5) rest 연산자와 spread연산자로 props 넘겨주기  
* java에서 String[] args와 비슷함  
* javascript의 var obj = {"name":"asdfa", "age":90}; => obj.name, obj.age 처럼 사용 => 줄여쓰기 위해 다른 변수에 할당하여 name=obj.name age = obj.age로 씀  
    파이썬 같은거에서 이렇게 일일히 쓰지 않고 한번에 가능하게 해줌. name,age =obj;

* Borderedinput.js 수정 - function 부분을 수정  
```javascript
function BorderedInput({hasMarginBottom, ...rest}){
    return (<TextInput style={[styles.input, hasMarginBottom && styles.margin]} 
        {...rest} />)
        //onChangeText={onChangeText} value={value} placeholder={placeholder}/>);
}
```  
-> 우리 눈에는 안보이지만 주석의 부분처럼 있어서 알아서 데이터 전달해줌  

* SignInScreen.js파일에서 BorderedInput에 데이터를 넘겨주기  
```javascript
                <BorderedInput hasMarginBottom placeholder="이메일"/>
                <BorderedInput placeholder="비밀번호"/>
```  

### 6) CustomButton에 Secondary Button Style 지정  
* CustomButton.js 수정
````javascript
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
```  