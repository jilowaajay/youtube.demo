package com.example.emran.againproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by emran on 10/7/17.
 */

public class AfterFlash extends Activity
{
    TextInputLayout til;
    EditText etName;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = getSharedPreferences("UserName", 0);
        String previousName = sp.getString("getUserName", null);
        if (previousName != null)
        {
            startActivity(new Intent(AfterFlash.this, MainActivity.class));
        }
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.after_flash);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        til = (TextInputLayout) findViewById(R.id.til);
        etName = (EditText) findViewById(R.id.name);
        etName.addTextChangedListener(new MyTextWatcher(etName));
        etName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                submitForm();
            }
        });

    }

    @Override
    protected void onStop()
    {
        super.onStop();
        this.finish();
    }

    private void submitForm()
    {
        if (!validateName())
        {
            return;
        }
        Intent i = new Intent(AfterFlash.this, BeforeMain.class);
        i.putExtra("name", etName.getText().toString());
        startActivity(i);
    }

    private boolean validateName()
    {
        if (etName.getText().toString().trim().isEmpty())
        {
            til.setError("Enter your full name");
            requestFocus(etName);
            return false;
        } else
        {
            til.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(EditText etName)
    {
        if (etName.requestFocus())
        {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher
    {
        EditText etName;

        public MyTextWatcher(EditText etName)
        {
            this.etName = etName;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
        {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
        {

        }

        @Override
        public void afterTextChanged(Editable editable)
        {
            validateName();
        }
    }
}
