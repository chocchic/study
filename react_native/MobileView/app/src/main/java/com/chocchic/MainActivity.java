package com.chocchic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ListView;
import android.widget.ProgressBar;

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
        
        }  
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}