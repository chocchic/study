# Spring Boot  
## 1. Spring Boot  
    * 단독 실행되는 상용화가능한 수준의 스프링 기반 애플리케이션을 쉽게 만들어낼 수 있으며 최소한의 설정으로 스프링 플랫폼과 3rd Party 라이브러리를 사용할 수 있도록 해주는 프레임워크  

### 1) 장점  
    * 환경설정을 최소화해서 개발자가 Business Logic에 집중할 수 있도록 도와줘 생산성을 향상  
    * WAS를 내장해서 독립 실행이 가능한 스프링 애플리케이션 개발이 가능  
    * XML 설정 없이 자바 수준의 설정 방식 제공  
    * JAR을 사용하여 자바 옵션만으로 배포 가능  
    * 모니터링과 관리를 위한 Apring Autuator 제공  

### 2) 단점  
    * 설정을 자동화하지 않는다면 이전 방식과 동일  
    * 많은 설정이 자동으로 이루어지기 때문에 설정 자체를 변경하고자 하는 경우 내부의 설정 코드를 이해해야 함  

## 2. Spring Boot Starter  
    * 의존 관계를 개발자가 간편하게 설정할 수 있도록 도와주는 의존성 그룹  
    * 프로젝트를 생성할 때 체크를 해도 되고 필요하면 pom.xml이나 build.gradle파일에 추가하면 됨.  
    * 기본적으로 설정된 의존성  
        - spring-boot  
        - spring-boot-autoconfigure  
        - spring-boot-logging  
        - spring-core  
        - javax.annotation.api  
        - snakeyaml : yaml(사람이 쉽게 읽을 수 있는 데이터 직렬화 양식)을 사용하는데 필요한 의존성  

    * 도큐먼트 : https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/  

    * +) java 코딩할 때 가장 먼저 할 일은 Java, JavaWeb, Spring의 Document를 펼치는 것입니다.  

## 3. 개발 환경 설정  
### 1) JDK 1.8 이상의 버전을 설치  
### 2) IDE를 설치  
    * Eclipse에서는 plugin을 설치해서 수행  
        sts나 전자정부 프레임워크, any framework같은 경우는 이미 plugin이 설치되어 있습니다.  

    * Intelli J : 코딩이 편리한데, 웹 프로그래밍을 하려면 유료버전을 사용해야 함.  

### 3) 사용할 데이터베이스 서버와 접속도구를 준비  

## 4. STS에서 Spring Boot Application을 생성  
### 1) (File) - (New) - (Spring Starter Project)  

### 2) 옵션 설정을 하고 프로젝트를 생성  
    * start.spring.io를 브라우저에서 입력하고 들어가서 웹에서 생성 가능  