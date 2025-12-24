package com.example.edittext;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivity2 extends AppCompatActivity {

    private NoPasteEditText edtvTest;
    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        edtvTest = (NoPasteEditText) findViewById(R.id.edtv_test);
        edtvTest.setImeOptions(EditorInfo.IME_ACTION_SEND);
        edtvTest.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        edtvTest.setSingleLine(true);
        tvTest = (TextView) findViewById(R.id.tv_test);
        edtvTest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
