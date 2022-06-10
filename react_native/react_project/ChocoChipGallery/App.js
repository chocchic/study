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