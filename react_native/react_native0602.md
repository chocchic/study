# 스마트 디바이스 애플리케이션  
## 1. Native App을 SDK를 이용해서 직접 개발  
* Android : Android Studio를 설치해서 Java나 Kotlin 언어를 이용해서 Apllication 개발  
C++는 사용 가능(NDK 설치)  
Google은 더이상 Java의 형태로 API를 제공하지 않겠다고 선언  

Kotlin은 JVM 기반의 언어입니다.
Kotlin코드는 Java의 Class로 번역이 되서 실행됩니다.  

Kotlin이 가독성이 뛰어나고 함수형 프로그래밍 문법을 지원하기 때문에 최근의 기업들은 Kotlin으로 만드는 것을 권장  

* iOS(이제는 Mac용도 동일한 방법으로 생성) - Xcode를 설치해서 Objective-C(C++ 문법을 지원)나 Swift언어를 이용해서 Application 개발  

* 장점  
최신의 API(모든 센서 포함)를 전부 사용하는 것이 가능  
빌드 속도가 빠름   

* 단점  
    동일한 Application을 서로 다른 운영체제에서 개발을 할 때 Learning Curve가 길 가능성이 높습니다.(최근에는 Kotlin이나 Swift를 사용하는데 2개 언어의 문법은 python과 거의 유사)  
    모바일 웹 앱을 만드는 것보다 업데이트 속도가 느릴 수 있음 - 업데이트를 할 때 소스코드를 수정하거나 Resource를 수정하게 되면 앱을 다시 빌드해서 마켓에서 승인을 받아야하기 때문입니다.  
## +)
멀티 태스크를 지원하는 네트워크에 강력한 운영체제 개발 프로젝트 - UNIX - C언어 등장  
UNIX의 95% 이상이 C언어로 개발  

UNIX는 특정 벤더 별로 따로 제작  

Open Source 형태의 운영체제 개발 - LINUX - C언어로 개발  

PC나 Smart Phone 제작 회사에서는 이 운영체제를 직접 사용하는 것이 부담이 됨  
권한 문제, 접근성  

## 2. 모바일 웹 어플리케이션을 이용해서 개발
* 모바일 웹 어플리케이션을 만들고 스마트 디바이스의 브라우저에서 접속을 하거나 스마트 디바이스의 WebView를 이용해서 접속해서 웹 애플리케이션을 사용하는 방식  
* 여기서 등장한 개념 중의 하나가 Progressive Web(UI를 사용자가 사용하기 쉽도록 많이 사용하는 디바이스 기준에 맞추는 것 - 스마트 폰)의 개념  

* 장점
    대부분의 개발자가 알고 있는 웹 애플리케이션 개발을 수행하는 거라서 Learning Curve가 짧다.  
    업데이트를 했을 때 반영이 빠름  

* 단점  
    스마트폰 API(센서의 경우는 회전과 터치 정도만 사용가능, Web Socket은 사용 불가능)의 대부분을 사용하기가 어렵습니다.  
    현재는 Apple과 Google 모두 WebView로만 구성된 애플리케이션은 마켓에서 reject됩니다.  

## 3. 스마트 디바이스 회사가 아닌 곳에서 제공하는 프레임워크 이용  
* react-native, ionic : 웹 기술을 이용해서 애플리케이션을 제작하는 방식으로 프레임워크가 Native App의 코드를 변경하는 방식  

* cordova : 웹 기술을 이용해서 애플리케이션을 제작하는 방식인데ㅡ 프레임워크가 Web View를 만들어서 그 위에 HTML페이지를 출력하는 방식  

* xamarin, flutter : xamarin의 경우는 MS에서 제작한 프레임워크로 C#을이용해서 애플리케이션을 제작, flutter는 google이 제작한 프레임워크로 Dart언어를 이용해서 애플리케이션을 제작  

* unity 3D, unreal : 3차원 게임 엔진을 가진 프레임워크, Unity는 C#을 기반으로 하고 unreal은 C++를 기반으로 합니다. 게임이나 아바타 등을 이용하는 애플리케이션 제작에 많이 이용  

* 장점
    iOS와 Android앱을 동시에 개발하는 것이 가능  
    스마트 디바이스의 API도 어느정도 사용이 가능  
* 단점
    프레임워크 자체의 업데이트 주기가 짧음  
    최신의 API를 사용하지 못하는 경우도 있음  
    스마트 디바이스 제조회사에서 reject를 시키는 경우가 있음  
    모든 API를 사용할 수 없기 때문에 Native Application의 API 사용법을 어느 정도는 숙지해야 합니다.  

# Android Studio를 이용한 애플리케이션 개발  
* react-native나 ionic같은 별ㄹ도의 프레임워크를 이용해서 개발을 하더라도 출시할 때는 Android Studio나 Xcode에서 빌드를 해야하고, 시뮬레이터에서 실행을 하고자할 때도 Android Studio나 Xcode가 설치되어 있어야 합니다.  

## 1. Android 개발환경 설정  
### 1) JDK 설치  
* 11버전 이상 권장  
* 확인 - 터미널에서 수행
JRE(자바 실행 환경 - 자밥로 만들어진 애플리케이션을 실행할 때 필수, JavaVirtualMachine이라고 하는 사람도 있음) 버전 확인 : java -version
JDK(자바 개발 도구 - 자바로 애플리케이션을 개발할 때 필수, Java Application Programming Interface 또는 Java SoftwareDevelopmentKit라고 하기도 합니다.) 버전 확인 : javac -version  

* javac -version은 JDK가 설치되어 있어도 명령이 제대로 실행되지 않을 수도 있음 - 환경변수 설정을 하지 않아서 임.  
    Mac은 할 필요가 없고, Windows에서만 수행하면 됩니다.  

JAVA_HOME : jdk 경로 등록  
path : jdk경로의 bin 디렉터리 등록  

### 2) Android Studio 설치  
* developers.android.com에서 자신의 운영체제에 맞는 버전을 다운로드 받아서 설치  
* 설치 후 Application을 하나 생성한 후 [Tools] - [SDK Manager]를 실행해서 필요한 운영체제 버전의 개발도구를 설치  
react-native 예전 버전은 29버전을 사용했고, 오늘은 31버전을 사용  
31버전과 29버전을 설치  
설치 시간이 조금 걸립니다.  

* 환경 변수 설정 : react-native를 사용하지 않는 경우는 할 필요 없음  
windows : ANDROID_HOME - %LOCALAAPPDATA%\Android\Sdk를 등록

Mac : vim ~/.zshrc를 터미널에서 실행해서 추가 -i를 눌러서 편집모드로 변경한 후 작성  
exprot ANDROID_HOME=$  
exprot PATH=$PATH:$ANDROID_HOME/emulator  
exprot PATH=$PATH:$ANDROID_HOME/tools  
exprot PATH=$PATH:$ANDROID_HOME/tools/bin  
exprot PATH=$PATH:$ANDROID_HOME/platform-tools  

내용을 추가하고 esc를 눌러서 명령모드로 나와서 :w!를 눌러서 저장하고 빠져나옴  
source ~/.zshrc를 실행해서 적용합니다.  

* Emulator 생성 - (Tools) - (Device Manager) 메뉴에서 생성  
현재 Android Studio는 Emulator가 독자적으로 실행되지 않고 Android Studio안에서 실행되므로 (File) - (Settings)메뉴에서 외부에서 실행되도록 설정  
Mac의 경우는 안드로이드 스튜디오 첫번째 메뉴에서 Preferences 메뉴를 실행해야 합니다.  
react-native에서 Android Application을 실행할 때는 Android는 에뮬레이터가 실행중이어야 합니다.  

에뮬레이터가 생성되지 않는 경우는 작업관리자에서 [성능] - [CPU]에서 가상화가 사용안함으로 되어있는지 확인하고 사용안함으로 되어있는지 확인하고, 사용 안함으로 되어있는경우는 CMOS(BIOS)에서 가상화 사용 설정을 해야합니다.  
CMOS 설정은 컴퓨터를 부팅할 때 기종에 따라서 다른데, F2나 Delete, F12등을 눌러서 들어가야 합니다.  

## 2. Android Application 구조
### 1) Gradle Scripts - 앱의 라이브러리 의존성과 SDK 버전 설정  
* Project(1개 이상의 Application으로 구성된 디렉토리의 개념) 수준의 build.gradle  
* Module(Application) 수준의 build.gradle  
이 디렉터리의 내용은 실행중에는 변경이 안됩니다.  

### 2) manifest/AndroidManifest.xml  
권한 설정과 컴포넌트 등록ㅇ르 하고 스타일을 설정하는 파일  
이 디렉터리의 내용은 실행중에는 변경이 안됩니다.  

### java 디렉터리  
source 파일이 존재하는 디렉터리  
이 디렉터리의 내용은 실행중에는 변경이 안됩니다.  

### 4) res 디렉터리  
source 파일을 제외한 파일이 존재해야하는 디렉터리  
이 디렉터리의 내용은 실행중에는 변경이 안됩니다.  
다시 디렉터리가 구분되어 있는데, 각 디렉터리의 이름과 용도는 결정되어 있습니다.  
이 디렉터리의 내용은 정수로 등록을 하게 되는데, 이 때 파일의 이름이 final 상수의 이름이 되기 때문에 대문자나 특수문자 또는 한글을 사용할 수 없습니다.  

## 3. 작업 방법  
화면 디자인은 res/layout 디렉터리의 xml파일을 이용해서 정적으로 생성하고 java코드에서 동적으로 생성하는 것이 가능  
layout 파일에 만든 view를 java코드에서 사용하기 위해서는 반드시 id가 있어야 합니다.  
이 id를 이용해서 java코드에서 사용을 합니다.  

* 동적인 코드 작성은 java 디렉터리에서 합니다.  
언어는 java와 kotlin 모두 가능한데, 애플리케이션을 생성할 때 언어를 설정합니다.  
코드를 복사붙여넣기 하면 변환해줄 것인지 묻는 메뉴가 보입니다.  

```java
    Strint str = new String("Hello Kotlin");
```
이라는 코드를 치면 확인 후 Kotlin방식으로 바꿔주지만 Buffer를 열어서 해주어야 합니다.  
```kotlin
    val str : String = String(StringBuffer("Hello Kotlin"))
```  

## 4. Version Control - 소스 코드 버전 관리  
환경 설정 메뉴에서 Version Control에서 계정을 등록하면 쉽게 가능  
주의할 점은 계쩡을 등록할 때 비밀번호는 Git Hub의 비밀번호가 아니고 Token 번호입니다.  

## 5. Application을 생성하고 실행  
### 1) Application 생성  
첫 화면에서 new Project를 실행하던가 (File) - ( 빼먹음)
첫 번째 화면을 설정 - empty activity 선택  
애플리케이션 이름과 옵션을 설정  

### 2) res/layout 디렉터리의 activity_main.xml파일을 열어서 수정 - 코드 창ㅇ ㅣ안보이면 code버튼을 눌러야함  
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    android:orientation="vertical">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        android:id="@+id/txtView" />
    <Button
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="버튼"
        android:id="@+id/btn"
    />
</LinearLayout>
```

### 3) java디렉터리에 MainActivity.kt 파일을 열어서 코드를 작성  
```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰를 찾아오기
        val txtView : TextView = findViewById(R.id.txtView)
        val btn : Button = findViewById(R.id.btn)

        btn.setOnClickListener(View.OnClickListener {
            txtView.setText("버튼을 눌렀습니다.")
        })
    }
}
```

### 4) 실행한 후 버튼을 눌러서 텍스트가 변경되는지 확인  

## 6. react_native  
* Javascript와 React라이브러리를 이용해서 스마트 디바이스의 Native Application을 개발할 수 있게 해주는 프레임워크  
* 하나의 코드로 Android와 iOS애플리케이션을 개발할 수 있습니다.  
* Javasscript 버전은 ECMAScript 2015(ES6)를 사용  
* Node.js기반으로 npm 사용  
* 장점은 빠른 개발 속도 단점은 Xcode나 Android Studio에 대한 지식이 있어야 하고 Native언어에 대한 지식도 있어야 함  
* 자바스크립트 코드가 Native언어로 번역이 된다고 생각하는 경우가 많은데 실제로는 JavascriptCore라는 자바스크립트 엔진이 앺에 포함되어 자바스크립트로 작성한 코드를 앱 내에서 실행해 주는 형태로 동작합니다.  

## 7. 개발 환경  
### 1) node.js 설치  

### 2) react native cli 설치  
npm i -g react-native-cli  

### 3) 실행의 편리성을 위해서 yarn 설치  
npm install --global yarn  

### 4) IDE(VScode) 설치  

### 5) Andorid Studio 설치  
* java도 설치되어야 하고, 환경변수도 설정해야 함  

### 6) Xcode 설치  
Mac에서는 Xcode이 외에 cocoa pods(Xcode의 의존성 관리자)도 설치되어야 함  