package com.chocchic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ItemDB extends SQLiteOpenHelper {

    // 생성자
    // Context는 어떤 정보를 저장한 객체를 의미
    // 안드로이드에서는 context를 매개변수로 하는 메서드가 많은데, Context를 대입하라고 하면 Activity클래스의 인스턴스를 대입하면 됩니다.
    
    public ItemDB(@Nullable Context context) {
        // 상위 클래스의 생성자 호출
        super(context, "item.db", null, 1);
    }
    
    // 앱을 설치할 때 호출되는 메서드
    // 여기서는 필요한 테이블을 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 테이블 생성 구문
        db.execSQL("create table item(itemid integer primary key, itemname, price integer, description, pictureurl, email)");
    }

    // 업그레이드될 때 호출되는 메서드
    // 여기서는 테이블을 삭제하고 새로 생성하는 코드를 주로 작성
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS item");
        onCreate(db);
    }
}
