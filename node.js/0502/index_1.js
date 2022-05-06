//문제 - url에서 query 부분만 추출해서 자바스크립트 객체로 변환
addr = 'https://cyberadam.cafe24.com/item/detail?itemid=1'

//문자열로 만들어진 URL을 각각의 부분으로 분할
//url 모듈의 parse 라는 함수
const url = require('url')
const parsedUrl = url.parse(addr)

//분할된 url 의 query라는 속성을 호출하면 query 부분만 별도의 문자열로 추출
console.log(parsedUrl.query)

//queryString 문자열을 자바스크립트 객체로 변환해주는 함수는
//querystring 모듈의 parse 라는 함수
const querystring = require('querystring')
const param = querystring.parse(parsedUrl.query)
console.log(param.itemid)

//암호화 모듈 사용
const crypto = require('crypto')

//이 방식으로 암호화를 하게되면 수많은 횟수를 반복해서
//문자열을 대입하면 원본을 찾아 낼 수 있을지도 모름
//이 때 사용하는 테이블을 레인보우 테이블이라고 합니다.
console.log(
    crypto.createHash('sha512').update('문장').digest('base64'))

//랜덤한 숫자를 적용해서 위의 작업을 수만번 반복해서 암호화

var salt;
var pw = '문장';
var pw1
crypto.randomBytes(64, (err, buf) => {
    salt = buf.toString('base64')
    crypto.pbkdf2(pw, salt, 1000000, 64, 'sha512', 
        (err, key) => {
            pw1 = key.toString('base64')

            console.log('원래 비밀번호:' + pw);
            console.log('salt:' + salt);
            console.log('변경된 비밀번호:' + pw1);
            //데이터베이스에 저장: salt 와 변경된 비밀번호
        })
});

