package com.example.emre.restoranrezervasyonyonetimi;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.emre.restoranrezervasyonyonetimi.Main2Activity.trh;



public class sqlVeritabani extends SQLiteOpenHelper {

    public static final String veritabaniAdi = "rezervasyonBilgileri";
    public static final String tabloAdi = "musteriTablosu";
    public static final String adsoyad = "adsoyad";
    public static final String telefon = "telefon";
    public static final String masano = "masano";
    public static final String tarih = "tarih";
    public static final String id = "id";

    public ArrayList<String> sonuc = new ArrayList<String>();



    public  sqlVeritabani(Context c){
        super(c,veritabaniAdi,null,1);

    }



    public void onCreate(SQLiteDatabase vt) {

        String sql=" create table "+tabloAdi+" (adsoyad text, telefon integer, masano integer, tarih text,id integer primary key autoincrement )";
        vt.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase vt, int eski, int yeni) {
        vt.execSQL(" drop table if exists "+tabloAdi);
        onCreate(vt);
    }



    public boolean RGiris(String adsoy, String tel, String mno,String tar){

        SQLiteDatabase db = this.getWritableDatabase();

            ContentValues yenideger = new ContentValues();
        long sonuc;


            if (adsoy.isEmpty() || tel.isEmpty() || mno.isEmpty() || tar.isEmpty()) { //edittex ler boşsa kayıt yapılmayacak
                return false;
            } else {
                String[] sutunlar = {adsoyad,telefon,masano,tarih,id};
                Cursor c = db.query(tabloAdi,sutunlar,null,null,null,null,null);
                      c.moveToFirst(); // bunu koyunca aynı ismli(telefon,masa numarası) değerleri kayıt ediyor  kaldırınca da bir kere kayıt yaptıktan sonra daha yapmıyor
                while (!c.isAfterLast()){
                    if (tel.equals(c.getInt(c.getColumnIndex("telefon"))) || (mno.equals(c.getInt(c.getColumnIndex("masano"))) && tar.equals(c.getString(c.getColumnIndex("tarih"))))) {
                        return false; //telefon ve masano önceden girilmiş değerlerle aynı ise kayıt yapılmayacak

                    }
                    c.moveToNext();
                }
                yenideger.put(adsoyad, adsoy);
                yenideger.put(telefon, tel);
                yenideger.put(masano, mno);
                yenideger.put(tarih, tar);

                //sonuc = db.insert("musteriTablosu", null, yenideger);
            }

        sonuc = db.insert("musteriTablosu", null, yenideger);

            db.close();

            if (sonuc == -1)
                return false;
            else
                return true;


    }

    public int RCikis(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String []s={id};
        int i=db.delete("musteriTablosu","id=?",s);
        db.close();

        return  i;

    }

    public int RDuzelt(String adsoy, String tel, String mno,String tar,String id){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues yenidegerler = new ContentValues();

        if(!adsoy.isEmpty())
            yenidegerler.put(adsoyad,adsoy);

        if(!tel.isEmpty())
            yenidegerler.put(telefon,tel);

        if(!mno.isEmpty())
            yenidegerler.put(masano,mno);

        if(!tar.isEmpty())
            yenidegerler.put(tarih,tar);


        String []id2={id};
        int i= db.update("musteriTablosu",yenidegerler,"id=?",id2);
        db.close();

        return i;
    }


    public List<String> listele() {

        List<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] sutunlar = {masano, adsoyad, telefon, tarih,id};

        String [] tarih2={trh};

        Cursor c;
        if(!trh.isEmpty()){
            c = db.query(tabloAdi, sutunlar, "tarih=?",tarih2, null, null, null);

        }
        else{
            c = db.query(tabloAdi,sutunlar, null, null, null, null, null);

        }



        veriler.add("MASANO-" + "ADSOYAD-" + "TELEFON-" + "TARİH-"+"(ID)");

        while (c.moveToNext()) {


            veriler.add(c.getInt(c.getColumnIndex("masano")) + "-" + c.getString(c.getColumnIndex("adsoyad")) + "-" + c.getInt(c.getColumnIndex("telefon")) + "-" + c.getString(c.getColumnIndex("tarih"))+"-("+
                    c.getInt(c.getColumnIndex("id"))+")");


        }
        c.close();
        db.close();
        return veriler;

    }

    public int satirSayisi(){
        SQLiteDatabase db = this.getWritableDatabase();
        int satir=0;
        Cursor c =db.rawQuery(" select * from musteriTablosu ",null);
        satir=c.getCount();
        return satir;
    }


}

