package com.chocchic.sampleandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰를 찾아오기
        val txtView : TextView = findViewById(R.id.txtView)
        val btn : Button = findViewById(R.id.btn)

        btn.setOnClickListener(View.OnClickListener {
            txtView.setText("버튼을 눌렀습니다.")
        })
    }
}