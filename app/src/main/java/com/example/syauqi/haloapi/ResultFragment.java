package com.example.syauqi.haloapi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syauqi.haloapi.model.User;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    @InjectView(R.id.tv_user)
    TextView usern;

    @InjectView(R.id.tv_message)
    TextView message;

    @InjectView(R.id.tv_age)
    TextView age;

    @InjectView(R.id.tv_sex)
    TextView sex;
    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_result, container, false);

        ButterKnife.inject(this,view);
        EventBus.getDefault().register(this);

        return view;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onEvent(User user){
        if(user != null){
            usern.setText("Username : "+user.getUsername());
            message.setText("Message : "+user.getMessage());
            age.setText("Age : "+user.getAge());
            sex.setText("Sex : "+user.getSex());
        }
    }
}
