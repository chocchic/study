// 문제 - url에서 query부분만 추출해서 자바스크립트 객체로 변환
addr = 'https://cyberadam.cafe24.com/item/list/detail?itemid=1'
// 문자열로 만들어진 url을 각각의 부분으로 분할 : url 모듈의 parse라는 함수
const url = require('url')
parsedAddr = url.parse(addr)
// 분할된 url의 query라는 속성을 호출하면 query부분만 별도의 문자열로 추출
console.log(parsedAddr.query)
// queryString 문자열을 자바스크립트 객체로 변환해주는 함수는 queryStirng 모듈의 parse라는 함수입니다
const querystirng = require('querystring')
const param=querystirng.parse(parsedAddr.query)
console.log(param.itemid)

// 암호화 모듈 사용

// 이 방식으로 암호화를 하게 되면 수많은 횟수를 반복해서 문자열을 대입하면 원본을 찾아낼 수 있을지도 모름
// 이때 사용하는 테이블을 레인보우 테이블이라고 합니다.
const crypto = require('crypto')

console.log(crypto.createHash('sha512').update('문장').digest('base64'))

// 랜덤한 숫자를 적용해서 위의 작업을 수만번 반복해서 암호화
crypto.randomBytes(64, (err, buf) => {
    const salt = buf.toString('base64')
    crypto.pbkdf2('문장', salt, 1000000, 64, 'sha512',
    (err, key) => {
        console.log(key.toString('base64'))
    })


//DB에 저장할 때는 salt와 key를 같이 저장해야합니다.

// 암호화하기위해서 사용했던 평문('문장')과 암호화된 문장이 동일한 문장인지 확인하기
    crypto.pbkdf2('문장', salt, 100000, 64, 'sha512', 
    (err, key) => {
        console.log(key.toString('base64') === '문장') // 같은 pbkdf2가 아니므로 다른 결과가 나와서 false
    })

})

var salt;
var pw='문장';
var pw1;
crypto.randomBytes(64, (err, buf) => {
    const salt = buf.toString('base64')
    crypto.pbkdf2('문장', salt, 1000000, 64, 'sha512',
        (err, key) => {
            pw1 = key.toString('base64')

            console.log('원래 비밀번호 : ' + pw);
            console.log('salt : ' + salt);
            console.log('변경된 비밀번호 : ' + pw1);
            // DB에 저장 : salt와 변경된 pw
    })
});

console.log('내가 먼저 될지도 몰라~' + pw1) // pw1이 undefined가 나옴
// 비밀번호를 입력받은 후
// crypto.pbkdf2('입력받은 비밀번호', 'DB에서 읽은 salt', 100000, 
//     (err, key) => {
//         if(key.toString('base64') === pw1){
//             console.log('비밀번호 일치')
//         }else{
//             console.log('비밀번호 불일치')
//         }
//     })


// 양방향 암호화
const algorithm = 'aes-256-cbc'
// key는 32바이트
const key = '12345678901234567890123456789012'
        // iv는 16바이트
const iv = '1234567890123456'

const cipher = crypto.createCipheriv(algorithm, key,iv);

let r1 = cipher.update('문장', 'utf8', 'base64')
r1 = r1 + cipher.final('base64')
console.log("r1 : " + r1)

// r1을 이용해서 원래 문장 복원
const decipher = crypto.createDecipheriv(algorithm, key,iv);
let r2 = decipher.update(r1, 'base64', 'utf8')
r2 = r2 + decipher.final('utf8')
console.log("r2 : " + r2)