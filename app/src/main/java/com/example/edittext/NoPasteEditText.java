package com.example.edittext;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * 禁止粘贴的EditText
 * 禁止通过长按菜单粘贴和输入法粘贴板粘贴
 */
public class NoPasteEditText extends AppCompatEditText {

    private String textBeforeChange = "";
    private Handler handler = new Handler(Looper.getMainLooper());

    public NoPasteEditText(Context context) {
        super(context);
        init();
    }

    public NoPasteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NoPasteEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 监听文本变化，用于检测通过输入法粘贴板的粘贴操作
        addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textBeforeChange = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 检测是否是粘贴操作：
                // 一次性插入的文本长度超过3个字符，且没有删除原有文本（before == 0）
                // 正常输入通常是一个字符一个字符的，或者最多2-3个字符（如某些输入法的快捷输入）
                // 粘贴操作通常会一次性插入较多文本
                if (count > 3 && before == 0) {
                    final String originalText = textBeforeChange;
                    // 延迟检查并恢复，避免与输入法的其他操作冲突
                    handler.postDelayed(() -> {
                        String currentText = getText().toString();
                        // 如果当前文本比变化前多了超过3个字符，认为是粘贴操作
                        if (currentText.length() > originalText.length() && 
                            currentText.length() - originalText.length() > 3) {
                            // 恢复到粘贴前的状态
                            int selectionPos = getSelectionStart();
                            setText(originalText);
                            // 设置光标位置，但不超过原始文本长度
                            int newPos = Math.min(selectionPos, originalText.length());
                            setSelection(newPos);
                        }
                    }, 50);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        // 拦截长按菜单的粘贴操作
        if (id == android.R.id.paste) {
            return false;
        }
        return super.onTextContextMenuItem(id);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        // 创建自定义的InputConnection，拦截输入法的粘贴操作
        InputConnection inputConnection = super.onCreateInputConnection(outAttrs);
        if (inputConnection == null) {
            return null;
        }
        return new NoPasteInputConnection(inputConnection, true);
    }

    /**
     * 自定义InputConnection，拦截commitText方法来阻止粘贴
     */
    private class NoPasteInputConnection extends InputConnectionWrapper {

        public NoPasteInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        @Override
        public boolean commitText(CharSequence text, int newCursorPosition) {
            // 如果一次性提交的文本长度超过3个字符，认为是粘贴操作，拦截它
            // 正常输入通常是一个字符一个字符提交的，或者最多2-3个字符（如某些输入法的快捷输入）
            // 粘贴操作通常会一次性提交较多文本
            if (text != null && text.length() > 3) {
                return false;
            }
            return super.commitText(text, newCursorPosition);
        }

        @Override
        public boolean setComposingText(CharSequence text, int newCursorPosition) {
            // setComposingText通常用于输入法的组合文本（如拼音输入），这里不拦截
            // 因为正常的输入法需要使用这个方法
            return super.setComposingText(text, newCursorPosition);
        }
    }
}

