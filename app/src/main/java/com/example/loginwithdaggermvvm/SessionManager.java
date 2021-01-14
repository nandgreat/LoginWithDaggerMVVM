package com.example.loginwithdaggermvvm;

import android.util.Log;

import com.example.loginwithdaggermvvm.model.db.User;
import com.example.loginwithdaggermvvm.view.login.LoginResource;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

@Singleton
public class SessionManager {

    private static final String TAG = "DaggerExample";

    // data
    private MediatorLiveData<LoginResource<User>> cachedUser = new MediatorLiveData<>();

    @Inject
    public SessionManager() {
    }

    public void authenticateWithId(final LiveData<LoginResource<User>> source) {
        if (cachedUser != null) {
            cachedUser.setValue(LoginResource.loading((User) null));
            cachedUser.addSource(source, new Observer<LoginResource<User>>() {
                @Override
                public void onChanged(LoginResource<User> userLoginResource) {
                    cachedUser.setValue(userLoginResource);
                    cachedUser.removeSource(source);

                    if (userLoginResource.status.equals(LoginResource.AuthStatus.ERROR)) {
                        cachedUser.setValue(LoginResource.<User>logout());
                    }
                }
            });
        }
    }

    public void logOut() {
        Log.d(TAG, "logOut: logging out...");
        cachedUser.setValue(LoginResource.<User>logout());
    }


    public LiveData<LoginResource<User>> getAuthUser() {
        return cachedUser;
    }

}