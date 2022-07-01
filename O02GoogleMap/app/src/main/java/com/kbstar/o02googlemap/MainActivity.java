package com.kbstar.o02googlemap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

/*
File
-> Project Structure...
-> Dependencies
-> app
-> +
-> Library Dependency
-> play-services-maps
-> Enter -> 선택후  OK

 */
public class MainActivity extends AppCompatActivity {

    private Button button;
    GoogleMap map;
    SupportMapFragment mapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                Log.d("GMAP", "READY...");
                map = googleMap;
                //map.setMyLocationEnabled(true);
            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMap();
            }
        });

        AndPermission.with(this)
                .runtime()
                .permission(
                        Permission.ACCESS_FINE_LOCATION,
                        Permission.ACCESS_COARSE_LOCATION
                )
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //printDebug("허가된 갯수 : " + permissions.size());
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //printDebug("거부된 갯수 : " + permissions.size());
                    }
                })
                .start();

    }

    public void showMap()
    {
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
        Log.d("GoogleMap", msg);
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