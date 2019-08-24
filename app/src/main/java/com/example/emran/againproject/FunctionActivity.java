package com.example.emran.againproject;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FunctionActivity extends FragmentActivity implements FragmentA.Communicator
{
    FragmentManager fm;
    Button btnDescription, btnJava, btnXml;
    TextView tvTopic;

    @Override
    protected void onCreate(Bundle bundle)                                                          //onCreateMethod
    {
        super.onCreate(bundle);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_function);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String[] str = getResources().getStringArray(R.array.topicNames);
        bundle = getIntent().getExtras();
        int pos = bundle.getInt("ClickedPosition");

        tvTopic = (TextView) findViewById(R.id.tvTopic);
        final Animation topicAnimation = AnimationUtils.loadAnimation(this, R.anim.topic_animations_translate);
        tvTopic.startAnimation(topicAnimation);
        tvTopic.setText(str[pos]);

        tvTopic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final Animation topicAnimation = AnimationUtils.loadAnimation(FunctionActivity.this, R.anim.topic_animations_focus);
                tvTopic.startAnimation(topicAnimation);
            }
        });

        initializeViewControls();
        int[] present_java = getResources().getIntArray(R.array.integer_java);
        int[] present_xml = getResources().getIntArray(R.array.integer_xml);
        if (present_java[pos] == 0)
        {
            btnJava.setVisibility(View.GONE);
        }
        if (present_xml[pos] == 0)
        {
            btnXml.setVisibility(View.GONE);
        }

        eventHandling();
    }

    private void initializeViewControls()                                                           //Initialization of variables
    {
        btnDescription = (Button) findViewById(R.id.btnDescription);
        btnJava = (Button) findViewById(R.id.btnJava);
        btnXml = (Button) findViewById(R.id.btnXml);
        btnDescription.setBackgroundResource(R.drawable.after_click_border);
        btnDescription.setTextColor(Color.WHITE);
        btnJava.setBackgroundResource(R.drawable.border);
        btnJava.setTextColor(Color.parseColor("#FF3CBCFD"));
        btnXml.setBackgroundResource(R.drawable.border);
        btnXml.setTextColor(Color.parseColor("#FF3CBCFD"));
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        FragmentA fa = new FragmentA();
        ft.add(R.id.ll1, fa, "A");
        ft.commit();
    }

    private void eventHandling()                                                                    //Button event Handling
    {
        final Animation btnAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation);
        btnDescription.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                view.startAnimation(btnAnimation);
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.ll1, new FragmentA(), "A");
                btnDescription.setBackgroundResource(R.drawable.after_click_border);
                btnDescription.setTextColor(Color.WHITE);
                btnJava.setBackgroundResource(R.drawable.border);
                btnJava.setTextColor(Color.parseColor("#FF3CBCFD"));
                btnXml.setBackgroundResource(R.drawable.border);
                btnXml.setTextColor(Color.parseColor("#FF3CBCFD"));
                ft.commit();
            }
        });
        btnJava.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                view.startAnimation(btnAnimation);
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.ll1, new FragmentB(), "B");
                btnJava.setBackgroundResource(R.drawable.after_click_border);
                btnJava.setTextColor(Color.WHITE);
                btnDescription.setBackgroundResource(R.drawable.border);
                btnDescription.setTextColor(Color.parseColor("#FF3CBCFD"));
                btnXml.setBackgroundResource(R.drawable.border);
                btnXml.setTextColor(Color.parseColor("#FF3CBCFD"));
                ft.commit();
            }
        });
        btnXml.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                view.startAnimation(btnAnimation);
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.ll1, new FragmentC(), "C");
                btnXml.setBackgroundResource(R.drawable.after_click_border);
                btnXml.setTextColor(Color.WHITE);
                btnJava.setBackgroundResource(R.drawable.border);
                btnJava.setTextColor(Color.parseColor("#FF3CBCFD"));
                btnDescription.setBackgroundResource(R.drawable.border);
                btnDescription.setTextColor(Color.parseColor("#FF3CBCFD"));
                ft.commit();
            }
        });
    }

    @Override
    public void comm()
    {
        Bundle b = getIntent().getExtras();
        int pos = b.getInt("ClickedPosition");
        FragmentA fa = (FragmentA) fm.findFragmentByTag("A");
        fa.sendData_A(pos);
    }
}