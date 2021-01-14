package com.example.loginwithdaggermvvm.di.auth;

import com.example.loginwithdaggermvvm.di.PerActivity;
import com.example.loginwithdaggermvvm.model.db.AppDatabase;
import com.example.loginwithdaggermvvm.model.db.FarmersRecordDao;
import com.example.loginwithdaggermvvm.model.db.User;
import com.example.loginwithdaggermvvm.model.db.UserDao;
import com.example.loginwithdaggermvvm.network.auth.AuthApi;
import com.example.loginwithdaggermvvm.repository.UserRepository;
import com.example.loginwithdaggermvvm.util.Helper;

import java.util.concurrent.Executor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    @AuthScope
    @Provides
    @Named("auth_user")
    static User someUser() {
        return new User();
    }


    @Provides
    @PerActivity
    public UserDao userDao(AppDatabase appDatabase) {
        return appDatabase.userDao();
    }

    @Provides
    @Singleton
    UserRepository getRepository(Executor executor, UserDao userDao, AuthApi apiEndPoint, Helper helper) {
        return new UserRepository(executor, userDao, apiEndPoint, helper);
    }

    @AuthScope
    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }
}
