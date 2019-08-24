package com.example.emran.againproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by emran on 10/7/17.
 */

public class BeforeMain extends Activity
{
    TextView tvShowName;
    Button btnSubmit2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.before_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tvShowName = (TextView) findViewById(R.id.tvShowName);
        savedInstanceState = getIntent().getExtras();
        String name = savedInstanceState.getString("name");

        SharedPreferences sp = getSharedPreferences("UserName", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("getUserName", name);
        editor.commit();

        tvShowName.setText("Welcome, " + name);
        btnSubmit2 = (Button) findViewById(R.id.btnSubmit2);
        btnSubmit2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(BeforeMain.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        this.finish();
    }
}
