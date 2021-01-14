package com.example.loginwithdaggermvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.loginwithdaggermvvm.di.componet.DaggerAppComponent;
import com.example.loginwithdaggermvvm.di.module.AppModule;
import com.example.loginwithdaggermvvm.di.module.NetworkModule;
import com.example.loginwithdaggermvvm.model.db.User;
import com.example.loginwithdaggermvvm.view.LoginActivity;
import com.example.loginwithdaggermvvm.viewmodel.AppViewModel;
import com.example.loginwithdaggermvvm.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView textView;

    private AppViewModel appViewModel;

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        textView = findViewById(R.id.textView);


        DaggerAppComponent.builder().appModule(new AppModule()).build()
                .appComponent(new NetworkModule(this)).inject(this);


        appViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AppViewModel.class);
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

                                    textView.setText("Welcome " + user.getName());
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
