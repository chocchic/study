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

/*
import type {Node} from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';

import {
  Colors,
  DebugInstructions,
  Header,
  LearnMoreLinks,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';

const Section = ({children, title}): Node => {
  const isDarkMode = useColorScheme() === 'dark';
  return (
    <View style={styles.sectionContainer}>
      <Text
        style={[
          styles.sectionTitle,
          {
            color: isDarkMode ? Colors.white : Colors.black,
          },
        ]}>
        {title}
      </Text>
      <Text
        style={[
          styles.sectionDescription,
          {
            color: isDarkMode ? Colors.light : Colors.dark,
          },
        ]}>
          <View>
            <Text style={{fontStyle:"italic"}}>React-Native로 만든 앱</Text>
          </View>
          <View>
            <Image style={{width:100, height:100}}
            source={require('./myhero.jpeg')}/>
          </View>
        {children}
      </Text>
    </View>
  );
};

const App: () => Node = () => {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        style={backgroundStyle}>
        <Header />
        <View
          style={{
            backgroundColor: isDarkMode ? Colors.black : Colors.white,
          }}>
          <Section title="Step One">
            Edit <Text style={styles.highlight}>App.js</Text> to change this
            screen and then come back to see your edits.
          </Section>
          <Section title="See Your Changes">
            <ReloadInstructions />
          </Section>
          <Section title="Debug">
            <DebugInstructions />
          </Section>
          <Section title="Learn More">
            Read the docs to discover what to do next:
          </Section>
          <LearnMoreLinks />
        </View>
      </ScrollView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});
*/
export default App;
