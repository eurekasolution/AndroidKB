package com.kbstar.o01gps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.DragAndDropPermissions;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
//import com.yanzhenjie.permission.Action;
//import com.yanzhenjie.permission.runtime.Permission;


import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static String TAG = "MYGPS";
    private Button btnGPS;
    private TextView display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGPS = findViewById(R.id.btnGPS);
        display = findViewById(R.id.display);

        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getGPSInfo();
            }
        });

        // Step 5. 퍼미션 허가 코드 추가

        AndPermission.with(this)
                .runtime()
                .permission(
                        Permission.ACCESS_FINE_LOCATION,
                        Permission.ACCESS_COARSE_LOCATION
                )
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        printDebug("허가된 갯수 : " + permissions.size());
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        printDebug("거부된 갯수 : " + permissions.size());
                    }
                })
                .start();

    }

    //@SuppressWarnings({"MissingPermission"})
    public void getGPSInfo() {
        // Step 1: 위치 관리 객체 참조
        printDebug("\n위치관리 객체 참조");
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            printDebug("Location = " + location);
            if(location != null)
            {
                Double lat = location.getLatitude();
                Double lon = location.getLongitude();

                printDebug("최근 위치");
                printDebug("Lat : " + lat);
                printDebug("Lon : " + lon);

                // Step 3. 위치 정보 갱신 요청
                GPSListener gpsListener = new GPSListener();
                long minTime = 10000;
                float minDistance = 0.0F;
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        minTime, minDistance, gpsListener);

                printDebug("정보요청 중....");


                // Step 4. ==> 위험권한 코드 추가
                // gradle


            }else
            {
                GPSListener gpsListener = new GPSListener();
                long minTime = 10000;
                float minDistance = 0.0F;
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        minTime, minDistance, gpsListener);

                printDebug("정보요청 중1....");
            }

        }catch(Exception e)
        {
            printDebug("Exception : " + e.getMessage());
        }

    }

    public void printDebug(String msg)
    {
        display.append(msg + "\n");
    }

    // Step 2: 위치 리스너 구현
    class GPSListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location location) {
            Double lat = location.getLatitude();
            Double lon = location.getLongitude();

            printDebug("현재 위치");
            printDebug("Lat : " + lat);
            printDebug("Lon : " + lon);
        }

        @Override
        public void onFlushComplete(int requestCode) {
            //LocationListener.super.onFlushComplete(requestCode);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            //LocationListener.super.onStatusChanged(provider, status, extras);
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {
            //LocationListener.super.onProviderEnabled(provider);
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {
            //LocationListener.super.onProviderDisabled(provider);
        }
    }


}