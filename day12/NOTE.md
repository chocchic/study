1. 컬렉션 Collection
	자료구조 과목에서 배우는 많은 자료구조들을 컬렉션으로 만들어 제공  
	제네릭이라는 기법으로 구현  
	가변 크기의 컨테이너로 만들어짐  

	1) 컬렉션을 위한 자바 인터페이스와 클래스
	   ![collection](./collection.jpg)
	[ Collection 상속, 단일 클래스 객체]
	- Vector, ArrayList : 가변 크기의 배열
	- LinkedList : 노드들이 링크로 연결되는 리스트
	- Stack : 스택
	- HashSet : 집합
	[ Map 상속 ]
	- HashMap :  키와 값의 쌍으로 이루어진 값을 저장하는 컬렉션
	
	2) 제네릭(generic) 기법
		컬렉션은 제네릭 기법으로 만들어짐
		컬렉션 클래스 이름에 <E>,<K>,<V> 등이 포함 -> 타입 매개변수(generic type)
		Vector<Integer> --> 정수만 저장하는 벡터
		Vector<String> --> 문자열만 저장하는 벡터
		특정타입만 다루지 않고 여러 종류의 타입으로 변신할 수 있도록 <E>를 사용  
		단, int, char, double등 기본타입으로 제네릭 사용 불가. -> Wrapper클래스로 지정  
		
		ex) 제네릭 타입을 가진 클래스  
		```java
		class Stack<E> {
			...
			void push(E element) { .... }
			E pop() { ... }
			...
		}
		
		Stack<String> s = new Stack<String>(); 
		
			void push(String element) { ... }
			String pop() { ... }
		```  
		E : element (요소) 	T : type V : value K : key  

2. ArrayList<E> : java.util.ArrayList
		가변크기의 배열을 구현, Vector와 거의 동일  
		스레드간에 동기화 지원 X, 다수의 스레드가 동시에 ArrayList 요소 삽입/삭제시 데이터 훼손이 우려되지만, 멀티 스레드 동기화 시간 소모없어 Vector보다 속도가 빠름.  
		중간에 빈공간이 생기면 요소 위치 자동 이동.  
		초기 사이즈 10이지만 부족하면 자동 증가  
	
		1) 주요 메서드  
			boolean add(E element) : 맨 뒤에 element 추가  
			void add(int idx, E element) : idx위치에 element 추가  
			boolean addAll(Collection <? extends E> c) : 컬렉션 c의 모든 요소를 맨 뒤에 추가  
			E get(int idx) : idx위치의 요소 리턴  
			void clear() : 모든 요소 삭제  
			E remove(in idx) : idx위치 요소 삭제하며 리턴  
			boolean remove(Object o) : o와 동일한 요소 삭제  
			int size() : 저장된 요소의 개수 리턴  
			boolean contains(Object o) : o를 포함하고 있으면 true리턴  
			int indexOf(Object o) : o가 저장된 인덱스번호 리턴, 없으면 -1  
			boolean isEmpty() : 저장된 요소가 없으면 true  
			Object[] toArray() : 모든 요소를 포함한 배열 리턴  
		2) 객체 생성  
			ArrayList<타입 매개변수> 변수명 = new ArrayList<타입 매개변수>();  
3. Vector : java.util.Vector  
	가변적 배열 구조, 동기화 지원  

	1) 객체 생성  
		Vector<타입매개변수> 변수명 = new Vector<타입매개변수>();  
	2) 주요 메서드  
		boolean add(E element)  : 맨 뒤에 추가  
		void add(int idx, E element) : idx위치에 element추가  
		E get(int idx)  
		E elementAt(int idx)  
		void clear()  
		E remove(int idx)  
		boolean remove(Object o)  
		E set(int idx, E element)  
		int size() 저장된 요소의 개수 리턴  
		int capacity() : 저장 공간 크기 리턴  
		Object[] toArray() : 배열로 만들어 리턴  
		int indexOf(Object o) : o의 인덱스 버호 리턴  
		boolean isEmpty() : 저장된 요소가 하나도 없으면 true  

4. 제네릭 없이 사용  
	제네릭 없이 사용시 Object로 처리  
	하지만 꺼냈을 때도 Object로 돌려주므로 내용물을 온전히 잘 사용하려면, 맞는 타입으로 형변환해 담아서 사용해야 함.  

5. Iterator<E> : java.util.Iterator  
	요소가 순서대로 저장된 컬렉션에서 요소를 순차적으로 검색할 떄 사용하면 편함  

	1) 주요 메서드  
		boolean hasNext() : 다음에 방문할 요소가 있으면 true  
		E next() : 다음 요소 리턴  
		void remove() : 마지막으로 리턴된 요소 제거  
	
6. HashMap<K, V> : java.util.HashMap  
	키(key)와 값(value)의 쌍으로 구성되는 요소들을 다룬다  
	K = key로 사용할 데이터 타입  
	V = 값으로 사용할 데이터 타입  
	데이터 저장시, key와 값을 받아 key를 이용하여 해시함수를 실행, 해시함수가 리턴하는 위치에 키와 값을 저장하는 형태  
	반대로 데이터를 불러올 때 키를 이용하여 꺼내오는데, 키를 이용하여 동일한 해시함수를 실행하여 값이 저장된 위치를 알아내어 값을 리턴  
	-> index가 없다 => key로 꺼냄(사용자 정의 인덱스 값)  
	-> index 중복 X => key값은 중복되면 X. 유일해야 함  

	1) 객체 생성  
	HashMap<key제네릭타입, value제네릭타입> 변수명 = new HashMap<key제네릭, value제네릭>();  
	
	2) 주요 메서드  
	V put(K key, V value) : key value 쌍으로 요소 하나 추가  
	V get(K Key) : key를 주고 값 꺼내기  
	V remove(K key) : key에 해당하는 key,value 세트 삭제  
	int size() : 저장된 요소의 개수  
	void clear() : 전체 삭제  
	Set<K> keySet() : 키들만 모아서 Set타입으로 리턴  
	boolean isEmpty() : 저장된게 없으면 true  
	boolean containsKey(Object key) : key를 포함하면 true  
	boolean containsValue(Object value) : value를 포함하면 true  

7. Set<E> -> HashSet<E>  
	순서가 없고, 중복 요소가 없는 컬렉션. 동일한 값 저장 불가  

8. Properties  
	K, V 형태로 데이터 저장  
	주로 어플리케이션의 환경 설저오가 관련된 정보를 .properties 확장자의 파일에 저장해놓고 해당 파일을 자바 코드로 불러올 때 사용하는 자바 클래스.  
