package com.chocchic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberRegisterActivity extends AppCompatActivity {
    private Button registerbtn, mainbtn, localbtn, gallerybtn, camerabtn;
    private EditText emailinput, pwinput, pwconfirminput, nameinput;
    private ImageView image;

    //갤러리 화면에서 이미지를 선택한 후 동작하는 Launcher 인스턴스
    ActivityResultLauncher<Intent> imageLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if(result.getResultCode() == RESULT_OK){
                                Intent intent = result.getData();
                                Uri uri = intent.getData();
                                image.setImageURI(uri);
                            }
                        }
                    });

    //카메라 화면에서 이미지를 선택한 후 동작하는 Launcher 인스턴스
    ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK){
                Intent intent = result.getData();
                Bundle extras = intent.getExtras();
                Bitmap bitmap = (Bitmap)extras.get("data");
                image.setImageBitmap(bitmap);
            }
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_register);

        registerbtn = (Button)findViewById(R.id.registerbtn);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler handler = new Handler(Looper.getMainLooper()){
                    @Override
                    public void handleMessage(Message message){
                        String msg = (String)message.obj;
                        Toast.makeText(MemberRegisterActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                };

                //입력한 내용을 전부 읽어옵니다.
                //getText()의 결과는 Editable 타입이라서 문자열로 바로 사용할 수 없어서
                //인스턴스를 문자열로 치환해주는 toString()을 호출해서 문자열로 변환합니다.
                String email = emailinput.getText().toString().trim();
                String pw = pwinput.getText().toString();
                String pwconfirm = pwconfirminput.getText().toString();
                String name = nameinput.getText().toString();

                Message message = new Message();
                //입력한 내용이 없는 경우
                if(email.length() == 0){
                    message.obj = "이메일은 필수 입력입니다.";
                    handler.sendMessage(message);
                    return;
                }else{
                    //email 정규식 객체 생성
                    String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
                    Pattern p = Pattern.compile(regex);
                    //email 을 정규식 객체의 패턴에 맞는지 확인
                    Matcher m = p.matcher(email);
                    //정규식 패턴에 맞지 않는다면 에러
                    if(m.matches() == false){
                        message.obj = "이메일 형식에 맞지 않습니다.";
                        handler.sendMessage(message);
                        return;
                    }
                }

                //비밀번호 관련 유효성 검사
                if(pw.length() == 0){
                    message.obj = "비밀번호는 필수 입력입니다.";
                    handler.sendMessage(message);
                    return;
                }else{
                    //password 정규식 객체 생성
                    //영문 대소문자 숫자 특수문자 포함 8자 이상
                    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}";
                    Pattern p = Pattern.compile(regex);
                    //pw가 정규식 객체의 패턴에 맞는지 확인
                    Matcher m = p.matcher(pw);
                    if(m.matches() == false){
                        message.obj = "비밀번호는 영문 대소문자 와 숫자 및 특수문자를 포함해야 합니다.";
                        handler.sendMessage(message);
                        return;
                    }
                }

                if(pw.equals(pwconfirm) == false){
                    message.obj = "2개의 비밀번호가 다릅니다.";
                    handler.sendMessage(message);
                    return;
                }

                if(name.length() < 2){
                    message.obj = "이름은 2자 이상이어야 합니다.";
                    handler.sendMessage(message);
                    return;
                }else{
                    String regex = "[0-9]|[a-z]|[A-Z]|[가-힣]";
                    for(int i=0; i<name.length(); i++) {
                        String ch = name.charAt(i) + "";
                        Pattern p = Pattern.compile(regex);
                        //pw가 정규식 객체의 패턴에 맞는지 확인
                        Matcher m = p.matcher(ch);
                        if(m.matches() == false){
                            message.obj = "별명에는 영문 과 숫자 그리고 한글만 사용해야 합니다..";
                            handler.sendMessage(message);
                            return;
                        }
                    }
                }

                //서버에 요청을 전송할 스레드 생성
                new Thread(){
                    @Override
                    public void run(){
                        try{
                            //회원 가입을 위한 서버 URL
                            URL url = new URL("http://192.168.10.8:9000/member/register");

                            //연결 객체를 생성
                            HttpURLConnection con = (HttpURLConnection)url.openConnection();
                            con.setRequestMethod("POST");
                            con.setConnectTimeout(30000);
                            con.setUseCaches(false);

                            //파일을 제외한 파라미터 만들기
                            String [] data = {email, pw, name};
                            String [] dataName = {"email", "password", "name"};

                            //boundary 생성 - 실행할 때 마다 다른 값을 같도록 해주어야 함
                            String boundary = UUID.randomUUID().toString();
                            //라인의 종료를 위한 변수
                            String lineEnd = "\r\n";

                            //파일 첨부가 있는 경우 설정
                            //html에서도 form 안에 file 이 있는 경우는 반드시 enctype을 multipart/form-data로 전송해야 ㅎ바니다.
                            con.setRequestProperty("ENCTYPE", "multipart/form-data");
                            con.setRequestProperty(
                                    "Content-Type", "multipart/form-data;boundary=" + boundary);

                            //파라미터 구분자 생성
                            String delimiter = "--" + boundary + lineEnd;

                            //파라미터를 하나로 묶기
                            StringBuffer postDatabuilder = new StringBuffer();
                            for(int i=0; i<data.length; i++){
                                postDatabuilder.append(delimiter);
                                postDatabuilder.append("Content-Disposition: form-data; name=\"" +
                                        dataName[i] + "\"" + lineEnd + lineEnd + data[i] + lineEnd);
                            }

                            //파일 파라미터 생성
                            String fileName = name + ".png";
                            //이미지 뷰에 이미지가 출력된 경우에만
                            if(image.getDrawable() != null){
                                postDatabuilder.append(delimiter);
                                postDatabuilder.append("Content-Disposition: form-data; name=\"" +
                                        "image" + "\";filename=\"" + fileName + "\"" + lineEnd);
                            }

                            //파라미터 전송
                            DataOutputStream ds = new DataOutputStream(con.getOutputStream());
                            ds.write(postDatabuilder.toString().getBytes());
                            //이미지 업로드
                            if(image.getDrawable() != null){
                                ds.writeBytes(lineEnd);

                                //raw 디렉토리에 존재하는 이미지인 경우
                                //InputStream fres = getResources().openRawResource(R.raw.choi);

                                //ImageView의 내용을 가지고 스트림 생성
                                BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
                                Bitmap bitmap = drawable.getBitmap();

                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                                InputStream fres = new ByteArrayInputStream(baos.toByteArray());

                                //이미지 업로드
                                byte [] buffer = new byte[fres.available()];

                                int length = -1;
                                while((length = fres.read(buffer)) != -1){
                                    ds.write(buffer, 0, length);
                                }
                                ds.writeBytes(lineEnd);
                                ds.writeBytes(lineEnd);
                                ds.writeBytes("--" + boundary + "--" + lineEnd);
                                fres.close();
                            }else{
                                ds.writeBytes(lineEnd);
                                ds.writeBytes("--" + boundary + "--" + lineEnd);
                            }
                            ds.flush();
                            ds.close();

                            //API Server에게 전송하고 문자열 응답을 받기 위한 준비
                            BufferedReader br = new BufferedReader(
                                    new InputStreamReader(con.getInputStream()));
                            StringBuilder sb = new StringBuilder();

                            while(true){
                                String line = br.readLine();
                                if(line == null){
                                    break;
                                }
                                sb.append(line + "\n");
                            }

                            br.close();
                            con.disconnect();
                            Log.e("전송된 데이터", sb.toString());

                            if(sb.toString().length() >= 1){
                                //받아온 문자열을 JSON 객체로 변환
                                JSONObject object = new JSONObject(sb.toString());
                                //JSON 객체에서 email 키에 해당하는 값을 문자열로 저장
                                String result = object.getString("email");
                                if(email.equals(result)){
                                    //회원 가입 성공
                                    finish();

                                }else{
                                    //회원 가입 실패
                                    message.obj = email;
                                    handler.sendMessage(message);
                                }
                            }
                        }catch(Exception e){
                            message.obj = e.getLocalizedMessage();
                            handler.sendMessage(message);
                        }
                    }
                }.start();


            }
        });

        mainbtn = (Button)findViewById(R.id.mainbtn);
        mainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //현재 Activity 종료
                finish();
            }
        });

        localbtn = (Button)findViewById(R.id.localbtn);
        localbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //raw 디렉토리에 있는 이미지를 읽어서 ImageView에 출력
                Bitmap bitmap = BitmapFactory.decodeResource(
                        getResources(),R.raw.choi);
                image.setImageBitmap(bitmap);
            }
        });

        gallerybtn = (Button)findViewById(R.id.gallerybtn);
        gallerybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                imageLauncher.launch(intent);
            }
        });

        camerabtn = (Button)findViewById(R.id.camerabtn);
        camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraLauncher.launch(intent);
            }
        });

        emailinput = (EditText)findViewById(R.id.emailinput);
        pwinput = (EditText)findViewById(R.id.pwinput);
        pwconfirminput = (EditText)findViewById(R.id.pwconfirminput);
        nameinput = (EditText)findViewById(R.id.nameinput);

        image = (ImageView)findViewById(R.id.image);

    }
}