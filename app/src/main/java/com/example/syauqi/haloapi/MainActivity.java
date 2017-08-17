package com.example.syauqi.haloapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.syauqi.haloapi.api.HaloAPIService;
import com.example.syauqi.haloapi.api.UserAPIService;
import com.example.syauqi.haloapi.dagger.HaloAPIApplication;
import com.example.syauqi.haloapi.model.Result;
import com.example.syauqi.haloapi.model.User;
import com.example.syauqi.haloapi.util.Const;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog dialog;
    private ResultFragment resultFragment;
    private EventBus bus = EventBus.getDefault();

    @Inject @Named("service user")
    Retrofit retrofit1;

    @Inject @Named("service twohgo")
    Retrofit retrofit2;

    @InjectView(R.id.et_firstname)
    EditText firstname;

    @InjectView(R.id.et_lastname)
    EditText lastname;

    @InjectView(R.id.et_username)
    EditText username;

    @InjectView(R.id.et_message)
    EditText message;

    @InjectView(R.id.et_age)
    EditText age;

    @InjectView(R.id.et_sex)
    EditText sex;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        ((HaloAPIApplication) getApplication()).getMyComponent().inject(this);

        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setMessage("Loading");

        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.bt_getasmodel)
    public void getDataAsModel() {
        dialog.show();
        UserAPIService apiService = retrofit1.create(UserAPIService.class);
        Call<Result> result = apiService.getResultInfo();
        result.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                dialog.dismiss();
                try {
                    Toast.makeText(MainActivity.this, " response version " + response.body().getInfo().getVersion() + "\n response seed " + response.body().getInfo().getSeed(), Toast.LENGTH_SHORT).show();
                    System.out.println("response output version " + response.body().getInfo().getVersion());
                    System.out.println("response output seed " + response.body().getInfo().getSeed());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    @OnClick(R.id.bt_getasjson)
    public void getDataAsJSON() {
        dialog.show();
        UserAPIService apiService = retrofit1.create(UserAPIService.class);
        Call<ResponseBody> result = apiService.getResultAsJSON();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dialog.dismiss();
                try {
                    Toast.makeText(MainActivity.this, " response version " + response.body().string(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    @OnClick(R.id.bt_httpget)
    public void queryJSON() {
        dialog.show();
        HashMap<String, String> params = new HashMap<>();
        params.put("firstname", firstname.getText().toString());
        params.put("lastname", lastname.getText().toString());

        HaloAPIService apiService = retrofit1.create(HaloAPIService.class);
        Call<ResponseBody> result = apiService.getStoryOfMe(params);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dialog.dismiss();
                try {
                    if (response.body() != null)
                        Toast.makeText(MainActivity.this, " response message " + response.body().string(), Toast.LENGTH_LONG).show();
                    if (response.errorBody() != null)
                        Toast.makeText(MainActivity.this, " response message " + response.errorBody().string(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    @OnClick(R.id.bt_httppost)
    public void postMessage() {
        dialog.show();
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username.getText().toString());
        params.put("message", message.getText().toString());
        params.put("age", age.getText().toString());
        params.put("sex", sex.getText().toString());

        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        resultFragment = new ResultFragment();
        fTrans.replace(R.id.container, resultFragment);
        fTrans.commit();
        User user = new User();
        user.setUsername(username.getText().toString());
        user.setMessage(message.getText().toString());
        user.setAge(age.getText().toString());
        user.setSex(sex.getText().toString());

        bus.post(user);

        HaloAPIService apiService = retrofit2.create(HaloAPIService.class);
        Call<ResponseBody> result = apiService.postMessage(params);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dialog.dismiss();

                try {
                    if (response.body() != null) {
                        // Post
                        Toast.makeText(MainActivity.this, " response message " + response.body().string(), Toast.LENGTH_LONG).show();

                    }
                    if (response.errorBody() != null)
                        Toast.makeText(MainActivity.this, " response message " + response.errorBody().string(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
