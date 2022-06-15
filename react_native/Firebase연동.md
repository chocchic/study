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
```javascript
import React from 'react'
import { StyleSheet, View, Pressable, Text, Platform } from 'react-native'

function CustomButton({onPress, title, hasMarginBottom, theme}){
    const isPrimary = theme === 'primary';

    return(
        <View style={[styles.block, hasMarginBottom && styles.margin]} >
            <Pressable onPress={onPress} style={({pressed})=> [styles.wrapper, 
                    isPrimary && styles.primaryWrapper,
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

* SignInScreen.js파일의 회원 가입버튼을 수정  
```javascript
<CustomButton title="회원가입" theme="secondary"/>
```  

### 7) 회원가입화면 만들기 - 하나의 화면을 동적으로 수정  
* SignInScreen.js 수정  
```javascript
import React, { useState } from 'react'

import { StyleSheet, Text, View } from 'react-native'

import { SafeAreaView } from 'react-native-safe-area-context'

import BorderedInput from '../components/Borderedinput'
import CustomButton from '../components/CustomButton'

function SignInScreen( {navigation, route}){
    // 로그인인지 회원 가입인지 구분하기 위한 변수 생성
    const {isSignUp} = route.params ?? {};
    return(
        <SafeAreaView style={styles.fullscreen}>
            <Text style={styles.text}>ChocoChip Gallery</Text>
            <View style={styles.form}>
                <BorderedInput hasMarginBottom placeholder="이메일"/>
                <BorderedInput placeholder="비밀번호" hasMarginBottom={isSignUp}/>
                {isSignUp && <BorderedInput placeholder="비밀번호 확인" />}
                <View style={styles.buttons}>
                    {isSignUp ? (
                        <>
                            <CustomButton title="회원가입" hasMarginBottom />
                            <CustomButton title="로그인" theme="secondary" onPress={()=>{
                                navigation.goBack();
                            }}/>
                        </>
                    ) : (
                        <>
                            <CustomButton title="로그인" hasMarginBottom />
                            <CustomButton title="회원가입" theme="secondary" onPress={()=>{
                                navigation.push("SignIn", {isSignUp:true});
                            }}/>
                        </>
                    )}
                    
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

### 8) 입력받은 내용 관리 - 입력받은 내용을 변수에 지정  
* SignInScreen.js을 수정  
```javascript
import React, { useState } from 'react'

import { StyleSheet, Text, View, Keyboard, KeyboardAvoidingView, Platform } from 'react-native'

import { SafeAreaView } from 'react-native-safe-area-context'

import BorderedInput from '../components/Borderedinput'
import CustomButton from '../components/CustomButton'

function SignInScreen( {navigation, route}){
    // 로그인인지 회원 가입인지 구분하기 위한 변수 생성
    const {isSignUp} = route.params ?? {};

    // 속성과 속성을 수정하는 함수 그리고 기본값을 설정
    const [form, setForm] = useState({
        email:'',
        password:'',
        confirmPassword:''
    });

    // form에 데이터를 설정하는 함수 - BorderedInput에 연결
    const createChangeTextHandler = name => value => {
        // name하고 value가 들어오면
        // form 속성 안에서 name에 value를 설정
        setForm({...form, [name]:value});
    }

    // 버튼을 눌렀을 때 호출될 함수
    const onSubmit = () =>{
        Keyboard.dismiss();
        console.log(form);
    }

    return(
        <KeyboardAvoidingView style={styles.keyboardAvoidingView} 
        behavior={Platform.select({iod:'padding'})}>
        <SafeAreaView style={styles.fullscreen}>
            <Text style={styles.text}>ChocoChip Gallery</Text>
            <View style={styles.form}>
                <BorderedInput hasMarginBottom placeholder="이메일"
                value={form.email} onChangeText={createChangeTextHandler('email')}/>
                <BorderedInput placeholder="비밀번호" hasMarginBottom={isSignUp}
                value={form.password} onChangeText={createChangeTextHandler('password')}/>
                {isSignUp && <BorderedInput placeholder="비밀번호 확인" 
                value={form.confirmPassword} onChangeText={createChangeTextHandler('confirmPassword')} />}
                <View style={styles.buttons}>
                    {isSignUp ? (
                        <>
                            <CustomButton title="회원가입" hasMarginBottom />
                            <CustomButton title="로그인" theme="secondary" onPress={()=>{
                                navigation.goBack();
                            }}/>
                        </>
                    ) : (
                        <>
                            <CustomButton title="로그인" hasMarginBottom />
                            <CustomButton title="회원가입" theme="secondary" onPress={()=>{
                                navigation.push("SignIn", {isSignUp:true});
                            }}/>
                        </>
                    )}
                    
                </View>
            </View>
        </SafeAreaView>
        </KeyboardAvoidingView>
    )
}

const styles = StyleSheet.create({
    keyboardAvoidingView:{flex:1},
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

### 9) 키보드 속성 수정을 위해서 SigleInScreen.js 수정  
```javascript
import React, { useState } from 'react'

import { StyleSheet, Text, View, Keyboard, KeyboardAvoidingView, Platform } from 'react-native'

import { SafeAreaView } from 'react-native-safe-area-context'

import BorderedInput from '../components/Borderedinput'
import CustomButton from '../components/CustomButton'

function SignInScreen( {navigation, route}){
    // 로그인인지 회원 가입인지 구분하기 위한 변수 생성
    const {isSignUp} = route.params ?? {};

    // 속성과 속성을 수정하는 함수 그리고 기본값을 설정
    const [form, setForm] = useState({
        email:'',
        password:'',
        confirmPassword:''
    });

    // form에 데이터를 설정하는 함수 - BorderedInput에 연결
    const createChangeTextHandler = name => value => {
        // name하고 value가 들어오면
        // form 속성 안에서 name에 value를 설정
        setForm({...form, [name]:value});
    }

    // 버튼을 눌렀을 때 호출될 함수
    const onSubmit = () =>{
        Keyboard.dismiss();
        console.log(form);
    }

    return(
        <KeyboardAvoidingView style={styles.keyboardAvoidingView} 
        behavior={Platform.select({iod:'padding'})}>
        <SafeAreaView style={styles.fullscreen}>
            <Text style={styles.text}>ChocoChip Gallery</Text>
            <View style={styles.form}>
                <BorderedInput hasMarginBottom placeholder="이메일"
                value={form.email} onChangeText={createChangeTextHandler('email')}
                autoCapitalize="none" autoCorrect={false} autoCompleteType="email" keyboardType="email-address"
                />
                <BorderedInput placeholder="비밀번호" hasMarginBottom={isSignUp}
                value={form.password} onChangeText={createChangeTextHandler('password')}
                secureTextEntry />
                {isSignUp && <BorderedInput placeholder="비밀번호 확인" 
                value={form.confirmPassword} onChangeText={createChangeTextHandler('confirmPassword')} 
                secureTextEntry />}
                <View style={styles.buttons}>
                    {isSignUp ? (
                        <>
                            <CustomButton title="회원가입" hasMarginBottom />
                            <CustomButton title="로그인" theme="secondary" onPress={()=>{
                                navigation.goBack();
                            }}/>
                        </>
                    ) : (
                        <>
                            <CustomButton title="로그인" hasMarginBottom />
                            <CustomButton title="회원가입" theme="secondary" onPress={()=>{
                                navigation.push("SignIn", {isSignUp:true});
                            }}/>
                        </>
                    )}
                    
                </View>
            </View>
        </SafeAreaView>
        </KeyboardAvoidingView>
    )
}

const styles = StyleSheet.create({
    keyboardAvoidingView:{flex:1},
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

### 10) Input에서 return 키에 대한 처리  
* 다른 곳으로 포커스를 옮기는 작업을 수행  

* BorderedInput을 다른 곳에서 접근해서 사용할 수 있도록 BorderedInput.js를 수정
```javascript
import React from 'react';
import { StyleSheet, TextInput } from 'react-native';

function BorderedInput({hasMarginBottom, ...rest}, ref){
    return (<TextInput style={[styles.input, hasMarginBottom && StyleSheet.margin]} 
    
    ref = {ref}

    {...rest}/>);
}

const styles = StyleSheet.create({
    input:{
        borderColor:"#bdbdbd",
        borderWidth:1,
        paddingHorizontal:16,
        borderRadius:4,
        height:48,
        backgroundColor:'white'
    },
    margin:{
        marginBottom:16
    }
})

//ref에 BorderedInput을 대입해서 넘겨줍니다.
export default React.forwardRef(BorderedInput);
```  

* SignInScreen.js 파일을 수정해서 Return 키에 대한 처리를 수행  
```javascript
import React, {useRef, useState } from 'react';
import {
    StyleSheet,
    Text,
    View,
    Keyboard,
    KeyboardAvoidingView,
    Platform
} from 'react-native';
import {SafeAreaView} from 'react-native-safe-area-context'


import BorderedInput from '../components/BorderdedInput';
import CustomButton from '../components/CustomButton';

function SignInScreen({ navigation, route }) {
    //로그인인지 회원 가입인지 구분하기 위한 변수를 생성
    const {isSignUp} = route.params ?? {};

    //속성 과 속성을 수정하는 함수 그리고 기본값을 설정
    const [form, setForm] = useState({
      email:'',
      password:'',
      confirmPassword:''
    });

    //form 에 데이터를 설정하는 함수 - BorderedInput에 연결
    const createChangeTextHandler = name => value => {
      //form 속성 안에서 name에 value를 설정
      setForm({...form, [name]:value});
    };

    //버튼을 눌렀을 때 호출될 함수
    const onSubmit = () => {
      Keyboard.dismiss();
      console.log(form);
    }

    //password 와 confirmPassword에 대한 참조를 가져온 것입니다.
    const passwordRef = useRef();
    const confirmPasswordRef = useRef();


    return (
        <KeyboardAvoidingView style={styles.KeyboardAvoidingView} 
        behavior={Platform.select({ios:'padding'})}>

        <SafeAreaView style={styles.fullscreen}>
            <Text style={styles.text}>PublicGallery</Text>
            <View style={styles.form}>
              <BorderedInput hasMarginBottom placeholder="이메일"
              value={form.email} onChangeText={createChangeTextHandler('email')}
              autoCapitalize="none" autoCorrect={false} autoCompleteType="email"
              keyboardType="email-address"
              returnKeyType="next" onSubmitEditing = {() => passwordRef.current.focus()} />

              <BorderedInput placeholder="비밀번호" hasMarginBottom={isSignUp}
              value={form.password} onChangeText={createChangeTextHandler('password')}
              secureTextEntry
              ref={passwordRef}
              returnKeyType={isSignUp ? 'next' : 'done'} 
              onSubmitEditing = {() => {
              if(isSignUp){
                confirmPasswordRef.current.focus();
              }else{
                onSubmit();
              }
            }
          }/>

              {isSignUp && <BorderedInput placeholder="비밀번호 확인" 
              value={form.confirmPassword} onChangeText={createChangeTextHandler('confirmPassword')}
              secureTextEntry
              ref={confirmPasswordRef}
              returnKeyType="done" 
              onSubmitEditing = {onSubmit}/>}

              <View style={styles.buttons}>
                {isSignUp ? (
                    <>
                      <CustomButton title="회원가입" hasMarginBottom onPress={onSubmit}/>
                      <CustomButton title="로그인" theme="secondary" 
                        onPress={()=>{
                          navigation.goBack();
                      }}/>
                    </>
                ) : (
                  <>
                    <CustomButton title="로그인" hasMarginBottom onPress={onSubmit}/>
                    <CustomButton title="회원가입" theme="secondary" 
                        onPress={()=>{
                          navigation.push("SignIn", {isSignUp:true});
                      }}/>
                  </>
                )}
              </View>
            </View>
        </SafeAreaView>
        </KeyboardAvoidingView>
    );
}

const styles = StyleSheet.create({
  KeyboardAvoidingView:{
    flex:1
  },
    fullscreen: {
      flex: 1,
      alignItems: 'center',
      justifyContent: 'center',
    },
    text: {
      fontSize: 32,
      fontWeight: 'bold',
    },
    form:{
      marginTop:64,
      width:'100%',
      paddingHorizontal:16
    },
    buttons:{
      marginTop:64
    }
  });
  
  export default SignInScreen;
```

+) 데이터베이스 작업시 하나의 테이블에 열의 개수가 너무 많아지거나 행의 개수가 너무 많아지면 테이블을 수평이나 수직으로 분할하는 것이 좋음

MongoDB에서는 이를 샤딩과 클러스터링이라고 합니다.  

DB에서는 일반적으로 자주 사용하는 것과 그렇지 않은 것으로 분리하는 경우가 많음.  
아이디, 비밀번호, 프로필사진, 전화번호, 주소...
아이디, 비밀번호, 프로필 사진 + 아이디, 전화번호, 주소

프로그래밍에서 코드도 너무 복잡해지거나 라인 수가 많아지면 가독성이 떨어지게 되고 가독성이 떨어지면 유지보수가 어려워집니다.  
이런 경우에는 코드를 분할해주는 것이 좋습니다. -> 기능 단위로 분할    
객체 지향에서는 private메서드를 이용해서 분할하거나 상속이나 의존하는 클래스를 만들어서 해결합니다.  

### 11) SignInScreen.js파일의 내용을 분할
* 입력하는 컴포넌트들이 배치되는 부분을 components/SignForm.js로 작성  
```javascript
import React, {useRef} from 'react'

import BorderedInput from '../components/Borderedinput'

function SignForm({isSignUp, onSubmit, form, createChangeTextHandler}){
    const passwordRef = useRef();
    const confirmPasswordRef = useRef();

    return(
        <>
        <BorderedInput hasMarginBottom placeholder="이메일"
            value={form.email} onChangeText={createChangeTextHandler('email')}
            autoCapitalize="none" autoCorrect={false} autoCompleteType="email" keyboardType="email-address"
            returnKeyType="next" onSubmitEditing= {()=> passwordRef.current.focus()} // returnKeyType은 안드로이드보단 ios에서 의미가 있음
            />

            <BorderedInput placeholder="비밀번호" hasMarginBottom={isSignUp}
            value={form.password} onChangeText={createChangeTextHandler('password')}
            secureTextEntry ref={passwordRef} 
            returnKeyType={isSignUp ? 'next' : 'done'} onSubmitEditing = {() => {
                if(isSignUp) {confirmPasswordRef.current.focus()} 
                else {onSubmit();}
            }}/>

            {isSignUp && <BorderedInput placeholder="비밀번호 확인" 
            value={form.confirmPassword} onChangeText={createChangeTextHandler('confirmPassword')} 
            secureTextEntry ref={confirmPasswordRef} returnKeyType="done" onSubmitEditing={onSubmit}/>}
            
        </>
    )
}

export default SignForm
```  

* 버튼들이 배치될 컴포넌트를 components/SignButton.sj 파일에 작성  
```javascript
import React from 'react'
import { ActivityIndicator, StyleSheet, View } from 'react-native'
import CustomButton from './CustomButton'
import { useNavigation } from '@react-navigation/native'

function SignButton({isSignUp, onSubmit}){
    const navigation = useNavigation()
    const primaryTitle = isSignUp ? '회원가입' : '로그인'
    const secondaryTitle = isSignUp ? '로그인' : '회원가입'

    const onSecondaryButtonPress =() =>{
        if(isSignUp){
            navigation.goBack();
        }else{
            navigation.push('SignIn', {isSignUp:true})
        }
    }

    return (
        <View style={styles.buttons}>
            <CustomButton title={primaryTitle} hasMarginBottom onPress={onSubmit}/>
            <CustomButton title={secondaryTitle} hasMarginBottom onPress={onSecondaryButtonPress}/>
        </View>
    )
}

const styles = StyleSheet.create({
    buttons:{
        marginTop:64
    }
})

export default SignButton;
```  

* SignInScreen.js 수정  
```javascript
import React, { useState, useRef } from 'react'

import { StyleSheet, Text, View, Keyboard, KeyboardAvoidingView, Platform } from 'react-native'

import { SafeAreaView } from 'react-native-safe-area-context'

import SignButton from '../components/SignButton'

function SignInScreen( {navigation, route}){
    // 로그인인지 회원 가입인지 구분하기 위한 변수 생성
    const {isSignUp} = route.params ?? {};

    // 속성과 속성을 수정하는 함수 그리고 기본값을 설정
    const [form, setForm] = useState({
        email:'',
        password:'',
        confirmPassword:''
    });

    // form에 데이터를 설정하는 함수 - BorderedInput에 연결
    const createChangeTextHandler = name => value => {
        // name하고 value가 들어오면
        // form 속성 안에서 name에 value를 설정
        setForm({...form, [name]:value});
    }

    // 버튼을 눌렀을 때 호출될 함수
    const onSubmit = () =>{
        Keyboard.dismiss();
        console.log(form);
    }

    const passwordRef = useRef();
    const confirmPasswordRef = useRef();

    return(
        <KeyboardAvoidingView style={styles.keyboardAvoidingView} 
        behavior={Platform.select({iod:'padding'})}>
        <SafeAreaView style={styles.fullscreen}>
            <Text style={styles.text}>ChocoChip Gallery</Text>
            <View style={styles.form}>
                <View style={styles.form} isSignUp={isSignUp} onSubmit={onSubmit} createChangeTextHandler={createChangeTextHandler}>
                    <SignButton isSignUp={isSignUp} onSubmit={onSubmit}/>
                </View>
            </View>
        </SafeAreaView>
        </KeyboardAvoidingView>
    )
}

const styles = StyleSheet.create({
    keyboardAvoidingView:{flex:1},
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

### 12) Firebase를 인증처리  
* React Native를 이용한 Firebase 인증 처리 도큐먼트 https://rnfirebase.io/auth/usage  

* 처리를 위한 로직 파일이 저장될 lib디렉터리를 생성  

* 인증처리를 위한 함수들을 lib/auth.js파일에 작성 : https://rnfirebase.io/auth/usage의 내용을 가지고 작성  
```javascript
import auth from '@react-native-firebase/auth'

// 로그인 처리
export function signIn({email, password}){
    return auth().signInWithEmailAndPassword(email, password)
}

// 회원 가입
export function signUp({email, password}){
    return auth().createUserWithEmailAndPassword(email, password);
}

// 앱을 동작시킬 때, 로그인 상태가 변경될 때 호출되는 함수  
export function subscribeAuth(callback){
    return auth().onAuthStateChanged(callback);
}

// 로그아웃 처리
export function signOut(){
    return auth().signOut();
}
```  

* SignButton.js파일을 수정  
```javascript
import React from 'react'
import { ActivityIndicator, StyleSheet, View } from 'react-native'
import CustomButton from './CustomButton'
import { useNavigation } from '@react-navigation/native'

function SignButton({isSignUp, onSubmit, loading}){
    const navigation = useNavigation()
    const primaryTitle = isSignUp ? '회원가입' : '로그인'
    const secondaryTitle = isSignUp ? '로그인' : '회원가입'

    const onSecondaryButtonPress =() =>{
        if(isSignUp){
            navigation.goBack();
        }else{
            navigation.push('SignIn', {isSignUp:true})
        }
    }
    if(loading){
        return (
            <View style={styles.spinnerWrapper}>
                <ActivityIndicator size={32} color="#6200ee"/>
            </View>
        )
    }
    return (
        <View style={styles.buttons}>
            <CustomButton title={primaryTitle} hasMarginBottom onPress={onSubmit}/>
            <CustomButton title={secondaryTitle} hasMarginBottom onPress={onSecondaryButtonPress}/>
        </View>
    )
}

const styles = StyleSheet.create({
    buttons:{
        marginTop:64
    },
    spinnerWrapper:{
        marginTop:64,
        height:104,
        justifyContent:'center',
        alignItems:'center'
    }
})

export default SignButton;
```  

* SignInScreen.js파일을 수정  
```javascript
import React, { useState, useRef } from 'react'

import { StyleSheet, Text, View, Keyboard, KeyboardAvoidingView, Platform, Alert } from 'react-native'

import { SafeAreaView } from 'react-native-safe-area-context'

import SignButton from '../components/SignButton'

import {signIn, signUp} from '../lib/auth'

function SignInScreen( {navigation, route}){
    // 로그인인지 회원 가입인지 구분하기 위한 변수 생성
    const {isSignUp} = route.params ?? {};

    // 속성과 속성을 수정하는 함수 그리고 기본값을 설정
    const [form, setForm] = useState({
        email:'',
        password:'',
        confirmPassword:''
    });

    const [loading, setLoading] = useState();

    // form에 데이터를 설정하는 함수 - BorderedInput에 연결
    const createChangeTextHandler = name => value => {
        // name하고 value가 들어오면
        // form 속성 안에서 name에 value를 설정
        setForm({...form, [name]:value});
    }

    // 버튼을 눌렀을 때 호출될 함수
    const onSubmit = async() =>{
        Keyboard.dismiss();
        console.log(form);

        // 입력한 내용 가져오기
        const {email, password} = form;
        const info = {email, password};
        // 스피너를 화면에 출력
        setLoading(true);

        try{
            const {user} = isSignUp ? await signUp(info) : await signIn(info)
            console.log(user)
        }catch(e){
            Alert.alert("실패");
        }
    }

    const passwordRef = useRef();
    const confirmPasswordRef = useRef();

    return(
        <KeyboardAvoidingView style={styles.keyboardAvoidingView} 
        behavior={Platform.select({iod:'padding'})}>
        <SafeAreaView style={styles.fullscreen}>
            <Text style={styles.text}>ChocoChip Gallery</Text>
            <View style={styles.form}>
                <View style={styles.form} isSignUp={isSignUp} onSubmit={onSubmit} createChangeTextHandler={createChangeTextHandler}>
                    <SignButton isSignUp={isSignUp} onSubmit={onSubmit} loading={loading}/>
                </View>
            </View>
        </SafeAreaView>
        </KeyboardAvoidingView>
    )
}

const styles = StyleSheet.create({
    keyboardAvoidingView:{flex:1},
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
-> 비밀번호는 기본 6자 이상  

* 오류 처리  
https://firebase.google.com/docs/reference/js/v8/firebase.auth.Auth  
https://firebase.google.com/docs/reference/js/v8/firebase.auth.Error   

* 회원가입 오류처리를 위해 onSubmit함수를 수정  
```javascript
import React, { useState, useRef } from 'react'

import { StyleSheet, Text, View, Keyboard, KeyboardAvoidingView, Platform, Alert } from 'react-native'

import { SafeAreaView } from 'react-native-safe-area-context'

import SignButton from '../components/SignButton'

import {signIn, signUp} from '../lib/auth'

function SignInScreen( {navigation, route}){
    // 로그인인지 회원 가입인지 구분하기 위한 변수 생성
    const {isSignUp} = route.params ?? {};

    // 속성과 속성을 수정하는 함수 그리고 기본값을 설정
    const [form, setForm] = useState({
        email:'',
        password:'',
        confirmPassword:''
    });

    const [loading, setLoading] = useState();

    // form에 데이터를 설정하는 함수 - BorderedInput에 연결
    const createChangeTextHandler = name => value => {
        // name하고 value가 들어오면
        // form 속성 안에서 name에 value를 설정
        setForm({...form, [name]:value});
    }

    // 버튼을 눌렀을 때 호출될 함수
    // async()는 이 함수를 비동기적으로 수행
    const onSubmit = async() =>{
        Keyboard.dismiss();
        console.log(form);

        // 입력한 내용 가져오기
        const {email, password} = form;
        const info = {email, password};
        // 스피너를 화면에 출력
        setLoading(true);

        // await가 붙으면 이 동작이 완료될 때까지 대기
        try{
            const {user} = isSignUp ? await signUp(info) : await signIn(info)
            console.log(user)
        }catch(e){
            console.log(e);

            const messages = {
                'auth/email-already-in-use' : '이미 가입된 이메일입니다.',
                'auth/wrong-password' : '잘못된 비밀번호입니다.',
                'auth/user-not-found' : '존재하지 않는 계정입니다.',
                'auth/invalid-email' : '유효하지 않은 이메일입니다.'
            }

            const msg = messages[e.code] || `${isSignUp ? '가입' : '로그인'} 실패`;
            Alert.alert('실패', msg);
        }
    }

    const passwordRef = useRef();
    const confirmPasswordRef = useRef();

    return(
        <KeyboardAvoidingView style={styles.keyboardAvoidingView} 
        behavior={Platform.select({iod:'padding'})}>
        <SafeAreaView style={styles.fullscreen}>
            <Text style={styles.text}>ChocoChip Gallery</Text>
            <View style={styles.form}>
                <View style={styles.form} isSignUp={isSignUp} onSubmit={onSubmit} createChangeTextHandler={createChangeTextHandler}>
                    <SignButton isSignUp={isSignUp} onSubmit={onSubmit} loading={loading}/>
                </View>
            </View>
        </SafeAreaView>
        </KeyboardAvoidingView>
    )
}

const styles = StyleSheet.create({
    keyboardAvoidingView:{flex:1},
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

## 6. Firebase를 이용한 데이터 저장  
### 1) Firebase 콘솔 페이지에서 Firebase Database를 선택하고 데이터베이스 만들기를 클릭  

### 2) lib/users.js을 생성하고 작성
```javascript
import firestore from '@react-native-firebase/firestore'

export const usersCollection = firestore().collection('users');

// 데이터 저장
export function createUser({id, displayName, photoURL}){
    return usersCollection.doc(id).set({id, displayName, photoURL})
}

// 데이터 1개 가져오기
export async function getUser(id){
    const doc = await usersCollection.doc(id).get();
    return doc.data();
}
```

### 3) 회원정보를 출력할 screens/WelcomeScreen.js를 생성하고 작성  
```javascript
import React from 'react'
import { KeyboardAvoidingView, Platform, StyleSheet, Text } from 'react-native'
import { SafeAreaView } from 'react-native-safe-area-context'

function WelcomeScreen(){
    return (
        <KeyboardAvoidingView style = {styles.KeyboardAvoidingView}
        behavior ={Platform.select({ios:'padding'})}>
            <SafeAreaView style={styles.block}>
                <Text style={styles.title}>환영합니다</Text>
                <Text style={styles.description}>프로필을 설정하세요</Text>
            </SafeAreaView>
        </KeyboardAvoidingView>
    )
}

const styles = StyleSheet.create({
    KeyboardAvoidingView:{
        flex:1
    },
    block:{
        flex:1,
        alignItems:'center',
        justifyContent:'center'
    },
    description:{
        marginTop: 16,
        fontSize: 21,
        color: '#757575'
    }
})

export default WelcomeScreen
```  

### 4) WelcomeScreen을 RootStack에 등록  
```javascript
import React, { useState } from 'react'
import { StyleSheet, Text} from 'react-native'
import { SafeAreaView } from 'react-native-safe-area-context'
import {createNativeStackNavigator} from '@react-navigation/native-stack'

import SignInScreen from './SignInScreen'
import WelcomeScreen from './WelcomeScreen'

const Stack = createNativeStackNavigator()

function RootStack( {navigation, route}){
    return(
        <Stack.Navigator>
            <Stack.Screen name="SignIn" component={SignInScreen} options={{headerShown:false}} />
            <Stack.Screen name="Welcome" component={WelcomeScreen} options={{headerShown:false}} />
        </Stack.Navigator>
    )
}

const styles = StyleSheet.create({
    
})

export default RootStack
```  

### 5) 사용자 프로필 사진 과 닉네임을 입력할 수 있는 컴포넌트를 components 디렉토리에 SetupProfile.js 파일로 생성  
```javascript
import { useNavigation, useRoute } from "@react-navigation/native";
import React, {useState} from 'react';
import {StyleSheet, View} from 'react-native';
import {signOut} from '../lib/auth';
import {createUser} from '../lib/users';

import BorderedInput from "./BorderedInput";
import CustomButton from "./CustomButton";

function SetupProfile(){
    //닉네임 변수
    const [displayName, setDisplayName] = useState("");
    //화면 전환을 수행하는 navigation 찾아오기
    const navigation = useNavigation();

    //파라미터 생성
    const {params} = useRoute();

    const {uid} = params || {};

    //버튼을 눌렀을 때 Firebase 의 Storage에 저장
    const onSubmit = async () => {
        createUser({
            id:uid,
            displayName,
            photoURL:null
        })
    }

    //취소를 누른 경우
    const onCancel = () => {
        //로그 아웃
        signOut();
        //이전 화면으로 돌아가기
        navigation.goBack();
    }

    return (
        <View style={styles.block}>
            <View style={styles.circle} />
            <View style={styles.form} >
                <BorderedInput
                    placeholder="닉네임"
                    value={displayName}
                    onChangeText={setDisplayName}
                    onSubmitEditing={onSubmit}
                    returnKeyType="next"/>
                <CustomButton title="다음" onPress={onSubmit} hasMarginBottom />
                <CustomButton title="취소" onPress={onCancel} theme="secondary" />
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    block:{
        alignItems:'center',
        marginTop: 24,
        paddingHorizontal: 16,
        width:'100%'
    },
    circle:{
        backgroundColor:'#cdcdcd',
        borderRadius: 64,
        width:128,
        height:128
    },
    form:{
        marginTop:16,
        width:'100%'
    },
    buttons:{
        marginTop:48
    }
})

export default SetupProfile;
```  

### 6)SetupProfile 을 WelcomeScreen 에 등록  
```javascript
import React from 'react'
import {KeyboardAvoidingView, Platform, StyleSheet, Text} from 'react-native';
import {SafeAreaView} from 'react-native-safe-area-context';
import SetupProfile from '../components/SetupProfile';

function WelcomScreen(){
    return (
        <KeyboardAvoidingView style={styles.KeyboardAvoidingView}
        behavior={Platform.select({ios:'padding'})}>
            <SafeAreaView style={styles.block}>
                <Text style={styles.title}>환영합니다.</Text>
                <Text style={styles.description}>프로필을 설정하세요</Text>
                <SetupProfile />
            </SafeAreaView>
        </KeyboardAvoidingView>
    )
}

const styles = StyleSheet.create({
    KeyboardAvoidingView:{
        flex:1
    },
    block:{
        flex:1,
        alignItems:'center',
        justifyContent:'center'
    },
    description:{
        marginTop: 16,
        fontSize:21,
        color:'#757575'
    }
})

export default WelcomScreen;
```  

### 7)SignInScreen.js 파일을 수정해서 회원가입이나 로그인에 성공했을 때 user.uid를 이용해서 데이터가 존재하지 않으면 Welcome 화면을 출력  
```javascript
import React, {useRef, useState } from 'react';
import {
    Alert,
    StyleSheet,
    Text,
    View,
    Keyboard,
    KeyboardAvoidingView,
    Platform
} from 'react-native';
import {SafeAreaView} from 'react-native-safe-area-context'


import SignForm from '../components/SignForm';
import SignButtons from '../components/SignButtons';

import {signIn, signUp} from '../lib/auth'
import {getUser} from '../lib/users'

function SignInScreen({ navigation, route }) {
    //로그인인지 회원 가입인지 구분하기 위한 변수를 생성
    const {isSignUp} = route.params ?? {};

    //속성 과 속성을 수정하는 함수 그리고 기본값을 설정
    const [form, setForm] = useState({
      email:'',
      password:'',
      confirmPassword:''
    });

    const [loading, setLoading] = useState();

    //form 에 데이터를 설정하는 함수 - BorderedInput에 연결
    const createChangeTextHandler = name => value => {
      //form 속성 안에서 name에 value를 설정
      setForm({...form, [name]:value});
    };

    //버튼을 눌렀을 때 호출될 함수
    const onSubmit = async() => {
      Keyboard.dismiss();
      console.log(form);

      //입력한 내용 가져오기
      const {email, password} = form;
      const info = {email, password};
      //스피너를 화면에 출력
      setLoading(true);

      try{
        //isSignUp 에 따라 파이어베이스에 저장하던가 로그인을 수행
        const {user} = isSignUp? await signUp(info) : await signIn(info);
        //작업이 성공적으로 완료되면 uid를 이용해서 user의 정보를 가져오기
        const profile = await getUser(user.uid);
        if(!profile){
          navigation.navigate('Welcome', {uid:user.uid});
        }else{
          
        }
        console.log(user);
      }catch(e){
        console.log(e);

        const messages = {
          'auth/email-already-in-use':'이미 가입된 이메일입니다.',
          'auth/wrong-password': '잘못된 비밀번호입니다.',
          'auth/user-not-found': '존재하지 않는 계정입니다.',
          'auth/invalid-email': '유효하지 않은 이메일 입니다.'
        };

        const msg = messages[e.code] || `${isSignUp ? '가입' : '로그인'} 실패`;
        Alert.alert('실패', msg);
      }

    }

    //password 와 confirmPassword에 대한 참조를 가져온 것입니다.
    const passwordRef = useRef();
    const confirmPasswordRef = useRef();

    return (
        <KeyboardAvoidingView style={styles.KeyboardAvoidingView} 
        behavior={Platform.select({ios:'padding'})}>

        <SafeAreaView style={styles.fullscreen}>
            <Text style={styles.text}>PublicGallery</Text>
            <View style={styles.form}>
              <SignForm style={styles.form}
                isSignUp={isSignUp}
                onSubmit = {onSubmit}
                form={form}
                createChangeTextHandler = {createChangeTextHandler} />

              <SignButtons isSignUp={isSignUp} onSubmit={onSubmit} loading={loading}/>
            </View>  
        </SafeAreaView>
        </KeyboardAvoidingView>
    );
}

const styles = StyleSheet.create({
  KeyboardAvoidingView:{
    flex:1
  },
    fullscreen: {
      flex: 1,
      alignItems: 'center',
      justifyContent: 'center',
    },
    text: {
      fontSize: 32,
      fontWeight: 'bold',
    },
    form:{
      marginTop:64,
      width:'100%',
      paddingHorizontal:16
    },
    buttons:{
      marginTop:64
    }
  });
  
  export default SignInScreen;
```  

### 8)User의 상태 정보를 위한 함수를 소유하는 Context 파일을 contexts 디렉토리에 UserContext.js 파일로 저장  
```javascript
import React, { useContext, createContext, useState } from 'react';

const UserContext = createContext(null);

export function UserContextProvider({ children }) {
    const [user, setUser] = useState(null);
    console.log({ user });
    return (
        <UserContext.Provider
            children={children}
            value={{
                user,
                setUser,
            }}
        />
    );
}

export function useUserContext() {
    const userContext = useContext(UserContext);
    if (!userContext) {
        throw new Error('UserContext.Provider is not found');
    }
    return userContext;
}
```  

### 9)App.js 의 모든 영역을 이전에 생성한 UserContextProvider로 감싸기  
```javascript
import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import RootStack from './screens/RootStack';

import {UserContextProvider} from './contexts/UserContext'

function App(){
  return(
    <UserContextProvider>
    <NavigationContainer>
      <RootStack />
    </NavigationContainer>
    </UserContextProvider>
  )
};

export default App;
```  

### 10)User 정보를 가져와서 앱에 등록하는 시점  

* Welcome 화면에서 유저 정보를 등록하는 경우
* 프로필이 등록된 계정으로 로그인 한 경우
* 앱을 새로 시작해서 로그인 상태가 유지되는 경우

### 11)User 정보를 등록하기 위해서 SingInScreen.js 파일을 수정  
```javascript
import React, {useRef, useState } from 'react';
import {
    Alert,
    StyleSheet,
    Text,
    View,
    Keyboard,
    KeyboardAvoidingView,
    Platform
} from 'react-native';
import {SafeAreaView} from 'react-native-safe-area-context'


import SignForm from '../components/SignForm';
import SignButtons from '../components/SignButtons';

import {signIn, signUp} from '../lib/auth'
import {getUser} from '../lib/users'

import {useUserContext} from '../contexts/UserContext'

function SignInScreen({ navigation, route }) {
    //로그인인지 회원 가입인지 구분하기 위한 변수를 생성
    const {isSignUp} = route.params ?? {};

    //속성 과 속성을 수정하는 함수 그리고 기본값을 설정
    const [form, setForm] = useState({
      email:'',
      password:'',
      confirmPassword:''
    });

    const [loading, setLoading] = useState();
    const {setUser} = useUserContext();
    //form 에 데이터를 설정하는 함수 - BorderedInput에 연결
    const createChangeTextHandler = name => value => {
      //form 속성 안에서 name에 value를 설정
      setForm({...form, [name]:value});
    };

    //버튼을 눌렀을 때 호출될 함수
    const onSubmit = async() => {
      Keyboard.dismiss();
      console.log(form);

      //입력한 내용 가져오기
      const {email, password} = form;
      const info = {email, password};
      //스피너를 화면에 출력
      setLoading(true);

      try{
        //isSignUp 에 따라 파이어베이스에 저장하던가 로그인을 수행
        const {user} = isSignUp? await signUp(info) : await signIn(info);
        //작업이 성공적으로 완료되면 uid를 이용해서 user의 정보를 가져오기
        const profile = await getUser(user.uid);
        if(!profile){
          navigation.navigate('Welcome', {uid:user.uid});
        }else{
            setUser(profile)
        }
        console.log(user);
      }catch(e){
        console.log(e);

        const messages = {
          'auth/email-already-in-use':'이미 가입된 이메일입니다.',
          'auth/wrong-password': '잘못된 비밀번호입니다.',
          'auth/user-not-found': '존재하지 않는 계정입니다.',
          'auth/invalid-email': '유효하지 않은 이메일 입니다.'
        };

        const msg = messages[e.code] || `${isSignUp ? '가입' : '로그인'} 실패`;
        Alert.alert('실패', msg);
      }

    }

    //password 와 confirmPassword에 대한 참조를 가져온 것입니다.
    const passwordRef = useRef();
    const confirmPasswordRef = useRef();

    return (
        <KeyboardAvoidingView style={styles.KeyboardAvoidingView} 
        behavior={Platform.select({ios:'padding'})}>

        <SafeAreaView style={styles.fullscreen}>
            <Text style={styles.text}>PublicGallery</Text>
            <View style={styles.form}>
              <SignForm style={styles.form}
                isSignUp={isSignUp}
                onSubmit = {onSubmit}
                form={form}
                createChangeTextHandler = {createChangeTextHandler} />

              <SignButtons isSignUp={isSignUp} onSubmit={onSubmit} loading={loading}/>
            </View>  
        </SafeAreaView>
        </KeyboardAvoidingView>
    );
}

const styles = StyleSheet.create({
  KeyboardAvoidingView:{
    flex:1
  },
    fullscreen: {
      flex: 1,
      alignItems: 'center',
      justifyContent: 'center',
    },
    text: {
      fontSize: 32,
      fontWeight: 'bold',
    },
    form:{
      marginTop:64,
      width:'100%',
      paddingHorizontal:16
    },
    buttons:{
      marginTop:64
    }
  });
  
  export default SignInScreen;
```

### 12)Welcome 화면에서 프로필 정보를 등록한 경우를 처리하기 위해서 SetupProfile.js 파일을 수정  
```javascript
import { useNavigation, useRoute } from "@react-navigation/native";
import React, {useState} from 'react';
import {StyleSheet, View} from 'react-native';
import {signOut} from '../lib/auth';
import {createUser} from '../lib/users';

import BorderedInput from "./BorderedInput";
import CustomButton from "./CustomButton";

import {useUserContext} from '../contexts/UserContext'

function SetupProfile(){
    //닉네임 변수
    const [displayName, setDisplayName] = useState("");
    //화면 전환을 수행하는 navigation 찾아오기
    const navigation = useNavigation();

    //파라미터 생성
    const {params} = useRoute();

    const {uid} = params || {};

    //버튼을 눌렀을 때 Firebase 의 Storage에 저장
    const onSubmit = async () => {
        const user = {
            id:uid,
            displayName,
            photoURL:null
        }
        createUser(user);
        setUser(user);
    }

    //취소를 누른 경우
    const onCancel = () => {
        //로그 아웃
        signOut();
        //이전 화면으로 돌아가기
        navigation.goBack();
    }

    return (
        <View style={styles.block}>
            <View style={styles.circle} />
            <View style={styles.form} >
                <BorderedInput
                    placeholder="닉네임"
                    value={displayName}
                    onChangeText={setDisplayName}
                    onSubmitEditing={onSubmit}
                    returnKeyType="next"/>
                <CustomButton title="다음" onPress={onSubmit} hasMarginBottom />
                <CustomButton title="취소" onPress={onCancel} theme="secondary" />
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    block:{
        alignItems:'center',
        marginTop: 24,
        paddingHorizontal: 16,
        width:'100%'
    },
    circle:{
        backgroundColor:'#cdcdcd',
        borderRadius: 64,
        width:128,
        height:128
    },
    form:{
        marginTop:16,
        width:'100%'
    },
    buttons:{
        marginTop:48
    }
})

export default SetupProfile;
```  

### 13)로그인이 정상적으로 처리된 후 보여질 컴포넌트를 screens 디렉토리에 MainTab.js 파일에 작성  
```javascript
import React from "react";
import {StyleSheet, Text, View} from 'react-native';
import {useUserContext} from '../contexts/UserContext';

function MainTab(){
    const {user} = useUserContext();
    return (
        <View style={styles.block}>
            <Text style={styles.text}>Hello {user.displayName}</Text>
        </View>
    )
}

const styles = StyleSheet.create({
    block:{
        flext:1,
        alignItems: 'center',
        justifyContent:'center'
    },
    text:{
        fontSize:24
    }
})

export default MainTab;

14)RootStack 에서 사용자 정보가 있는 경우 MainTab을 출력하도록 코드를 추가
import React from 'react';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import SignInScreen from './SignInScreen';
import WelcomeScreen from './WelcomeScreen';

const Stack = createNativeStackNavigator();

import {useUserContext} from '../contexts/UserContext';
import MainTab from './MainTab';

function RootStack() {
  const {user} = useUserContext(); 
  return (
    <Stack.Navigator>
      {
        user?(
          <>
            <Stack.Screen name="MainTab" component={MainTab} options={{headerShown:false}} />
          </>
        ):(
          <>
            <Stack.Screen
              name="SignIn"
              component={SignInScreen}
              options={{headerShown:false}}
            />
            <Stack.Screen
              name="Welcome"
              component={WelcomeScreen}
              options={{headerShown:false}}
            />  
          </>
        )
      }

      
    </Stack.Navigator>
  );
}

export default RootStack;
```  

## 7.디바이스의 이미지 사용  
### 1)라이브러리  
* react-native-image-picker  
* 설치 : yarn add react-native-image-picker  

### 2) 권한 설정  
* iOS에서는 iOS/앱이름 디렉터리의 info.plist에서 설정  
* Android는 권한 설정을 android/app/src/main/AndroidMainfest.xml파일에서 설정  
```xml
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```  

### 3) 갤러리나 카메라 사용  
import { launchcamera, launchImageLibrary } from 'react-native-image-picker'

* 카메라 이용  
launchcarmera(options, callback)  
options는 생략가능  

* 갤러리 사용
launchImageLibrary(options, callback)  
    - options 속성  
        mediaType : photo 또는 video  
        maxWidth, maxHeigth  
        videoQuality : 영상에서의 화질로 low와 high 선택가능하고 iOS에서는 medium도 있음  
        quality : 이미지의 화질로 0에서 1 사이의 숫자로 설정  
        selectcionLimit : 선택할 이미지의 수인데 기본값은 1이고, 0을 설정하면 무한대로 선택 가능  

    - callback 함수의 매개변수  
        didCancel : 사용자가 선택을 취소하면 true를 넘겨줌  
        errorCode : 에러에 대한 코드 정보  
        errorMessage : 에러 메시지  
        assests : 선택한 이미지의 정보 객체 배열  
            base64  
            uri  
            width  
            height  
            fileSize  
            type  
            fileName  

### 4) 이미지를 선택해서 화면에 출력  
* 갤러리를 불러오도록 SetupProfile.js 파일을 수정  
