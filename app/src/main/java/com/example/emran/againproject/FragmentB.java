package com.example.emran.againproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by emran on 6/7/17.
 */

public class FragmentB extends Fragment
{
    TextView tvJava;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v2 = inflater.inflate(R.layout.frag_b, container, false);
        tvJava = (TextView) v2.findViewById(R.id.tvJava);
        savedInstanceState = getActivity().getIntent().getExtras();
        int pos = savedInstanceState.getInt("ClickedPosition");
        String java[] = getResources().getStringArray(R.array.java);
        tvJava.setText(java[pos]);
        return v2;
    }
}
