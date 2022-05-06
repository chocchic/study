# node _ express

## 1. 에러 처리
  1) 에러 발생 즉시 처리  
    res.status(상태 코드).수행할 코드  
      : 수행할 코드 부분은 매개변수가 4개가 되는데 error 객체, req 객체, next  
  
  2) 에러 별도 처리  
  ```javascript
    app.use((err,req,res, next) =>  
      에러 처리 코드
    }
  ```

## 2. req 객체 - request(클라이언트의 정보를 저장한 요청) 객체  
  * req.app : app 객체에 대한 참조  
  * req.body : body-parser가 만드는 요청의 내용을 해석한 객채(post 방식으로 전송된 데이터 중에서 file을 제외한 데이터 해석에 사용)  
  * req.params : 라우트 매개변수에 대한 정보가 저장된 객체(URL의 일부분을 파라미터로 사용하고자 하는 경우 사용)  
  * req.query : query string 해석에 사용(get 방식으로 전송된 데이터를 해석)
