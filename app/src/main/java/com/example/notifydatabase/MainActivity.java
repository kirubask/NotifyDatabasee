package com.example.notifydatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button fragmentOne;
    Button fragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getStringExtra("name") != null && getIntent().getStringExtra("phnNum") != null){
            Log.d("TAG", "onCreate: "+getIntent().getStringExtra("name")+" "+getIntent().getStringExtra("phnNum"));
            replaceFragment(FragmentTwo.newInstance(getIntent().getStringExtra("name"),getIntent().getStringExtra("phnNum")));
        }
        fragmentOne = findViewById(R.id.frameLayoutOne);
        fragmentTwo = findViewById(R.id.frameLayoutTwo);

        fragmentOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new FragmentOne());
            }
        });

        fragmentTwo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                replaceFragment(new FragmentTwo());

            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }
}