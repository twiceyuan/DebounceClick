package com.twiceyuan.debounceclick.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.twiceyuan.debounceclick.R;
import com.twiceyuan.debounceclick.library.Callback;
import com.twiceyuan.debounceclick.library.ClickEvent;
import com.twiceyuan.debounceclick.library.DebounceClick;

public class MainActivity extends AppCompatActivity {

    private ClickEvent mEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = (TextView) findViewById(R.id.text);
        assert text != null;

        DebounceClick.onClick(findViewById(R.id.button1), new View.OnClickListener() {
            @Override public void onClick(View v) {
                text.append(String.format("Button 1 被点击 => %s\n", System.currentTimeMillis()));
                if (mEvent != null) {
                    mEvent.done();
                }
            }
        });

        DebounceClick.onClick(findViewById(R.id.button2), 2000, new View.OnClickListener() {
            @Override public void onClick(View v) {
                text.append(String.format("Button 2 被点击 => %s\n", System.currentTimeMillis()));
                if (mEvent != null) {
                    mEvent.done();
                }
            }
        });

        DebounceClick.onClick(findViewById(R.id.button3), new Callback() {
            @Override public void call(View view, ClickEvent event) {
                text.append(String.format("Button 3 被点击 => %s\n", System.currentTimeMillis()));
                mEvent = event;
            }
        });
    }
}
