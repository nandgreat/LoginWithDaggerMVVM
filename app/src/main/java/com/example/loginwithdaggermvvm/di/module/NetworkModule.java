package com.example.loginwithdaggermvvm.di.module;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.loginwithdaggermvvm.di.PerActivity;
import com.example.loginwithdaggermvvm.model.ApiEndPoint;
import com.example.loginwithdaggermvvm.model.Repositry;
import com.example.loginwithdaggermvvm.model.db.AppDatabase;
import com.example.loginwithdaggermvvm.model.db.FarmersRecordDao;
import com.example.loginwithdaggermvvm.model.db.UserDao;
import com.example.loginwithdaggermvvm.network.auth.AuthApi;
import com.example.loginwithdaggermvvm.repository.UserRepository;
import com.example.loginwithdaggermvvm.util.Helper;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


@Module
public class NetworkModule {

    Context context;

    public NetworkModule(Context context) {
        this.context = context;
    }

    @Provides
    @PerActivity
    public AppDatabase appDatabase() {
        return Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "test.db")
                .build();
    }

    @Provides
    @PerActivity
    public UserDao postDao(AppDatabase appDatabase) {
        return appDatabase.userDao();
    }

    @Provides
    @PerActivity
    public Repositry repositry(FarmersRecordDao farmersRecordDao, Executor
            executor, ApiEndPoint apiEndPoint) {
        return new Repositry(farmersRecordDao, executor, apiEndPoint);
    }


    @Provides
    @PerActivity
    public UserRepository userRepository(Executor executor, UserDao userDao, AuthApi apiEndPoint, Helper helper) {
        return new UserRepository(executor, userDao, apiEndPoint, helper);
    }

    @Provides
    @PerActivity
    public CompositeDisposable compositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    public LinearLayoutManager linearLayoutManager() {
        return new LinearLayoutManager(context);
    }

}
