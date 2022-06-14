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