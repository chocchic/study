package com.chocchic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // 최종 업데이트 시간을 저장하기 위한 변수
    private String updateTime;

    // 현재 페이지 번호, 한 페이지당 데이터 개수, 전체 페이지 개수를 저장할 변수
    private int page;
    private int size;
    private int totalPage;

    // 로컬 데이터베이스 변수
    private ItemDB itemDB;

    // 데이터 목록을 저장할 List
    private List<Item> list;

    // 화면에 보여지는 뷰를 위한 변수
    private ProgressBar downloadview;
    private ListView listView;

    // ListView에 데이터를 공급해줄 Adapter
    private ItemAdapter itemAdapter;

    // Looper는 메세지 시스템. 메인 스레드에게 요청을 전송하는 핸들러
    Handler handler = new Handler(Looper.getMainLooper()){
        // 자바에서 메서드 오버라이딩할 때 되도록이면 @Override를 붙이는 것이 좋음 -> 변수명 틀리거나 하면 알 수 있음
        // 메서드의 추상여부와 추상이 아닐 때 할 작업이 무엇인지 확인
        @Override
        public void handleMessage(Message msg){
            // 하단이나 상단에 출력되는 메시지 박스가 Snackbar
            Snackbar.make(MainActivity.this.getWindow().getDecorView(),"데이터 업데이트", Snackbar.LENGTH_LONG);

            // ListView에 출력할 데이터 공급자 생성
            // 클래스 안에서 this를 하게 되면 인스턴스 자신
            // anonymous 클래스에 안에서 this를 하게 되면 anonymous 클래스의 인스턴스
            // 내부 클래스 안에서 외부 클래스 인스턴스를 사용하고자 할 때는 클래스 이름.this를 이용하면 됩니다.
            itemAdapter = new ItemAdapter(MainActivity.this, list, R.layout.item_cell);

            // ListView와 Adapter를 연결
            // 데이터가 변경되면 itemAdapter.notifyDataSetChanged()를 호출
            listView.setAdapter(itemAdapter);

            // 프로그래스 뷰를 화면에서 제거
            downloadview.setVisibility(View.GONE);

            // 현재 시간을 파일에 기록
            try{
                FileOutputStream fos = openFileOutput("updatetime.txt", Context.MODE_PRIVATE);
                fos.write(updateTime.getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                Log.e("업데이트 오류", "파일이 없습니다.");
            } catch (IOException e) {
                Log.e("업데이트 오류", "파일을 읽어오는 과정에서 문제가 생겼습니다.");
            }
        }  
    };

    // 출력하기 위한 데이터를 만드는 스레드
    class DataDisplayThread extends Thread {
        // 다운로드 받은 문자열을 저장하기 위한 변수
        StringBuilder sb = new StringBuilder();

        // 스레드로 수행할 내용
        public void run(){
            try{
                // 업데이트한 시간 가져오기 - GET방식, 파라미터 없음
                URL url = new URL("http://192.168.0.9:80/item/updatedate");
                // URL에 연결
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                // 옵션 설정
                con.setUseCaches(false); // 캐싱된 데이터 사용 여부
                con.setConnectTimeout(30000); // 최대 접속 요청 시간 - 30초

                // 스트림 생성 - 문자열을 다운로드 받기 위해
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                // 문자열이 아닌 파일 다운로드
                //InputStream is = con.getInputStream();

                // 문자열 읽기
                while(true){
                    String line = br.readLine();
                    if(line == null){
                        break;
                    }
                    sb.append(line+"\n");
                }
                br.close();;
                con.disconnect();

                // 중간에 다운로드 받은 내용을 출력
                Log.e("다운로드 받은 문자열", sb.toString());
                // 다운로드 받은 문자열이 csv나 XML,JSON, YML이라면 파싱해야 하고, 아니면 바로 사용 가능

                // JSOn 파싱
                // 다운로드 받은 문자열이 { }로 감싸져 있어서 JSONObject로 생성
                // [ ]로 감싸져 있으면 JSONArray로 생성
                JSONObject object = new JSONObject(sb.toString());
                // updatedate키의 값을 문자열로 가져오기
                String serverUpdateTime = object.getString("serverUpdateTime");

                String localUpdateTime = null;
                // 로컬의 업데이트 타임을 구하기
                try{
                    FileInputStream fis = openFileInput("updatetime.txt");
                    byte[] data = new byte[fis.available()];
                    fis.read();
                    fis.close();
                    localUpdateTime = new String(data);
                }catch (Exception e){
                    Log.e("업데이트 파일", "업데이트 시간이 기록된 파일이 없음");
                }

            }catch (Exception e){
                Log.e("데이터 다운로드 예외", e.getLocalizedMessage());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}