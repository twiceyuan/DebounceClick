package com.twiceyuan.debounceclick.library;

import android.view.View;

/**
 * Created by twiceYuan on 5/22/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
public interface Callback {

    void call(View view, ClickEvent event);
}
