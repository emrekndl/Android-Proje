package com.example.emre.restoranrezervasyonyonetimi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    sqlVeritabani bvt;
    EditText ad,tel,tarih,masano,id;
    Button giris,cikis,goster,duzelt;
    TextView doluluk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bvt=new sqlVeritabani(this);

        doluluk=(TextView)findViewById(R.id.doluluk);


        ad =(EditText)findViewById(R.id.ad);
        tel=(EditText)findViewById(R.id.tel);
        tarih=(EditText)findViewById(R.id.tarih);
        masano=(EditText)findViewById(R.id.masano);
        id =(EditText)findViewById(R.id.id);

        giris=(Button)findViewById(R.id.girisbtn);
        cikis=(Button)findViewById(R.id.cikisbtn);
        goster=(Button)findViewById(R.id.goster);
        duzelt=(Button)findViewById(R.id.duzelt);



        goster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });


        doluluk.setText("Rezervasyon Yapan Kişi Sayısı :"+bvt.satirSayisi());




        Giris();
        Cikis();
        Duzelt();
    }

    protected void onResume(){
        bvt.getWritableDatabase();
        super.onResume();
        doluluk.setText("Rezervasyon Yapan Kişi Sayısı :"+bvt.satirSayisi());

    }
    protected void onPause(){
        bvt.close();
        super.onPause();
        doluluk.setText("Rezervasyon Yapan Kişi Sayısı :"+bvt.satirSayisi());
    }



    public void Giris(){
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean eklendimi = bvt.RGiris(ad.getText().toString(),
                        tel.getText().toString(), masano.getText().toString(),
                        tarih.getText().toString());




                if(eklendimi != false) {
                    Toast.makeText(MainActivity.this, "Rezervasyon Yapıldı",
                            Toast.LENGTH_LONG).show();
                    doluluk.setText("Rezervasyon Yapan Kişi Sayısı :"+bvt.satirSayisi());
                }
                else{
                    Toast.makeText(MainActivity.this,"Rezervasyon Yapılamadı",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void Cikis(){
        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer sil = bvt.RCikis(id.getText().toString());




                if(sil>0){
                    Toast.makeText(MainActivity.this, "Rezervasyon Çıkışı Yapıldı",
                            Toast.LENGTH_LONG).show();
                    doluluk.setText("Rezervsayon Yapan Kişi Sayısı :"+bvt.satirSayisi());
                }
                else {
                    Toast.makeText(MainActivity.this, "Rezervasyon Çıkışı Yapılamadı",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    public void Duzelt(){
        duzelt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dzlt = bvt.RDuzelt(ad.getText().toString(),tel.getText().toString(),masano.getText().toString(),tarih.getText().toString(),id.getText().toString());

                if(dzlt>0){
                    Toast.makeText(MainActivity.this, "Rezervasyon Düzeltildi",
                            Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this,"Rezervasyon Düzeltilemedi",
                            Toast.LENGTH_LONG).show();
            }
        });
    }
}
