//암호화 모듈 사용
const crypto = require('crypto')
//양방향 암호화
const algorithm = 'aes-256-cbc'
//key는 32바이트
const key = '12345678901234567890123456789012'
//iv는 16바이트
const iv = '1234567890123456'

const cipher = crypto.createCipheriv(algorithm, key, iv)

let r1 = cipher.update('문장', 'utf8', 'base64')
r1 = r1 + cipher.final('base64')
console.log("r1:" + r1);

//r1을 이용해서 원래 문장 복원
const decipher = crypto.createDecipheriv(algorithm, key, iv)
let r2 = decipher.update(r1, 'base64', 'utf8')
r2 = r2 + decipher.final('utf8')
console.log('r2:' + r2)