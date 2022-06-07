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

## 5. 데이터가 없을 때 보여지는 화면을 AddToDo 위에 출력  
### 1)components 디렉토리에 Empty.js 파일을 생성하고 작성  
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

### 2) App.js 파일에서 Empty.js 파일을 출력하도록 설정  
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

* resizeMode속성을 이용해서 크기가 원본 이미지와 맞지 않을 때 이미지크기를 조정  
  - cover(기본 값 - 리사이징만 수행)  
  - contain(이미지의 가로세로 비율을 유지한 이미지를 리사이징해서 이미지의 모든 영역이 뷰 안에 보이게 함)  
  - stretch(뷰의 크기대로 이미지를 리사이징 - 가로세로 비율이 변경됨)  
  - repeat
  - center

### 4) 이미지 출력을 위해서 Empty.js파일을 수정  
```javascript
import React from 'react'
import {View, Text, StyleSheet, Image} from 'react-native'
import {useSafeAreaInsets} from 'react-native-safe-area-context'

function Empty({data}){
    return (
        <View style = {styles.block}>
            <Image source={require('../assets/images/young_and_happy.png')} style={styles.image} resizeMode="stretch"/>
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
    },
    image:{
        width:240,
        height:179,
        marginBottom:16
    }
});

export default Empty;
```  

## 6. 사용자 입력을 받기 위한 준비 - 키보드 입력  
### 1) AddToDo.js파일에 키보드 입력을 위한 뷰(TextInput)를 배치  
```javascript
import React from "react";
import {View, StyleSheet,TextInput} from 'react-native'

function AddToDo(){
    return (
        <View style = {styles.block}>
            <TextInput placeholder="수행할 내용을 입력하세요" style={styles.input}/>
        </View>
    )
}

const styles = StyleSheet.create({
    block:{
        backgroundColor:'red',
        height:64,
        paddingHorizontal:16,
        borderColor:"#bdbdbd",
        borderBottomWidth:1,
        borderTopWidth:1,
        justifyContent:'center'
    },
    input:{
        fontSize:16,
        paddingVertical:8
    }
})

export default AddToDo;
```  

### 2) 키보드를 출력하는 UI를 화면에 배치했을 때 고려사항  
* Android는 하단에 메뉴가 있어서 키보드를 메뉴를 이용해서 내릴 수 있지만 아이폰은 안됩니다.  
  return키를 누를 때나 빈 화면을 눌렀을 때 키보드가 내려가도록 하는 것이 일반적  
* 키보드가 화면에 출력되었을 때 다른 뷰를 가리는 현상이 벌어지는데 이를 해결하는 방법에 대해 고민을 해봐야 합니다.  
  키보드가 화면에 출력될 때 다른 뷰들을 약간 올려서 출력하는 방법으로 해결  
  리액트 네이티브에서는 이 문제를 KeyboardAvoidingView를 이용해서 해결

3)App.js 파일을 수정  
```javascript
import React from 'react';
import {
  StyleSheet,
  Text,
  View,
  KeyboardAvoidingView,
  Platform
} from 'react-native';

import DateHead from './components/DateHead'
import {SafeAreaProvider, SafeAreaView} from 'react-native-safe-area-context'

import AddToDo from './components/AddToDo'
import Empty from './components/Empty'

function App(){
  const today = new Date();
  
  return (
    <SafeAreaProvider>
    <SafeAreaView edges={['bottom']} style={styles.block} >
        <KeyboardAvoidingView 
      behavior={Platform.OS === 'ios' ? 'padding' : undefined} style={styles.avoid}>
        <DateHead date={today} />
        <Empty />
        <AddToDo />
        </KeyboardAvoidingView>
    </SafeAreaView>
    </SafeAreaProvider>
  );
};

const styles = StyleSheet.create({ 
  block:{
    flex:1
  },
  avoid:{
    flex:1
  }
});

export default App;
```  
### 4) App.js파일을 수정
```javascript
import React, {useState} from "react";
import {View, StyleSheet,TextInput} from 'react-native'

function AddToDo(){
    // text라는 속성을 생성하고 setText라는 함수로 수정. 기본값은 ''
    const [text, setText] = useState('');
    console.log(text); // metro에 찍힘
    return (
        <View style = {styles.block}>
            <TextInput placeholder="수행할 내용을 입력하세요" style={styles.input}
            value={text} onChangeText={setText}/>
        </View>
    )
}

const styles = StyleSheet.create({
    block:{
        backgroundColor:'red',
        height:64,
        paddingHorizontal:16,
        borderColor:"#bdbdbd",
        borderBottomWidth:1,
        borderTopWidth:1,
        justifyContent:'center'
    },
    input:{
        fontSize:16,
        paddingVertical:8
    }
})

export default AddToDo;
```  

## 7. Custom Button 만들기  
* 이미지나 직접 드로잉 한 객체를 이용해서 버튼과 유사한 효과를 만들기  

### 1) AddToDo.js 수정하기
```javascript
import React, {useState} from "react";
import {View, StyleSheet,TextInput, Image} from 'react-native'

function AddToDo(){
    // text라는 속성을 생성하고 setText라는 함수로 수정. 기본값은 ''
    const [text, setText] = useState('');
    console.log(text); // metro에 찍힘
    return (
        <View style = {styles.block}>
            <TextInput placeholder="수행할 내용을 입력하세요" style={styles.input}
            value={text} onChangeText={setText}/>

            <View style={styles.buttonStyle}>
                <Image source={require('../assets/icons/add_white/add_white.png')} />
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    block:{
        backgroundColor:'white',
        height:64,
        paddingHorizontal:16,
        borderColor:"#bdbdbd",
        borderBottomWidth:1,
        borderTopWidth:1,
        justifyContent:'center',
        flexDirection:'row' // 옆으로 매치하기 위해
    },
    input:{
        flex:1,
        fontSize:16,
        paddingVertical:8
    },
    buttonStyle:{
        alignItems:'center',
        justifyContent:'center',
        width:48,
        height:48,
        backgroundColor:'#26a69a',
        borderRadius:24
    }
})

export default AddToDo;
```  

### 3) 이미지를 눌렀을 떄 버튼을 눌렀을 때의 반응과 비슷하게 해주기 위해 AddToDo.js에 추가
```javascript
import React, {useState} from "react";
import {View, StyleSheet,TextInput, Image, TouchableOpacity} from 'react-native'

function AddToDo(){
    // text라는 속성을 생성하고 setText라는 함수로 수정. 기본값은 ''
    const [text, setText] = useState('');
    console.log(text); // metro에 찍힘
    return (
        <View style = {styles.block}>
            <TextInput placeholder="수행할 내용을 입력하세요" style={styles.input}
            value={text} onChangeText={setText}/>
            <TouchableOpacity activeOpacity={0.5}>
            <View style={styles.buttonStyle}>
                <Image source={require('../assets/icons/add_white/add_white.png')} />
            </View>
            </TouchableOpacity>
        </View>
    )
}

const styles = StyleSheet.create({
    block:{
        backgroundColor:'white',
        height:64,
        paddingHorizontal:16,
        borderColor:"#bdbdbd",
        borderBottomWidth:1,
        borderTopWidth:1,
        justifyContent:'center',
        flexDirection:'row' // 옆으로 매치하기 위해
    },
    input:{
        flex:1,
        fontSize:16,
        paddingVertical:8
    },
    buttonStyle:{
        alignItems:'center',
        justifyContent:'center',
        width:48,
        height:48,
        backgroundColor:'#26a69a',
        borderRadius:24
    }
})

export default AddToDo;
```  

### 4) 이미지를 터치하면 물결효과를 나타내도록 AddToDo.js파일을 수정  
* 물결효과는 안드로이드에서만 가능, iOS에서는 에러 발생  
* 물결효과 -> TouchableNativeFeedback
```javascript
import React, {useState} from "react";
import {View, StyleSheet,TextInput, Image, TouchableOpacity, Platform, TouchableNativeFeedback} from 'react-native'

function AddToDo(){
    // text라는 속성을 생성하고 setText라는 함수로 수정. 기본값은 ''
    const [text, setText] = useState('');
    console.log(text); // metro에 찍힘

    return (
        <View style = {styles.block}>
            <TextInput placeholder="수행할 내용을 입력하세요" style={styles.input}
            value={text} onChangeText={setText}/>

            {Platform.OS === 'ios' ? (
                <TouchableOpacity activeOpacity={0.5}>
                    <View style={styles.buttonStyle}>
                        <Image source={require('../assets/icons/add_white/add_white.png')} />
                    </View>
                </TouchableOpacity>
            ) : (
                <TouchableNativeFeedback>
                    <View style={styles.buttonStyle}>
                        <Image source={require('../assets/icons/add_white/add_white.png')} />
                    </View>
                </TouchableNativeFeedback>
            )}
        </View>
    )
}

const styles = StyleSheet.create({
    block:{
        backgroundColor:'white',
        height:64,
        paddingHorizontal:16,
        borderColor:"#bdbdbd",
        borderBottomWidth:1,
        borderTopWidth:1,
        justifyContent:'center',
        flexDirection:'row' // 옆으로 매치하기 위해
    },
    input:{
        flex:1,
        fontSize:16,
        paddingVertical:8
    },
    buttonStyle:{
        alignItems:'center',
        justifyContent:'center',
        width:48,
        height:48,
        backgroundColor:'#26a69a',
        borderRadius:24
    }
})

export default AddToDo;
```  

### 5) 안드로이드에 적용된 물결 효과는 사각 영역에 적용되어서 원형에는 맞지 않으므로 물결효과가 원형에만 적용되도록 AddToDo.js 수정  
```javascript
import React, {useState} from "react";
import {View, StyleSheet,TextInput, Image, TouchableOpacity, Platform, TouchableNativeFeedback} from 'react-native'

function AddToDo(){
    // text라는 속성을 생성하고 setText라는 함수로 수정. 기본값은 ''
    const [text, setText] = useState('');
    console.log(text); // metro에 찍힘

    return (
        <View style = {styles.block}>
            <TextInput placeholder="수행할 내용을 입력하세요" style={styles.input}
            value={text} onChangeText={setText}/>

            {Platform.OS === 'ios' ? (
                <TouchableOpacity activeOpacity={0.5}>
                    <View style={styles.buttonStyle}>
                        <Image source={require('../assets/icons/add_white/add_white.png')} />
                    </View>
                </TouchableOpacity>
            ) : (
                <View style={styles.circleWrapper}>
                    <TouchableNativeFeedback>
                        <View style={styles.buttonStyle}>
                            <Image source={require('../assets/icons/add_white/add_white.png')} />
                        </View>
                    </TouchableNativeFeedback>
                </View>
            )}
        </View>
    )
}

const styles = StyleSheet.create({
    block:{
        backgroundColor:'white',
        height:64,
        paddingHorizontal:16,
        borderColor:"#bdbdbd",
        borderBottomWidth:1,
        borderTopWidth:1,
        justifyContent:'center',
        flexDirection:'row' // 옆으로 매치하기 위해
    },
    input:{
        flex:1,
        fontSize:16,
        paddingVertical:8
    },
    buttonStyle:{
        alignItems:'center',
        justifyContent:'center',
        width:48,
        height:48,
        backgroundColor:'#26a69a',
        borderRadius:24
    },
    circleWrapper: {
        overflow: 'hidden',
        borderRadius:24
    }
})

export default AddToDo;
```  

### 6) 이미지로 만든 버튼을 눌렀을 때, Input의 내용을 삭제하고 키보드를 제거하도록 AddToDo.js파일을 수정  
```javascript
import React, {useState} from "react";
import {View, StyleSheet,TextInput, Image, TouchableOpacity, Platform, TouchableNativeFeedback, Keyboard} from 'react-native'

function AddToDo(){
    // text라는 속성을 생성하고 setText라는 함수로 수정. 기본값은 ''
    const [text, setText] = useState('');
    console.log(text); // metro에 찍힘

    const onPress = () =>{
        setText("");
        Keyboard.dismiss();
    }

    return (
        <View style = {styles.block}>
            <TextInput placeholder="수행할 내용을 입력하세요" style={styles.input}
            value={text} onChangeText={setText}/>

            {Platform.OS === 'ios' ? (
                <TouchableOpacity activeOpacity={0.5} onPress={onPress}>
                    <View style={styles.buttonStyle}>
                        <Image source={require('../assets/icons/add_white/add_white.png')} />
                    </View>
                </TouchableOpacity>
            ) : (
                <View style={styles.circleWrapper}>
                    <TouchableNativeFeedback>
                        <View style={styles.buttonStyle}>
                            <Image source={require('../assets/icons/add_white/add_white.png')} />
                        </View>
                    </TouchableNativeFeedback>
                </View>
            )}
        </View>
    )
}

const styles = StyleSheet.create({
    block:{
        backgroundColor:'white',
        height:64,
        paddingHorizontal:16,
        borderColor:"#bdbdbd",
        borderBottomWidth:1,
        borderTopWidth:1,
        justifyContent:'center',
        flexDirection:'row' // 옆으로 매치하기 위해
    },
    input:{
        flex:1,
        fontSize:16,
        paddingVertical:8
    },
    buttonStyle:{
        alignItems:'center',
        justifyContent:'center',
        width:48,
        height:48,
        backgroundColor:'#26a69a',
        borderRadius:24
    },
    circleWrapper: {
        overflow: 'hidden',
        borderRadius:24
    }
})

export default AddToDo;
```

### 7) Return Key의 모양을 설정하고 Return Key를 눌렀을 때 버튼을 누른 것과 동일한 효과를 나타내도록 AddToDo.js를 수정  
```javascript
import React, {useState} from "react";
import {View, StyleSheet,TextInput, Image, TouchableOpacity, Platform, TouchableNativeFeedback, Keyboard} from 'react-native'

function AddToDo(){
    // text라는 속성을 생성하고 setText라는 함수로 수정. 기본값은 ''
    const [text, setText] = useState('');
    console.log(text); // metro에 찍힘

    const onPress = () =>{
        setText("");
        Keyboard.dismiss();
    }

    return (
        <View style = {styles.block}>
            <TextInput placeholder="수행할 내용을 입력하세요" style={styles.input}
            value={text} onChangeText={setText} returnKeyType="done" onSubmitEditing={onPress}/>

            {Platform.OS === 'ios' ? (
                <TouchableOpacity activeOpacity={0.5} onPress={onPress}>
                    <View style={styles.buttonStyle}>
                        <Image source={require('../assets/icons/add_white/add_white.png')} />
                    </View>
                </TouchableOpacity>
            ) : (
                <View style={styles.circleWrapper}>
                    <TouchableNativeFeedback>
                        <View style={styles.buttonStyle}>
                            <Image source={require('../assets/icons/add_white/add_white.png')} />
                        </View>
                    </TouchableNativeFeedback>
                </View>
            )}
        </View>
    )
}

const styles = StyleSheet.create({
    block:{
        backgroundColor:'white',
        height:64,
        paddingHorizontal:16,
        borderColor:"#bdbdbd",
        borderBottomWidth:1,
        borderTopWidth:1,
        justifyContent:'center',
        flexDirection:'row' // 옆으로 매치하기 위해
    },
    input:{
        flex:1,
        fontSize:16,
        paddingVertical:8
    },
    buttonStyle:{
        alignItems:'center',
        justifyContent:'center',
        width:48,
        height:48,
        backgroundColor:'#26a69a',
        borderRadius:24
    },
    circleWrapper: {
        overflow: 'hidden',
        borderRadius:24
    }
})

export default AddToDo;
```  

## 8. todos 배열을 만들어서 항목 출력  
### 1) App.js파일에 데이터를 저장할 배열을 생성  
* react-native에서는 useState이용  
```javascript
import React, {useState} from 'react';
import {
  StyleSheet,
  Text,
  View,
  KeyboardAvoidingView,
  Platform
} from 'react-native';

import DateHead from './components/DateHead'
import {SafeAreaProvider, SafeAreaView} from 'react-native-safe-area-context'

import AddToDo from './components/AddToDo'
import Empty from './components/Empty'

function App(){
  const today = new Date();
  const [todos, setTodos] = useState([
    {id : 1, text:'작업 환경 설정', done:true},
    {id : 2, text:'BackEnd - Spring Boot', done:true},
    {id : 3, text:'FrontEnd - ReactNative', done:false}
  ])
  return (
    <SafeAreaProvider>
    <SafeAreaView edges={['bottom']} style={styles.block} >
        <KeyboardAvoidingView 
      behavior={Platform.OS === 'ios' ? 'padding' : undefined} style={styles.avoid}>
        <DateHead date={today} />
        <Empty />
        <AddToDo />
        </KeyboardAvoidingView>
    </SafeAreaView>
    </SafeAreaProvider>
  );
};

const styles = StyleSheet.create({ 
  block:{
    flex:1
  },
  avoid:{
    flex:1
  }
});

export default App;
```