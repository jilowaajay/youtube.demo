package com.example.emran.againproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by emran on 6/7/17.
 */

public class FragmentA extends Fragment
{
    TextView tvDescription;
    Communicator c;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v1 = inflater.inflate(R.layout.frag_a, container, false);
        tvDescription = (TextView) v1.findViewById(R.id.tvDescription);
        c.comm();
        return v1;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        c = (Communicator) context;
    }

    public void sendData_A(int pos)
    {
        String[] descriptions = getResources().getStringArray(R.array.description);
        tvDescription.setText(descriptions[pos]);
    }

    public interface Communicator
    {
        public void comm();
    }
}
