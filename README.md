CardTextWatcher
===============

CardTextWatcher 继承了 TextView的TextWatcher，具有TextWatcher的所有功能，同时针对现行银行，封装了银行卡格式
使用说明： 和TextWatcher用法一样，在

 txt.addTextChangedListener(new CardTextWatcher() {
            @Override
            public void onBeforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onOtherTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onAfterTextChanged(Editable s) {
                if (s.length() > 0) {
                    txt.setEnabled(true);
                }
            }
        });
