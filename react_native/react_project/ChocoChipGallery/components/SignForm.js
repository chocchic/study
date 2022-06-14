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
        </>
    )
}

export default SignForm