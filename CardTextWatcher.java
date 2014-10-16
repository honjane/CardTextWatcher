
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * Created by april.tang on 2014/10/16.
 */
public abstract class CardTextWatcher implements TextWatcher {
    //银行卡格式处理，如：0000 1234 4567 7894 999
    int beforeTextLength = 0;
    int onTextLength = 0;
    boolean isChanged = false;

    int location = 0;// 记录光标的位置
    private char[] tempChar;
    private StringBuffer buffer = new StringBuffer();
    int konggeNumberB = 0;
    TextView view;
    public CartTextWatcher(TextView view) {
        this.view = view;

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        onBeforeTextChanged(s,start,count,after);
        beforeTextLength = s.length();
        if (buffer.length() > 0) {
            buffer.delete(0, buffer.length());
        }
        konggeNumberB = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                konggeNumberB++;
            }
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        onOtherTextChanged(s,start,before,count);
        onTextLength = s.length();
        buffer.append(s.toString());
        if (onTextLength == beforeTextLength || onTextLength <= 3 || isChanged) {
            isChanged = false;
            return;
        }
        isChanged = true;
    }

    @Override
    public void afterTextChanged(Editable s) {
        onAfterTextChanged(s);
        if (isChanged) {
            location = view.getSelectionEnd();
            int index = 0;
            while (index < buffer.length()) {
                if (buffer.charAt(index) == ' ') {
                    buffer.deleteCharAt(index);
                } else {
                    index++;
                }
            }

            index = 0;
            int konggeNumberC = 0;
            while (index < buffer.length()) {
                if ((index == 4 || index == 9 || index == 14 || index == 19)) {
                    buffer.insert(index, ' ');
                    konggeNumberC++;
                }
                index++;
            }

            if (konggeNumberC > konggeNumberB) {
                location += (konggeNumberC - konggeNumberB);
            }

            tempChar = new char[buffer.length()];
            buffer.getChars(0, buffer.length(), tempChar, 0);
            String str = buffer.toString();
            if (location > str.length()) {
                location = str.length();
            } else if (location < 0) {
                location = 0;
            }

            view.setText(str);
            Editable etable = view.getEditableText();
            Selection.setSelection(etable, location);
            isChanged = false;

        }


    }
    public abstract void onBeforeTextChanged(CharSequence s, int start, int count, int after);
    public abstract void onOtherTextChanged(CharSequence s, int start, int before, int count);
    public abstract void onAfterTextChanged(Editable s);

}
