package com.chocchic;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class LocationActivity extends AppCompatActivity implements AutoPermissionsListener {

    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        textView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //위치 정보 서비스를 위한 객체를 생성
                LocationManager manager =
                        (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                try{
                    //이전 위치를 출력
                    Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(location != null){
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        String message = "최근 위치\n Latitude:" + latitude +
                                "\nLongitude:" + longitude;
                        textView.setText(message);
                    }

                    //리스너 객체 생성
                    GPSListener gpsListener = new GPSListener();
                    //옵션 설정 - 10초마다 위치 정보를 갱신하고 최소 이동거리를 설정
                    long minTime = 10000;
                    float minDistance = 0;

                    manager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener);
                    Toast.makeText(LocationActivity.this, "위치 정보 확인 요청",
                            Toast.LENGTH_LONG).show();
                }catch(SecurityException e){
                    Toast.makeText(LocationActivity.this, e.getLocalizedMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        //동적 권한 요청
        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }

    class GPSListener implements LocationListener{

        //위치 정보를 처음 받거나 갱신된 경우 호출되는 메서드
        @Override
        public void onLocationChanged(@NonNull Location location) {
            //위도와 경도 가져오기
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            String message = "내 위치\n Latitude:" + latitude +
                    "\nLongitude:" + longitude;
            textView.setText(message);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            LocationListener.super.onStatusChanged(provider, status, extras);
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {
            LocationListener.super.onProviderEnabled(provider);
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {
            LocationListener.super.onProviderDisabled(provider);
        }
    }

    //권한 사용을 거부한 경우에 그 개수를 파악해주는 메서드
    @Override
    public void onDenied(int i, String[] strings) {
        Toast.makeText(this, "거부한 권한 개수:" + strings.length,
                Toast.LENGTH_LONG).show();
    }

    //권한 사용을 허용한 경우에 그 개수를 파악해주는 메서드
    @Override
    public void onGranted(int i, String[] strings) {
        Toast.makeText(this, "허용한 권한 개수:" + strings.length,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int [] grantResults) {
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);

        AutoPermissions.Companion.parsePermissions(this, requestCode,
                permissions, this);
    }
}