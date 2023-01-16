package com.example.notifydatabase;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FragmentTwo extends Fragment {

    View view;
    static String name1,phnNum1;

    public static FragmentTwo newInstance(String name, String phnNum) {
        name1 = name;
        phnNum1 = phnNum;
        return new FragmentTwo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_two, container, false);
        Toast.makeText(getContext(), name1+"   "+phnNum1, Toast.LENGTH_SHORT).show();
        return view ;
    }
}