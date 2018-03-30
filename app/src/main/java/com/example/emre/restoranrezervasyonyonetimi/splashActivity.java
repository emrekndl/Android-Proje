package com.example.emre.restoranrezervasyonyonetimi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread girisThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(1000);
                } catch (Exception e) {

                } finally {

                    Intent i = new Intent(splashActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        girisThread.start();

    }
}
