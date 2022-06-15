import auth from '@react-native-firebase/auth'

//로그인 처리
export function signIn({email, password}){
    return auth().signInWithEmailAndPassword(email, password);
}

//회원 가입
export function signUp({email, password}){
    return auth().createUserWithEmailAndPassword(email, password);
}

//앱을 동작시킬 때 그리고 로그인 상태가 변경될 때 호출되는 함수
export function subscribeAuth(callback){
    return auth().onAuthStateChanged(callback);
}

//로그아웃 처리
export function signOut(){
    return auth().signOut();
}
