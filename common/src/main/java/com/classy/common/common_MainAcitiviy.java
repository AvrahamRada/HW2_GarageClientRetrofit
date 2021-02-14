package com.classy.common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.classy.common.controllers.AppUsageTime;
import com.classy.common.controllers.GarageController;
import com.classy.common.entities.Garageinfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class common_MainAcitiviy extends AppCompatActivity
{
    private TextView main_TXT_time,main_TXT_name,main_TXT_address, main_TXT_open, main_TXT_cars;
    private ProgressBar main_PGB_garage;
    private AppUsageTime appUsageTime;
    private long startTimeStamp = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_acitiviy_main);

        findViews();
        appUsageTime = AppUsageTime.initHelper(this.getApplicationContext());
        GarageController garageController = new GarageController();
        garageController.start(new GarageController.Callback_Garage() {
            @Override
            public void garage(Garageinfo garageinfo) {
                main_PGB_garage.setVisibility(View.GONE);
                main_TXT_name.setText("Name: " + garageinfo.getName());
                main_TXT_address.setText("Address: " + garageinfo.getAddress());
                String open = garageinfo.isOpen() ? "Yes" : "No";
                main_TXT_open.setText("Open: " + open);
                String cars = "";
                for (int i = 0; i < garageinfo.getCars().length; i++) {
                    cars += (garageinfo.getCars())[i];
                    if (i != garageinfo.getCars().length - 1) {
                        cars += ", ";
                    }
                }
                main_TXT_cars.setText("Cars: " + cars);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        long duration = (System.currentTimeMillis() - startTimeStamp) / 1000;
        appUsageTime.timeSaved(duration, "");
    }
    @Override
    protected void onResume() {
        super.onResume();
        startTimeStamp = System.currentTimeMillis();
        getAllUsageInfo();
    }

    private void findViews() {
        main_TXT_time = findViewById(R.id.main_TXT_time);
        main_TXT_name = findViewById(R.id.main_TXT_name);
        main_TXT_address = findViewById(R.id.main_TXT_address);
        main_TXT_open = findViewById(R.id.main_TXT_open);
        main_TXT_cars = findViewById(R.id.main_TXT_cars);
        main_PGB_garage = findViewById(R.id.main_PGB_garage);
    }
    public void getAllUsageInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appUsageTime.getInstance().finalTime(
                        "", time -> runOnUiThread(() -> {
                            if (time >= 60) {
                                long minTime = time / 60;
                                long secTime = time % 60;
                                    main_TXT_time.setText("Total time: \n" +
                                            minTime + " Minutes " + "and "+ secTime + " Seconds");
                            } else{
                                main_TXT_time.setText("Total  time: " + time + " Seconds");
                        }
            }

                        ));
            }
        }).start();
    }

}
