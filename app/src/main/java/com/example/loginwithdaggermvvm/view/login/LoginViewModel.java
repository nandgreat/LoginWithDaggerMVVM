package com.example.loginwithdaggermvvm.view.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginwithdaggermvvm.SessionManager;
import com.example.loginwithdaggermvvm.model.db.User;
import com.example.loginwithdaggermvvm.model.requests.LoginRequest;
import com.example.loginwithdaggermvvm.repository.UserRepository;
import com.example.loginwithdaggermvvm.util.ApiResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {

    private UserRepository repository;
    private SessionManager sessionManagerMain;

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();


    @Inject
    public LoginViewModel(UserRepository repository, SessionManager sessionManagerMain) {
        this.repository = repository;
        this.sessionManagerMain = sessionManagerMain;

    }

    public void userLogin(String email, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        disposables.add(repository.loginUser(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> responseLiveData.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
                ));

//        repository.loginUser(loginRequest);

    }


    public User getUser() {
        return repository.getCurrentUser();
    }

    public boolean checkLogin() {
        return repository.checkUserLogin();
    }

    public Flowable<User> getUserFromDB() {
        return repository.getUserFromDatabase();
    }

//    public void insert(){
//       repository.insertData();
//    }
//
//    public void updateFarmerData(final String firstName, final String middleName, final String address, final int id){
//       repository.updateFarmerData(firstName, middleName, address,id);
//    }

}
