package com.example.loginwithdaggermvvm.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.loginwithdaggermvvm.MainActivity;
import com.example.loginwithdaggermvvm.R;
import com.example.loginwithdaggermvvm.di.module.AppModule;
import com.example.loginwithdaggermvvm.di.module.NetworkModule;
import com.example.loginwithdaggermvvm.model.db.User;
import com.example.loginwithdaggermvvm.view.adapter.FarmerAdapter;
import com.example.loginwithdaggermvvm.viewmodel.AppViewModel;
import com.example.loginwithdaggermvvm.viewmodel.ViewModelFactory;
import com.example.loginwithdaggermvvm.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private LoginViewModel appViewModel;

    private User user;

    @Inject
    ViewModelProviderFactory providerFactory;

    private FarmerAdapter farmerAdapter;

    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appViewModel = ViewModelProviders.of(this, providerFactory).get(LoginViewModel.class);

        if (appViewModel.checkLogin()) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }


    public void onLoginPressed(View view) {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

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

//        compositeDisposable.add(
//                appViewModel.getUserFromDB()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Consumer<User>() {
//                            @Override
//                            public void accept(User user) throws Exception {
//                                if (user != null) {
//                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                }
//                            }
//                        }, new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable throwable) throws Exception {
//                                Log.d(TAG, "accept: " + throwable.getMessage());
//
//                            }
//                        }));

    }

}
