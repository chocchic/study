package com.chocchic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    // AdapterView 출력을 위한 속성
    // ** 셀을 만들기 위해서 필요한 정보 ** //
    // layoutInflater를 만들기 위한 문맥 정보
    Context context;
    // xml로 만든 레이아웃을 뷰로 전개하기 위한 객체
    LayoutInflater inflater;
    // layout File의 파일의 이름
    int layout;
    // 출력할 데이터의 List
    List<Item> data;

    // 생성자
    public ItemAdapter(Context context, List<Item> data, int layout){
        super();
        this.context = context;
        this.data = data;
        this.layout = layout;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    // 행의 개수를 설정하는 메서드
    @Override
    public int getCount() {
        return data.size();
    }
    // 항목에 보여질 문자열을 설정하는 메서드
    // 아래 2개 메서드는 getCount에서 리턴한 개수만큼 반복해서 수행
    @Override
    public Object getItem(int i) {
        return data.get(i).getItemname();
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getItemid();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
    // 아이폰은 implements, 안드로이드는 extends

}
