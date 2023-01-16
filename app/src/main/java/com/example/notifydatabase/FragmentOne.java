package com.example.notifydatabase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentOne extends Fragment {

    View view;
    EditText email;
    EditText phone;
    Button submit;
    private  DataBaseHelper dataBaseHelper;

    private  static  final String CHANNEL_ID = "APP_INNOVATIONS";
    private static final  String CHANNEL_NAME = "APP INNOVATIONS";
    private  static  final String CHANNEL_DESC = "JUST LEARNING";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, container, false);

        return view;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = view.findViewById(R.id.emailEditText);
        phone = view. findViewById(R.id.phoneNumberEditText);
        submit = view.findViewById(R.id.submitBtn);
        dataBaseHelper = new DataBaseHelper(requireContext());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription(CHANNEL_DESC);
            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addUsers();
               email.setText("");
               phone.setText("");
            }
        });
    }

    private void displayNotifications() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getContext(),CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications)
                .setContentTitle("Database Sqlite")
                .setContentText("Data added")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());

        Intent notificationIntent = new Intent(getActivity(), MainActivity.class);

        notificationIntent.putExtra("name","sample");
        notificationIntent.putExtra("phnNum","1234567890");
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pendingIntent;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getActivity
                    (getActivity(), 0, notificationIntent, PendingIntent.FLAG_MUTABLE);
        }
        else
        {
            pendingIntent = PendingIntent.getActivity
                    (getActivity(), 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);
        }
        notificationBuilder.setContentIntent(pendingIntent);
        notificationManagerCompat.notify(1,notificationBuilder.build());
    }

    private void addUsers() {
        String name = email.getText().toString().trim();
        String phoneNumber = phone.getText().toString().trim();
        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(phoneNumber)){
            Toast.makeText(getContext(), "Please enter the name and phone-number !", Toast.LENGTH_SHORT).show();
        }
        else if (dataBaseHelper != null && dataBaseHelper.addUser(name,phoneNumber) ){
            displayNotifications();
            Toast.makeText(getContext(), "Data Added as" + name + phoneNumber, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getContext(), "Data was not added", Toast.LENGTH_SHORT).show();
        }
    }


}