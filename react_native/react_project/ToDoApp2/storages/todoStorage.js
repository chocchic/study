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