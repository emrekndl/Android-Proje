package com.example.emre.restoranrezervasyonyonetimi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class Main2Activity extends AppCompatActivity {


    Button goster2,geri;
    EditText tarih2;
    ListView list;
    public static String trh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        goster2=(Button)findViewById(R.id.goster2);
        tarih2=(EditText)findViewById(R.id.tarih2);
        list=(ListView)findViewById(R.id.list);
        geri=(Button)findViewById(R.id.geri);






        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(i);


            }
        });
        goster2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sqlVeritabani vt = new sqlVeritabani(Main2Activity.this);
                trh=tarih2.getText().toString();
                List<String> veri=vt.listele();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_list_item_1,android.R.id.text1,veri);
                list.setAdapter(adapter);





            }
        });
    }


}

