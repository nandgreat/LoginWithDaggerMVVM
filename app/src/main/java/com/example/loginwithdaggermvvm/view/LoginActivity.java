package com.example.loginwithdaggermvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.loginwithdaggermvvm.MainActivity;
import com.example.loginwithdaggermvvm.R;
import com.example.loginwithdaggermvvm.di.componet.DaggerAppComponent;
import com.example.loginwithdaggermvvm.di.module.AppModule;
import com.example.loginwithdaggermvvm.di.module.NetworkModule;
import com.example.loginwithdaggermvvm.model.db.FarmersBean;
import com.example.loginwithdaggermvvm.model.db.User;
import com.example.loginwithdaggermvvm.view.adapter.FarmerAdapter;
import com.example.loginwithdaggermvvm.viewmodel.AppViewModel;
import com.example.loginwithdaggermvvm.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private AppViewModel appViewModel;
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    CompositeDisposable compositeDisposable;
    @Inject
    LinearLayoutManager linearLayoutManager;

    private FarmerAdapter farmerAdapter;

    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DaggerAppComponent.builder().appModule(new AppModule()).build()
                .appComponent(new NetworkModule(this)).inject(this);

        appViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AppViewModel.class);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }


    public void onLoginPressed(View view) {

        boolean isEmptyEmail = TextUtils.isEmpty(etEmail.getText().toString());
        boolean isEmptyPassword = TextUtils.isEmpty(etPassword.getText().toString());

        if (isEmptyEmail || isEmptyPassword) {
            etEmail.setError(isEmptyEmail ? "Email is required" : "");
            etPassword.setError(isEmptyPassword ? "Password is required" : "");

            return;
        }

        appViewModel.userLogin(etEmail.getText().toString(), etPassword.getText().toString());

    }

    @Override
    protected void onStart() {
        super.onStart();
        compositeDisposable.add(
                appViewModel.getUserFromDB()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<User>() {
                            @Override
                            public void accept(User user) throws Exception {
                                if (user != null) {
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d(TAG, "accept: " + throwable.getMessage());

                            }
                        }));

    }

}
