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

public class FragmentC extends Fragment
{
    TextView tvxml;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v3 = inflater.inflate(R.layout.frag_c, container, false);
        savedInstanceState = getActivity().getIntent().getExtras();
        tvxml = (TextView) v3.findViewById(R.id.tvXml);
        int pos = savedInstanceState.getInt("ClickedPosition");
        String java[] = getResources().getStringArray(R.array.xml);
        tvxml.setText(java[pos]);
        return v3;
    }
}
