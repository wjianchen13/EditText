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
     * 显示状态
     * @param
     * @return
     */
    public void onEmo(View v) {
        Intent it = new Intent();
        it.setClass(MainActivity.this, EmoActivity.class);
        startActivity(it);
    }
}
