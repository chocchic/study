package com.chocchic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {
    //화면에 보여지는 뷰에 대한 속성
    private Button back;
    private TextView itemname, price, description;
    private ImageView picture;

    //이미지를 다운로드 받을 url
    private String pictureurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //뷰를 찾아오기
        back = (Button)findViewById(R.id.back);
        itemname = (TextView)findViewById(R.id.itemname);
        price = (TextView)findViewById(R.id.price);
        description = (TextView)findViewById(R.id.description);
        picture = (ImageView)findViewById(R.id.picture);

        //이전 Activity로부터 넘겨받은 데이터 가져오기
        Intent intent = getIntent();
        Item item = (Item)intent.getSerializableExtra("item");

        //넘어온 데이터 출력
        itemname.setText(item.getItemname());
        price.setText(item.getPrice() + "원");
        description.setText(item.getDescription());

        //이미지 파일이름 저장
        pictureurl = item.getPictureurl();

        //back 버튼의 클릭 이벤트
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //현재 Activity를 종료
                finish();
            }
        });

        //이미지를 다운로드 받아주는 스레드를 생성하고 실행
        new ThreadEx().start();

    }

    //이미지를 다운로드 받기 위한 스레드
    class ThreadEx extends Thread{
        public void run(){
            try {
                URL url = new URL("http://192.168.0.9:80/member/download?path=" + pictureurl);
                //파일을 다운로드 받기 위한 스트림 생성
                InputStream inputStream = url.openStream();

                //안드로이드에서 이미지인 경우만 가능
                Bitmap bit = BitmapFactory.decodeStream(inputStream);

                //핸들러에게 bit를 전송해서 picture에서 출력하도록 합니다.
                Message message = new Message();
                message.obj = bit;
                handler.sendMessage(message);

            }catch(Exception e){
                Log.e("이미지 다운로드 실패", e.getLocalizedMessage());
            }
        }
    }

    //이미지 뷰에 이미지를 출력하기 위한 핸들러
    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message message){
            //전송된 데이터를 가져옵니다.
            Bitmap bitmap = (Bitmap)message.obj;
            //이미지 뷰에 출력
            picture.setImageBitmap(bitmap);
        }
    };

}