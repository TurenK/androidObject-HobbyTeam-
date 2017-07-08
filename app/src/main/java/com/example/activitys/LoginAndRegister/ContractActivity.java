package com.example.activitys.LoginAndRegister;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.activitys.R;

/**
 * Created by TurenK on 2017/7/6.
 */
public class ContractActivity extends Activity {
    Button log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);
        log = (Button) findViewById(R.id.back);
        log.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ContractActivity.this.finish();
            }
        });
    }
}