//util 이라는 모듈을 사용
//const는 변경 불가능한(immutable)  이름을 생성
//var 나 let은 변경 가능한(mutable) 이름을 생성
const util = require('util')

//함수에 경고 메시지를 주는 경우는 일반적으로 2가지인데
//한 가지 경우는 보안상의 허점이 발견된 경우 와
//가독성이나 성능이 좋은 다른 함수를 만들었을 때
const dontUseMe = util.deprecate((x, y) => {
    console.log(x + y)
}, '이 함수는 deprecated 입니다.')

dontUseMe(10, 20)

//crypto 모듈의 randomBytes 라는 함수를 이용
const crypto = require('crypto')

const rbPromise = util.promisify(crypto.randomBytes)

//crypto.randomBytes 함수에 64를 넘겨서 작업을 수행하고 작업을 성공하면
//함수가 리턴한 값을 buf에 대입한 후 코드 블럭을 수행하고 
//crypto.randomBytes 작업 도중 예외가 발생하면 catch 블럭에 에러 내용을 
//err에 대입해서 작업을 수행
rbPromise(64).then((buf) => {
    console.log(buf.toString('base64'))
}).catch((err)=>{
    console.error(err)
})

//test.txt 파일 읽기
const fs = require('fs')

//비동기적으로 파일 읽기
fs.readFile('./test.txt', (err, data) => {
    console.log(data)
    console.log("이 내용이 나중에 수행됨");
})

//동기적으로 파일 읽기 - 파일의 내용이 많을 때 이 방법은 위험
let data = fs.readFileSync('./test.txt')
console.log(data)
console.log('파일의 내용을 읽고 난 후 실행')

//일반적으로 파일 입출력 과 네트워크 입출력은 대부분 비동기 방식으로 구현