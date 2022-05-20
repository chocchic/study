import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// 데이터를 다론 곳으로 전송할 목적으로 만드는 클래스는 반드시 Serializable 인터페이스를 implements해야 합니다.
class Data implements Serializable{ // Serializable해주지 않으면 NotSerializableException이 뜬다
	public String name;
	public int num;
}

public class Main {
	public static void main(String[] args) {
		Data d = new Data();
		d.name="choc";
		d.num = 10;
		//String name = "choc";
		//int num = 10;
		
		// �쐞�쓽 �궡�슜�쓣 �뙆�씪�뿉 湲곕줉
		//try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("./file.data"))){
		// 객체 단위로 기록하는 스트림 ObjectOutputStream
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./data.data"))){
//			dos.write(num);
//			dos.writeChars(name);
			oos.writeObject(d);
			oos.flush();
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
