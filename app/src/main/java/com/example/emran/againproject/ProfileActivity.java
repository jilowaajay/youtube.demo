package com.example.emran.againproject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by emran on 11/7/17.
 */

public class ProfileActivity extends Activity
{
    TextView tvProfileUserName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        tvProfileUserName = findViewById(R.id.tvProfileUserName);

        SharedPreferences sp = getSharedPreferences("UserName", 0);
        String name = sp.getString("getUserName", null);
        tvProfileUserName.setText("" + name);
    }
}
