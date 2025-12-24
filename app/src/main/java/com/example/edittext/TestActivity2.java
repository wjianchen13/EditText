package com.example.edittext;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivity2 extends AppCompatActivity {

    private EditText edtvTest;
    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        edtvTest = (EditText) findViewById(R.id.edtv_test);
        edtvTest.setImeOptions(EditorInfo.IME_ACTION_SEND);
        edtvTest.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        edtvTest.setSingleLine(true);
        tvTest = (TextView) findViewById(R.id.tv_test);
    }

    /**
     * 显示状态
     * @param
     * @return
     */
    public void onTest(View v) {
        String text2 = edtvTest.getText().toString();
        tvTest.setText(text2);
    }
}
