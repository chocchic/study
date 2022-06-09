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

### 2) todos 목록을 출력하기 위한 ToDoList컴포넌트를 위한 ToDoList.js파일을 components 디렉터리에 생성하고 작성  
* 여러 개의 데이터 목록은 Table 형태의 View를 이용해서 출력하는 것이 일반적인데 ReactNative에서는 FlatList를 제공하는데, Android에서는 TableVIew나 RecyclerView를 제공하고 iOS에서는 TableView와 같은 뷰를 제공해서 뛰어난 UI와 기능을 제공합니다.  

* 특별한 경우가 아니라면 여러개의 데이터 목록은 프레임워크나 API에서 제공해주는 기능을 이용하는 것이 좋습니다.  

```javascript
import React from 'react'
import {View, Text, StyleSheet, FlatList} from 'react-native'

// 출력할 데이터를 todos라는 이름으로 넘겨받는다
function ToDoList({todos}){
    return (
       <FlatList style={styles.list} data={todos} renderItem={({item})=>{
            <View>
                <Text>{item.next}</Text>
            </View>
       }} keyExtractor={item=> item.id.toString()} />
    );
}

const styles = StyleSheet.create({
    list :{
        flex:1
    }
});

export default ToDoList;
```  

### 3) App.js파일에서 ToDOList 컴포넌트 추가하는 코드를 추가  
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
import ToDoList from './components/ToDoList';

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
        {todos.length === 0 ? <Empty /> : <ToDoList todos={todos}/>}
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

### 4) FlatList에서 하나의 항목을 출력할 때 사용하기 위한 컴포넌트를 components디렉터리에 ToDoItem.js파일에 작성  
```javascript
import React from 'react'
import {View, Text, StyleSheet, FlatList} from 'react-native'

function ToDoItem({id, text, done}){
    return (
       <View style={styles.item}>
           <View style={styles.circle}/>
           <Text style={styles.text}>{text}</Text>
       </View>
    );
}

const styles = StyleSheet.create({
    item:{
        flexDirection:"row",
        padding:16,
        borderBottomColor:"#e0e0e0",
        alignItems:'center'
    },
    circle:{
        width:24,
        height:24,
        borderRadius:12,
        borderColor:'#26a69a',
        borderWidth:1,
        marginRight:16
    },
    text:{
        flex:1,
        fontSize:16,
        color:'#212121'
    }
});

export default ToDoItem;
``` 


### 5) FlatList에서 ToDoItem을 출력할 수 있도록 ToDoList.js를 수정  
```javascript
// 출력할 데이터를 todos라는 이름으로 넘겨받는다
function ToDoList({todos}){
    return (
       <FlatList style={styles.list} data={todos} renderItem={({item})=>{
            <ToDoItem id={item.id} text={item.text} done={item.done}/>
       }} keyExtractor={item=> item.id.toString()} />
    );
}
```  

### 6) ToDoList.js파일을 수정해서 Item사이에 구분선을 설정  
```javascript
import React from 'react'
import {View, Text, StyleSheet, FlatList} from 'react-native'
import ToDoItem from './ToDoItem'

// 출력할 데이터를 todos라는 이름으로 넘겨받는다
function ToDoList({todos}){
    return (
       <FlatList style={styles.list} data={todos} renderItem={({item})=>{
            <ToDoItem id={item.id} text={item.text} done={item.done}/>
       }} keyExtractor={item=> item.id.toString()} 
       ItemSeparatorComponent={()=> <View style={styles.seperator}/>}
       />
    );
}

const styles = StyleSheet.create({
    list :{
        flex:1
    },
    seperator:{
        backgroundColor:'#e0e0e0',
        height:1
    }
});

export default ToDoList;
```  

### 7) done 항목의 값이 true일 때 이미지를 출력하기 위해서 사용할 이미지 파일을 복사 - assets/icons/check_white  

### 8) ToDoItem.js파일의 내용을 수정해서 done이 true일 때와 그렇지 않을 때의 출력을 변경  
* done && styles.filled는 done?styles.filled:null와 같은 의미
```javascript
import React from 'react'
import {View, Text, StyleSheet, Image} from 'react-native'

function ToDoItem({id, text, done}){
    return (
       <View style={styles.item}>
            <View style={[styles.circle, done && styles.filled]}>
                {done && (<Image source={require('../assets/icons/check_white/check_white.png')}/>)}
            </View> 
            <Text style={[styles.text, done && styles.lineThrough]}>{text}</Text>
       </View>
    );
}

const styles = StyleSheet.create({
    item:{
        flexDirection:"row",
        padding:16,
        borderBottomColor:"#e0e0e0",
        alignItems:'center'
    },
    circle:{
        width:24,
        height:24,
        borderRadius:12,
        borderColor:'#26a69a',
        borderWidth:1,
        marginRight:16
    },
    text:{
        flex:1,
        fontSize:16,
        color:'#212121'
    },
    filled:{
        justifyContent:'center',
        alignItems:'center',
        backgroundColor:'#26a69a'
    },
    lineThrough:{
        color:'#9e9e9e',
        textDecorationLine:'line-through'
    }
});

export default ToDoItem;
```

## 9. 새 항목 등록  
### 1) App.js파일에 새 항목 등록에 필요한 함수를 구현  
* javascript에서 for 안쓰는 이유
    var ar = ["one", "two"]
    for(idx in ar) {ar[idx]}
    이런식으로 작성하면 귀찮으니깐 ...map()을 사용한다.
```javascript
function App(){
  // 오늘 날짜 생성
  const today = new Date();

  // 데이터를 저장하기 위한 속성 생성
  const [todos, setTodos] = useState([
    {id : 1, text:'작업 환경 설정', done:true},
    {id : 2, text:'BackEnd - Spring Boot', done:true},
    {id : 3, text:'FrontEnd - ReactNative', done:false}
  ])

  // 데이터를 삽입하기 위한 함수
  function onInsert(text){
    // 가장 큰 id를 찾아서 +1
    const nextId = todos.length > 0 ? Math.max(...todos.map(todo => todo.id)) + 1 : 1;
    // 하나의 인스턴스 생성
    const todo={id:nextId, text, done:false}
    // 배열에 추가한 후 배열을 todos에 대입, 둘 다 가능 
    //setTodos(todos.push(todo))
    setTodos(todos.concat(todo))
  }

  return (
    <SafeAreaProvider>
    <SafeAreaView edges={['bottom']} style={styles.block} >
        <KeyboardAvoidingView 
      behavior={Platform.OS === 'ios' ? 'padding' : undefined} style={styles.avoid}>
        <DateHead date={today} />
        {todos.length === 0 ? <Empty /> : <ToDoList todos={todos}/>}
        <AddToDo onInsert={onInsert} />
        </KeyboardAvoidingView>
    </SafeAreaView>
    </SafeAreaProvider>
  );
};
```

### 2) 데이터 삽입을 위해서 AddToDo.js파일을 수정  
```javascript
    // 버튼을 누르거나 Return Key를 눌렀을 때 호출되는 함수  
    const onPress = () =>{
        onInsert({text});
        setText("");
        Keyboard.dismiss();
    }
```  

## 10. 할 일 완료 상태 토글 - done의 값을 toggle  
### 1) App.js파일에 토글을 위한 함수를 생성  
```javascript
function App(){
  // 오늘 날짜 생성
  const today = new Date();

  // 데이터를 저장하기 위한 속성 생성
  const [todos, setTodos] = useState([
    {id : 1, text:'작업 환경 설정', done:true},
    {id : 2, text:'BackEnd - Spring Boot', done:true},
    {id : 3, text:'FrontEnd - ReactNative', done:false}
  ])

  // 데이터를 삽입하기 위한 함수
  function onInsert(text){
    // 가장 큰 id를 찾아서 +1
    const nextId = todos.length > 0 ? Math.max(...todos.map(todo => todo.id)) + 1 : 1;
    // 하나의 인스턴스 생성
    const todo={id:nextId, text, done:false}
    // 배열에 추가한 후 배열을 todos에 대입, 둘 다 가능 
    //setTodos(todos.push(todo))
    setTodos(todos.concat(todo))
  }

  // done의 값을 토글시키기 위한 함수 
  // id를 찾아서 id에 해당하는 데이터를 찾아서 done의 값을 토글시키기
  function onToggle(id){
    const nextTodos = todos.map(todo=> todo.id === id? {...todo, done:!todo.done}:todo);
    setTodos(nextTodos)
  }

  return (
    <SafeAreaProvider>
    <SafeAreaView edges={['bottom']} style={styles.block} >
        <KeyboardAvoidingView 
      behavior={Platform.OS === 'ios' ? 'padding' : undefined} style={styles.avoid}>
        <DateHead date={today} />
        {todos.length === 0 ? <Empty /> : <ToDoList todos={todos} onToggle={onToggle}/>}
        <AddToDo onInsert={onInsert} />
        </KeyboardAvoidingView>
    </SafeAreaView>
    </SafeAreaProvider>
  );
};
```  

### 2) ToDoList.js파일에 토글 이벤트 설정을 위한 코드를 작성  
```javascript
// 출력할 데이터를 todos라는 이름으로 넘겨받는다
function ToDoList({todos, onToggle}){
    return (
       <FlatList style={styles.list} data={todos}
        renderItem={({item})=>{
            <ToDoItem id={item.id} text={item.text} done={item.done} onToggle={onToggle}/>
        }} keyExtractor={item=> item.id.toString()} 
        ItemSeparatorComponent={()=> <View style={styles.seperator}/>}
       />
    );
}
```  

### 3) FlatList의 각 항목이 터치를 사용할 수 있도록 ToDoItem.js를 수정  
```javascript
function ToDoItem({id, text, done, onToggle}){
    return (
       <View style={styles.item}>
           <TouchableOpacity onPress={()=>onToggle(id)}>
            <View style={[styles.circle, done && styles.filled]}>
                {done && (<Image source={require('../assets/icons/check_white/check_white.png')}/>)}
            </View> 
            </TouchableOpacity>
            <Text style={[styles.text, done && styles.lineThrough]}>{text}</Text>
       </View>
    );
}
```  

## 11. 항목 삭제  
### 1) 벡터 아이콘 사용을 위한 패키지 설치  
yarn add react-native-vector-icons -> https://oblador.github.io/react-native-vector-icons  

### 2) iOS 작업  
* 터미널에서 작성
    cd ios  
    pod install  

* ios/앱이름 디렉터리의 info.plist파일을 열어서 가장 하단(</dict> 앞)에 추가  
    <array>
        <string> MaterialIcons.ttf</string>
    </array>

### 3) android 작업  
* android/app/build.gradle 파일의 하단에 추가  
    apply from:file("../../node_modules/react-native-vector-icons/fonts.gradle")  

### 4) application을 다시 빌드  
yarn ios
yarn android  

### 5) 삭제아이콘 출력을 위해서 ToDoItems.js파일 수정  
```javascript
import React from 'react'
import {View, Text, StyleSheet, Image, TouchableOpacity} from 'react-native'

import Icon from 'react-native-vector-icons/MaterialIcons'

function ToDoItem({id, text, done, onToggle}){
    return (
       <View style={styles.item}>
           <TouchableOpacity onPress={()=>onToggle(id)}>
            <View style={[styles.circle, done && styles.filled]}>
                {done && (<Image source={require('../assets/icons/check_white/check_white.png')}/>)}
            </View> 
            </TouchableOpacity>
            <Text style={[styles.text, done && styles.lineThrough]}>{text}</Text>
            {done?(
                <Icon name="delete" size={32} color="red"/>
            ):(
                <View style={styles.removePlaceholder}/>
            )}
       </View>
    );
}

const styles = StyleSheet.create({
    item:{
        flexDirection:"row",
        padding:16,
        borderBottomColor:"#e0e0e0",
        alignItems:'center'
    },
    circle:{
        width:24,
        height:24,
        borderRadius:12,
        borderColor:'#26a69a',
        borderWidth:1,
        marginRight:16
    },
    text:{
        flex:1,
        fontSize:16,
        color:'#212121'
    },
    filled:{
        justifyContent:'center',
        alignItems:'center',
        backgroundColor:'#26a69a'
    },
    lineThrough:{
        color:'#9e9e9e',
        textDecorationLine:'line-through'
    },
    removePlaceholder:{
        width:32,
        height:32
    }
});

export default ToDoItem;
```  

### 6) App.js파일에 삭제를 위한 함수 추가  
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
import ToDoList from './components/ToDoList';

function App(){
  // 오늘 날짜 생성
  const today = new Date();

  // 데이터를 저장하기 위한 속성 생성
  const [todos, setTodos] = useState([
    {id : 1, text:'작업 환경 설정', done:true},
    {id : 2, text:'BackEnd - Spring Boot', done:true},
    {id : 3, text:'FrontEnd - ReactNative', done:false}
  ])

  // 데이터를 삽입하기 위한 함수
  function onInsert(text){
    // 가장 큰 id를 찾아서 +1
    const nextId = todos.length > 0 ? Math.max(...todos.map(todo => todo.id)) + 1 : 1;
    // 하나의 인스턴스 생성
    const todo={id:nextId, text, done:false}
    // 배열에 추가한 후 배열을 todos에 대입, 둘 다 가능 
    //setTodos(todos.push(todo))
    setTodos(todos.concat(todo))
  }

  // done의 값을 토글시키기 위한 함수 
  // id를 찾아서 id에 해당하는 데이터를 찾아서 done의 값을 토글시키기
  function onToggle(id){
    const nextTodos = todos.map(todo=> todo.id === id? {...todo, done:!todo.done}:todo);
    setTodos(nextTodos)
  }

  // 데이터를 삭제하기 위한 함수
  function onRemove(id){
    // 매개변수로 넘어온 아이디가 아닌 것만 골라서 nextTodos를 생성
    const nextTodos = todos.filter(todo => todo.id != id)
    setTodos(nextTodos)
  }

  return (
    <SafeAreaProvider>
    <SafeAreaView edges={['bottom']} style={styles.block} >
        <KeyboardAvoidingView 
      behavior={Platform.OS === 'ios' ? 'padding' : undefined} style={styles.avoid}>
        <DateHead date={today} />
        {todos.length === 0 ? <Empty /> : <ToDoList todos={todos} onToggle={onToggle} onRemove={onRemove}/>}
        <AddToDo onInsert={onInsert} />
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

### 7) ToDoList.js 파일에 삭제함수를 받아서 ToDoItem.js파일에 넘겨주는 코드를 작성  
```javascript
import React from 'react'
import {View, Text, StyleSheet, FlatList} from 'react-native'
import ToDoItem from './ToDoItem'

// 출력할 데이터를 todos라는 이름으로 넘겨받는다
function ToDoList({todos, onToggle, onRemove}){
    return (
       <FlatList style={styles.list} data={todos}
        renderItem={({item})=>{
            <ToDoItem id={item.id} text={item.text} done={item.done} onToggle={onToggle} onRemove={onRemove}/>
        }} keyExtractor={item=> item.id.toString()} 
        ItemSeparatorComponent={()=> <View style={styles.seperator}/>}
       />
    );
}

const styles = StyleSheet.create({
    list :{
        flex:1
    },
    seperator:{
        backgroundColor:'#e0e0e0',
        height:1
    }
});

export default ToDoList;
```  

### 8) ToDoItem.js파일에서 삭제함수를 건네받도록 수정
```javascript
import React from 'react'
import {View, Text, StyleSheet, Image, TouchableOpacity} from 'react-native'

import Icon from 'react-native-vector-icons/MaterialIcons'

function ToDoItem({id, text, done, onToggle, onRemove}){
    return (
       <View style={styles.item}>
           <TouchableOpacity onPress={()=>onToggle(id)}>
            <View style={[styles.circle, done && styles.filled]}>
                {done && (<Image source={require('../assets/icons/check_white/check_white.png')}/>)}
            </View> 
            </TouchableOpacity>
            <Text style={[styles.text, done && styles.lineThrough]}>{text}</Text>
            {done?(
                <TouchableOpacity onPress={()=>onRemove(id)}>
                    <Icon name="delete" size={32} color="red"/>
                </TouchableOpacity>
            ):(
                <View style={styles.removePlaceholder}/>
            )}
       </View>
    );
}

const styles = StyleSheet.create({
    item:{
        flexDirection:"row",
        padding:16,
        borderBottomColor:"#e0e0e0",
        alignItems:'center'
    },
    circle:{
        width:24,
        height:24,
        borderRadius:12,
        borderColor:'#26a69a',
        borderWidth:1,
        marginRight:16
    },
    text:{
        flex:1,
        fontSize:16,
        color:'#212121'
    },
    filled:{
        justifyContent:'center',
        alignItems:'center',
        backgroundColor:'#26a69a'
    },
    lineThrough:{
        color:'#9e9e9e',
        textDecorationLine:'line-through'
    },
    removePlaceholder:{
        width:32,
        height:32,

    }
});

export default ToDoItem;
```  

### 9) 삭제하기 전에 대화상자를 출력해서 삭제 여부 확인하는 코드를 ToDoItem.js파일에 추가  
* GUI Programming에서 주의해야할 점이 하나 있는데, 화면을 출력하는 코드는 함수 내에서 가장 마지막에 수행됩니다.  
    하나의 함수 내에서 이루어지는 화면 출력 코드느 모아서 마지막에 한번만 수행합니다.  

    화면에 요소를 출력하는 코드에 이어서 다른 코드가 존재하는 경우 화면을 출력하는 코드 뒤에 있더라도 이 코드가 화면 출력코드보다 뒤에서 수행된다는 보장을 할 수가 없습니다. 대화상자를 출력하는 코드는 콜백 메서드를 가지고 있어서 대화 상자가 닫히고 난 후 수행된 코드를 작성할 수 있도록 합니다.

    하나의 함수 내에서 스레드나 운영체제 자체의 스레드와 유사한 객체를 이용하지 않고 단순하게 타이머의 형태로 GUI를 변경하는 코드를 작성하게 되면 에러가 발생하거나 모아서 한꺼번에 처리합니다.  

* 예를 들어,
```javascript
    for(int i = 0; i < 9; i++){
        sleep(1000);
        // 텍스트 뷰에 i 값 출력
    }
```  
라는 수도코드가 있을 때, 0 1 2 3 4 5 ... 이런 식으로 찍히길 바라지만 실제로는 9 만 찍힙니다.  

* ToDoItem.js 전체  
```javascript
import React from 'react'
import {View, Text, StyleSheet, Image, TouchableOpacity, Alert } from 'react-native'

import Icon from 'react-native-vector-icons/MaterialIcons'

function ToDoItem({id, text, done, onToggle, onRemove}){
    // 대화상자를 출력해서 삭제여부를 묻는 함수
    // AlertStatic.alert: (title: string,
    //                     message?: string, 
    //                     buttons?: AlertButton[],
    //                     options?: AlertOptions) => void
    const remove = ()=>{
        Alert.alert(
            '삭제', '정말로 삭제?', [
            {text:"취소", onPress:()=>{}, style:'cancel'},  // 취소 버튼 누르면 아무 일도 안함
            {text:"삭제", onPress:()=>{onRemove(id)}, style:'destructive'}],  // 삭제 버튼 눌렀을 때만 발동
            {cancelable:true, onDismiss:()=>{}}
        )
    }
    return (
       <View style={styles.item}>
           <TouchableOpacity onPress={()=>onToggle(id)}>
            <View style={[styles.circle, done && styles.filled]}>
                {done && (<Image source={require('../assets/icons/check_white/check_white.png')}/>)}
            </View> 
            </TouchableOpacity>
            <Text style={[styles.text, done && styles.lineThrough]}>{text}</Text>
            {done?(
                <TouchableOpacity onPress={()=>{remove}}>
                    <Icon name="delete" size={32} color="red"/>
                </TouchableOpacity>
            ):(
                <View style={styles.removePlaceholder}/>
            )}
       </View>
    );
}

const styles = StyleSheet.create({
    item:{
        flexDirection:"row",
        padding:16,
        borderBottomColor:"#e0e0e0",
        alignItems:'center'
    },
    circle:{
        width:24,
        height:24,
        borderRadius:12,
        borderColor:'#26a69a',
        borderWidth:1,
        marginRight:16
    },
    text:{
        flex:1,
        fontSize:16,
        color:'#212121'
    },
    filled:{
        justifyContent:'center',
        alignItems:'center',
        backgroundColor:'#26a69a'
    },
    lineThrough:{
        color:'#9e9e9e',
        textDecorationLine:'line-through'
    },
    removePlaceholder:{
        width:32,
        height:32,

    }
});

export default ToDoItem;
```  

## 12. 스마트 디바이스 앱에 데이터 저장  
### 1) Smart Device API
* Android : 환경 설정 파일, Flat File(파일 시스템에 직접 생성), SQLite 활용  
* iOS : 환경설정파일, Flat File, SQLite 활용, Core Data 활용  
* Flat File을 이용할 때 주의할 점은 스마트 디바이스에서는 읽기 전용 디렉토리와 읽고 쓰기가 가능한 디렉터리가 구분되어 있습니다. 스마트 디바이스의 네이티브 코드르 이용해서 저장하는 경우에는 각 디렉터리의 권한을 확인할 수 있어야 합니다.  
    
## 2) react-native
* AsyncStorage라는 API를 이용해서 앱 내에 데이터를 저장할 수 있도록 해줍니다.  iOS에서는 네이티브 코드로 만들어지고 안드로이드에서는 SQLite를 기반으로 구현되어 있습니다.  
* 저장 방식은 Key-Value 형식이고, getItem, setItem, removeItem, clear등의 함수가 구현되어 있습니다.  
* Promise를 리턴하는 방식으로 구현되어 있습니다.  
* 도큐먼트 : https://reactnative.dev/docs/asyncstorage
* 설치 
yarn add @react-native-community/async-storage
cd ios
pod install

### +) 데이터 활용
* 에플리케이션이 실행 중인 동안만 데이터를 활용 - 메모리에 저장하는 방식(variable을 이용하는 방식)  

* 애플리케이션에 데이터를 반영구적으로 저장  
  애플리케이션의 파일 시스템을 이용 - 안드로이드나 아이폰은 애플리케이션마다 documents라고 하는 디렉터리를 가지고 있어서 파일 입출력을 할 수 있도록 해줍니다.  

  애플리케이션의 환경 설정 파일을 이용 - 작은 양의 데이터만 보관  

  스마트폰 디바이스의 로컬 데이터베이스 이용(SQLite)

  iOS의 경우는 인 메모리 데이터베이스형태인 CoreData 이용  

* 다른 컴퓨터(서버)에 데이터를 보관  
애플리케이션에 데이터를 보관했을 때 문제점은 애플리케이션이 삭제되면 데이터도 같이 삭제됩니다.  
서버에 데이터를 보관하면 애플리케이션이 삭제되더라도 데이터를 복원할 수 있습니다.  
네트워크가 되지 않으면 서버의 데이터를 사용할 수 없습니다.  

* 애플리케이션의 성격에 따라 적절하게 혼합해서 사용해야 합니다.  
현재 네트워크의 상태를 확인해서 네트워크 상태가 불안정하면 애플리케이션 내의 데이터를 활용하는 방법을 고려할 만 하고, 애플리케이션 내의 데이터와 서버의 존재하는 데이터 사이의 불일치 문제를 해결해야 합니다.  

클라이언트 -------------- 애플리케이션 서버 ------------ 저장소  

일반적인 방식은 클라이언트가 애플리케이션 서버에게 요청을 하면 애플리케이션 서버는 저장소에 요청을 하교 저장소가 준 결과를 애플리케이션 서버가 받아서 클라이언트에게 전송  

저장소의 데이터를 미리 애플리케이션 서버에게 전달하고 클라이언트가 애플리케이션 서버에게 요청을 하면 그 때, 애플리케이션 서버의 데이터를 가지고 응답을 하고, 나ㅏ중에 저장소와 동기화를 수행하는 방식을 이용하면 저장소 활용 속도가 빨라지고 트래픽도 감소합니다.  

    데이터가 100개 <-------> 저장소에도 데이터가 100개  
데이터 1개가 삽입 - 데이터가 101개 <---------> 저장소에 데이터가 101개  

안드로이드(ArrayList - 데이터베이스에서 불러온 데이터 저장, 100)  

데이터베이스 100개  

### 3) AsyncStorage에 데이터를 읽고 쓰기위한 객체를 생성 - storage/todoStorage.js  
```javascript
import AsyncStorage from "@react-native-community/async-storage";

// 키 설정
const key = 'todos'

const todosStorage ={
    // 데이터 가져오는 함수
    async get(){
        try{
            // 데이터 가져오기
            const rawTodos = await AsyncStorage.getItem(key)
            // 데이터를 파싱해서 리턴
            const savedTodos = JSON.parse(rawTodos)
            return savedTodos
        }catch(e){
            throw new Error('Failed to load todos')
        }
    }, async set(data){
        try{
            await AsyncStorage.setItem(key, JSON.stringify(data))
        }catch(e){
            throw new Error('Failed to save todos')
        }
    }
}

export default todosStorage;
```  

### 4) App.js파일 수정  
```javascript
import React, {useState, useEffect} from 'react';
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
import ToDoList from './components/ToDoList';

import todosStorage from './storages/todoStorage';

function App(){
  // 오늘 날짜 생성
  const today = new Date();

  // 데이터를 저장하기 위한 속성 생성
  const [todos, setTodos] = useState([
    {id : 1, text:'작업 환경 설정', done:true},
    {id : 2, text:'BackEnd - Spring Boot', done:true},
    {id : 3, text:'FrontEnd - ReactNative', done:false}
  ])

  // 데이터 불러오기
  useEffect(()=>{
    // 데이터를 가져온 후 setTodos함수에 대입해서 수행
    todosStorage.get().then(setTodos).catch(console.error)
  }, [])

  // 데이터 저장하기
  useEffect(()=>{
    todosStorage.set(todos).catch(console.error)
  }, [todos])
  // 데이터를 삽입하기 위한 함수
  function onInsert(text){
    // 가장 큰 id를 찾아서 +1
    const nextId = todos.length > 0 ? Math.max(...todos.map(todo => todo.id)) + 1 : 1;
    // 하나의 인스턴스 생성
    const todo={id:nextId, text, done:false}
    // 배열에 추가한 후 배열을 todos에 대입, 둘 다 가능 
    //setTodos(todos.push(todo))
    setTodos(todos.concat(todo))
  }

// 뒤에 생략 ...
```  

### 5) 테스트  
애플리케이션을 실행해서 데이터를 추가하거나 삭제한 후 에뮬레이터나 시뮬레이터를 종료  
앱을 다시 빌드했을 때 마지막에 수행한 데이터가 다시 출력되는지 확인 - 앱 내에 저장하므로 앱을 삭제하고 다시 시작하지 않는 이상 항상 데이터가 유지됩니다.