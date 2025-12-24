package com.example.edittext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 表情测试
     * @param
     * @return
     */
    public void onTest1(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, EmoActivity.class);
        startActivity(it);
    }

    /**
     * EditText禁止粘贴
     * @param
     * @return
     */
    public void onTest2(View v) {
        startActivity(new Intent(MainActivity.this, TestActivity2.class));
    }




}
