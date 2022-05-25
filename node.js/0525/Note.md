## BackEnd와 FrontEnd의 분리 개발  
CORS의 개념 - 서버 개발자에게 필수  
SPA의 개념 - FrontEnd개발자에게 필수이고 웹에서는 주로 React.js나 Vue.js로 구현  

node.js를 학습할 때는 일반적으로 JavaScript(ES6-ECMA2015, 객체 지향 프로그래밍의 개념이 있지만 일반적으로 함수적 프로그래밍의 특성이 강함)만 학습하면 되는데, react.js나 react-native.js를 학습할 때는 TypeScript(객체 지향적인 JavaScript)까지 학습해야합니다.  

## TypeScript 문법에 대해
JavaScript와 Python의 객체는 클래스와 상관없이 확장이 가능합니다.

* Java
```java
class T{
    public int n;
}
T obj = new T();
obj.n = 10;
print(obj.n);
print(obj.k); // 에러
```  

* JavaScript나 Python
```javascript
class T{
    public int n;
}
T obj = new T();
obj.n = 10;
print(obj.n);
print(obj.k); // javascript는 undefinde, python은 에러
obj.k = 12; // javascript나 python은 obj객체에 k라는 속성을 추가해줌
```


