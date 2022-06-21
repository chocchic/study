package com.chocchic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
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
import java.util.ArrayList;
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
                String serverUpdateTime = object.getString("updatedate");

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
                if(serverUpdateTime.equals(localUpdateTime)){
                    Log.e("업데이트한 시간 비교" , "시간이 같으므로 다운로드할 필요 없음");

                    // 전체 페이지 개수를 업데이트 - 전체 데이터가 몇 개인지 읽어옵니다.
                    FileInputStream fis = openFileInput("totalpage.txt");
                    byte[] data = new byte[fis.available()];
                    fis.read();
                    totalPage = Integer.parseInt(new String(data));
                    fis.close();
                }else{
                    Log.e("업데이트한 시간 비교", "시간이 다르므로 데이터를 다운로드");
                    // 데이터를 다운로드 받을 위치 설정
                    url = new URL("http://192.168.0.9:80/item/list?page="+page+"?size="+size);
                    con = (HttpURLConnection)url.openConnection();
                    con.setUseCaches(false);
                    con.setConnectTimeout(30000);

                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    sb= new StringBuilder();
                    while(true){
                        String line = br.readLine();
                        if(line == null){
                            break;
                        }
                        sb.append(line+"\n");
                    }
                    br.close();
                    con.disconnect();
                    Log.e("다운로드 받은 데이터", sb.toString());

                    // JSON 파싱
                    object = new JSONObject(sb.toString());
                    // 전체 페이지 개수 가져오기 - json에서 이름과 완전히 같아야함
                    totalPage = object.getInt("totalPage");
                    // 전체 페이지 개수를 파일에 저장
                    // 서버와 클라이언트의 업데이트한 날짜가 같은 경우 서버의 데이터를 받아오지 않기 때문에 몇개의 페이지가 알지 못함
                    // 전체 페이지 개수를 파일에 저장을 해 놓아야만 스크롤 여부를 알 수 있음
                    FileOutputStream fos = openFileOutput("totalpage.txt", Context.MODE_PRIVATE);
                    fos.write((""+totalPage).getBytes());
                    fos.close();

                    // 아이템 목록 가져오기
                    JSONArray ar = object.getJSONArray("itemList");
                    // 로컬 뎅이터베이스 연결
                    SQLiteDatabase db = itemDB.getWritableDatabase();
                    // Item 테이블의 모든 데이터 삭제
                    db.delete("item", null, null);
                    //  순회
                    for(int i = 0; i< ar.length(); i++){
                        JSONObject item = ar.getJSONObject(i);

                        // ContentValues(Map처럼 사용하지만 Entity처럼 동작)를 이용해서 데이터 삽입, 수정, 삭제가 가능
                        ContentValues row = new ContentValues();
                        row.put("itemid", item.getLong("itemid"));
                        row.put("itemname", item.getString("itemname"));
                        row.put("price", item.getInt("price"));
                        row.put("description", item.getString("description"));
                        row.put("picture", item.getString("pictureurl"));
                        row.put("email", item.getString("email"));
                        // item테이블에 데이터 삽입
                        db.insert("item", null, row);
                    }
                }
                // SQlite데이터베이스에서 데이터를 읽어서 itemList에 저장
                SQLiteDatabase db = itemDB.getReadableDatabase();
                // Cursor는 Iterable, Enumeration과 유사한데, next()를 이용해서 다음 데이터를 찾아가는 방식으로 동작하고,
                // 읽은 데이터가 없으면 false를 리턴
                Cursor cursor = db.rawQuery("select itemid, itemname, price, description, pictureurl, email "+
                        "from item order by itemid desc", null);

                // 데이터를 저장할 List클리어
               list.clear();

               // 커서 순회
               while(cursor.moveToNext()){
                   Item item = new Item();
                   Item.setItemid(cursor.getLong(0));
                   Item.setItemname(cursor.getString(1));
                   Item.setPrice(cursor.getInt(2));
                   Item.setDescription(cursor.getString(3));
                   Item.setPictureurl(cursor.getString(4));
                   Item.setEmail(cursor.getString(5));

                   list.add(item);
               }
               Log.e("list", list.toString());
                // Message 생성
                Message msg = new Message();
                // 핸들러에게 메세지를 전송
                // handler의 handleMessage가 호출됩니다.
                handler.sendMessage(msg);
            }catch (Exception e){
                Log.e("데이터 다운로드 예외", e.getLocalizedMessage());
            }
        }
    }

    @Override
    // Ac
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 첫 호출시 주는 default 이거 안주면 page가 null이 되서 오류
        // 페이지 번호와 한 페이지에 보여질 데이터 개수 초기화
        page = 1;
        size = 15;
        //데이터베이스 rorcp todtjd
        itemDB = new ItemDB(this);
        // list 초기화
        list = new ArrayList<>();

        // 뷰 찾아오기
        listView = (ListView)findViewById(R.id.listview);
        downloadview = (ProgressBar)findViewById(R.id.downloadview);
        // 기본 출력을 위한 Adapter 생성과 설정
        itemAdapter = new ItemAdapter(this, list, R.layout.item_cell);
        listView.setAdapter(itemAdapter);
        
        // 밑에 뭔가 빼먹음
        // 스레드를 만들어서
        new DataDisplayThread();
    }
}