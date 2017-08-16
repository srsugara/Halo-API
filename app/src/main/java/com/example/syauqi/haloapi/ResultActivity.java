package com.example.syauqi.haloapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syauqi.haloapi.model.User;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ResultActivity extends AppCompatActivity {

    @InjectView(R.id.tv_user)
    TextView usern;

    @InjectView(R.id.tv_message)
    TextView message;

    @InjectView(R.id.tv_age)
    TextView age;

    @InjectView(R.id.tv_sex)
    TextView sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ButterKnife.inject(this);
    }



}
