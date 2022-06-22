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
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

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
    //최종 업데이트 시간을 저장하기 위한 변수
    private String updateTime;

    //현재 페이지 번호, 한 페이지 당 데이터 개수, 전체 페이지 개수를 저장할 변수
    private int page;
    private int size;
    private int totalPage;

    //로컬 데이터베이스 변수
    private ItemDB itemDB;

    //데이터 목록을 저장할 List
    private List<Item> list;

    //화면에 보여지는 뷰를 위한 변수
    private ProgressBar downloadview;
    private ListView listView;

    //ListView에 데이터를 공급해줄 Adapter
    private ItemAdapter itemAdapter;

    // 가장하단에서 스크롤했는지 여부를 저장하기 위한 변수
    private Boolean lastitemVisibleFlag = false;

    //Looper는 메시지 시스템
    //메인 스레드에게 요청을 전송하는 핸들러
    Handler handler = new Handler(Looper.getMainLooper()){
        //java에서 메서드 오버라이딩을 할 때 되도록이면 @Override를 습관적으로 붙이는 것이 좋습니다.
        //메서드의 추상 여부 와 추상이 아닌 경우 어떤 작업을 하는지 확인
        @Override
        public void handleMessage(Message message){
            //하단이나 상단에 출력되는 메시지 박스가 Snackbar
            /*
            Snackbar.make(MainActivity.this.getWindow().getDecorView(),
                    "데이터 업데이트", Snackbar.LENGTH_LONG);
             */

            Toast.makeText(MainActivity.this, "데이터 출력",
                    Toast.LENGTH_LONG).show();

            //ListView 에 출력할 데이터 공급자 생성
            //클래스 안에서 this를 하게 되면 인스턴스 자신
            //anonymous 클래스에 안에서 this를 하게되면 anonymous 클래스의 인스터스
            //내부 클래스 안에서 외부 클래스 인스턴스를 사용하고자 할 때는
            //클래스이름.this를 이용하면 됩니다.
            itemAdapter = new ItemAdapter(MainActivity.this,list, R.layout.item_cell);
            //ListView 와 Adapter를 연결
            //데이터가 변경되면 itemAdapter.notifyDataSetChanged()를 호출
            listView.setAdapter(itemAdapter);
            //프로그래스 뷰를 화면에서 제거
            downloadview.setVisibility(View.GONE);

            //현재 시간을 파일에 기록
            try{
                //안드로이드에서 파일에 기록하는 작업은 Data 디렉토리에서만 가능한데
                //openFileOutput을 호출하면 Data 디렉토리에 대한 경로 설정을 해줍니다.
                FileOutputStream fos = openFileOutput("updatetime.txt",Context.MODE_PRIVATE);
                fos.write(updateTime.getBytes());
                fos.close();
            }catch(Exception e){
                Log.e("업데이트 오류", "업데이트 한 시간을 기록하지 못함");
                Log.e("업데이트 오류", e.getLocalizedMessage());
            }

        }
    };

    //출력하기 위한 데이터를 만드는 스레드
    class DataDisplayThread extends Thread{
        //다운로드 받은 문자열을 저장하기 위한 변수
        StringBuilder sb = new StringBuilder();

        //스레드로 수행할 내용
        public void run(){
            try{
                //업데이트 한 시간 가져오기 - GET 방식, 파라미터 없음음
                URL url = new URL("http://192.168.0.9:80/item/updatedate");
                //URL에 연결
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                //옵션 설정
                con.setUseCaches(false);//캐싱된 데이터 사용 여부
                con.setConnectTimeout(30000);//최대 접속 요청 시간 - 30초

                //스트림 생성 - 문자열을 다운로드 받기 위한 스트림
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                //문자열이 아닌 파일 다운로드
                //InputStream is = con.getInputStream();

                //문자열 읽기
                while(true){
                    String line = br.readLine();
                    if(line == null){
                        break;
                    }
                    sb.append(line + "\n");
                }
                br.close();
                con.disconnect();
                //중간에 다운로드 받은 내용을 출력
                Log.e("다운로드 받은 문자열", sb.toString());
                //다운로드 받은 문자열이 csv 나 XML, JSON, YML 이라면 파싱 필요, 아니면 바로 사용 가능

                //JSON 파싱 :
                //다운로드 받은 문자열이 { }로 감싸져 있어서 JSONObject로 생성
                //[ ]로 감싸져 있으면 JSONArray로 생성
                JSONObject object = new JSONObject(sb.toString());
                //updatedate 키의 값을 문자열로 가져오기
                String serverUpdateTime = object.getString("updatedate");
                //서버의 업데이트 시간을 updateTime에 대입
                updateTime = serverUpdateTime;

                String localUpdateTime = null;
                //로컬의 업데이트 타임을 구하기
                try{
                    FileInputStream fis = openFileInput("updatetime.txt");
                    byte[] data = new byte[fis.available()];
                    fis.read(data);
                    fis.close();
                    localUpdateTime = new String(data);
                }catch(Exception e){
                    Log.e("업데이트 파일", "업데이트 시간이 기록된 파일이 없음");
                }
                if(serverUpdateTime.equals(localUpdateTime)){
                    Log.e("업데이트 한 시간 비교", "시간이 같으므로 다운로드 할 필요 없음");

                    //전체 페이지 개수를 업데이트 - 전체 데이터가 몇개인지 읽어옵니다.
                    FileInputStream fis = openFileInput("totalpage.txt");
                    byte[] data = new byte[fis.available()];
                    fis.read(data);
                    totalPage = Integer.parseInt(new String(data));
                    fis.close();

                }else{
                    Log.e("업데이트 한 시간 비교", "시간이 다르므로 데이터를 다운로드");
                    //데이터를 다운로드 받을 위치 설정
                    url = new URL("http://192.168.10.8:9000/item/list?page="
                            + page + "&size=" + size);
                    con = (HttpURLConnection)url.openConnection();
                    con.setUseCaches(false);
                    con.setConnectTimeout(30000);

                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    sb = new StringBuilder();
                    while(true){
                        String line = br.readLine();
                        if(line == null){
                            break;
                        }
                        sb.append(line + "\n");
                    }
                    br.close();
                    con.disconnect();
                    Log.e("다운로드 받은 데이터", sb.toString());

                    //JSON 파싱
                    object = new JSONObject(sb.toString());
                    //전체 페이지 개수 가져오기
                    totalPage = object.getInt("totalPage");
                    //전체 페이지 개수를 파일에 저장
                    //서버 와 클라이언트의 업데이트한 날짜가 같은 경우 서버의 데이터를 받아오지 않기 때문에
                    //몇 개의 페이지가 있는지 알지 못하기 때문에
                    //전체 페이지 개수를 파일에 저장을 해 놓아야 스크롤 여부를 결정할 수 있습니다.
                    FileOutputStream fos = openFileOutput(
                            "totalpage.txt", Context.MODE_PRIVATE);
                    fos.write((""+totalPage).getBytes());
                    fos.close();

                    //아이템 목록 가져오기
                    JSONArray ar = object.getJSONArray("itemList");

                    //로컬 데이터베이스 연결
                    SQLiteDatabase db = itemDB.getWritableDatabase();
                    //Item 테이블의 모든 데이터 삭제
                    db.delete("item", null, null);
                    //순회
                    for(int i=0; i<ar.length(); i++){
                        JSONObject item = ar.getJSONObject(i);

                        //ContentValues(Map 처럼 사용하지만 Entity처럼 동작)
                        //를 이용해서 데이터 삽입, 수정, 삭제가 가능
                        ContentValues row = new ContentValues();
                        row.put("itemid", item.getLong("itemid"));
                        row.put("itemname", item.getString("itemname"));
                        row.put("price", item.getInt("price"));
                        row.put("description", item.getString("description"));
                        row.put("pictureurl", item.getString("pictureurl"));
                        row.put("email", item.getString("email"));

                        //item 테이블에 데이터 삽입
                        db.insert("item", null, row);

                    }
                }

                //SQLite 데이터베이스에서 데이터를 읽어서 itemList에 저장

                //데이터베이스에서 데이터 읽기
                SQLiteDatabase db = itemDB.getReadableDatabase();
                //Cursor 는 Iterable, Enumeration 과 유사한데
                //next()를 이용해서 다음 데이터를 찾아가는 방식으로 동작하고
                //읽은 데이터가 없으면 false를 리턴
                Cursor cursor = db.rawQuery(
                        "select itemid, itemname, price, description, pictureurl, email " +
                                "from item order by itemid desc", null);
                //데이터를 저장할 List 클리어
                list.clear();

                //커서 순회
                while(cursor.moveToNext()){
                    Item item = new Item();
                    item.setItemid(cursor.getLong(0));
                    item.setItemname(cursor.getString(1));
                    item.setPrice(cursor.getInt(2));
                    item.setDescription(cursor.getString(3));
                    item.setPictureurl(cursor.getString(4));
                    item.setEmail(cursor.getString(5));

                    list.add(item);
                }

                Log.e("list", list.toString());
                //Message 생성
                Message message = new Message();
                //핸들러에게 메시지를 전송
                //handler의 handleMessage 가 호출됩니다.
                handler.sendMessage(message);

            }catch(Exception e){
                Log.e("데이터 다운로드 예외", e.getLocalizedMessage());
            }
        }
    }

    @Override
    //Activity가 만들어지면 호출되는 메서드
    //화면을 초기화하고 설정하는 작업을 수행
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //페이지 번호 와 한 페이지에 보여질 데이터 개수 초기화
        page=1;
        size=15;
        //데이터베이스 객체 생성
        itemDB = new ItemDB(this);
        //List 초기화
        list = new ArrayList<>();

        //뷰 찾아오기
        listView = (ListView)findViewById(R.id.listview);
        downloadview = (ProgressBar)findViewById(R.id.downloadview);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            // i는 현재 스크롤 상태
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if(i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastitemVisibleFlag){
                    // 다음 페이지의 데이터 가져오기
                    // page번호를 1 증가시켜서 다음 페이지의 데이터를 요청
                    if(page >= totalPage){
                        Toast.makeText(MainActivity.this,"더 이상의 데이터가 없습니다.", Toast.LENGTH_LONG).show();
                    }else{
                        page = page +1;
                        downloadview.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            // i는 가장 처음에 보이는 데이터의 인덱스
            // i1은 한 페이지에 보여줄 데이터 개수
            // i2는 출력되어야 할 전체 데이터의 개수
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                // 마지막 부분에서 스크롤했는지 여부를 설정
                lastitemVisibleFlag = i2 > 2 && i+i1 >=i2;
            }
        });
        //기본 출력을 위한 Adapter 생성 과 설정
        itemAdapter = new ItemAdapter(this, list, R.layout.item_cell);
        listView.setAdapter(itemAdapter);

        //스레드를 만들어서 실행
        new DataDisplayThread().start();
    }
}