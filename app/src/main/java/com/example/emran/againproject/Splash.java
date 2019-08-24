package com.example.emran.againproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

/**
 * Created by emran on 11/7/17.
 */

public class Splash extends Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Thread()
        {
            @Override
            public void run()
            {
                super.run();
                try
                {
                    Thread.sleep(4000);
                    startActivity(new Intent(Splash.this, AfterFlash.class));
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        this.finish();
    }
}
