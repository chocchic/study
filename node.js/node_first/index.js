//console.log('Hello Node.js')

// 현재 디렉토리의 var.js파일에서 exports된 데이터를 odd와 even에 할당
const {odd, even} = require('./var')

console.log(odd)
console.log(even)

const f = require('./globalA')
f() // globalA에서 exports 해준 함수를 실행

// 다양한 방법으로 로그 출력
// 색상을 설정해서 보통 log는 검은색, error는 빨강색으로 출력해서 구분
console.log(global.message); 
console.error(global.message);
// 로그가 표로 출력
console.table([global.message]);

console.time('시간 측정');
for(var i = 0; i < 100000000; i+=1){ }
    console.timeEnd('시간 측정');

// 1000밀리초 = 1초 마다 hi출력
//var i = 0;
/*
setInterval(()=>{
    if(i%2==0){console.log('Hi')}
    else{console.log('Hello')}
    i++;
}, 1000)
*/
/*
//var ar = ['Hello','Hi'];
var ar = ['A','B','C','D','E']
setInterval(()=>{console.log(ar[i++%ar.length])}, 1000)
*/

// 프로세스 정보
console.log(process.cpuUsage())
console.log(process.platform)

// 운영체제 정보 - CPU 코어 정보 확인
var os = require('os')
console.log("core 개수 : ", os.cpus().length);
console.log(os.cpus())

const path = require('path')
console.log('dir 기호 : ' + path.sep)
// 현재 디렉토리에 있는 index.html 파일의 경로 생성
console.log(path.join(__dirname, 'index.html'))

//URL 분해
const url = require('url')
const {URL} = url
const parsedURL = url.parse('https://cyberadam.cafe24.com/item/detail?id=1')
console.log(parsedURL)

const myURL = new URL('https://cyberadam.cafe24.com/item/detail?id=1&pw=2')
//queryString의 이름 부분만 확인
//console.log(myURL.searchParams.keys())
// iterator는 next라는 함수로 다음 데이터에 접근
var iter = myURL.searchParams.keys()
console.log(iter.next())
console.log(iter.next())

