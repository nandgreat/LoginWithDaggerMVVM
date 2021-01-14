package com.example.loginwithdaggermvvm.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginwithdaggermvvm.R;
import com.example.loginwithdaggermvvm.di.componet.DaggerAppComponent;
import com.example.loginwithdaggermvvm.di.module.AppModule;
import com.example.loginwithdaggermvvm.di.module.NetworkModule;
import com.example.loginwithdaggermvvm.model.db.FarmersBean;
import com.example.loginwithdaggermvvm.view.adapter.FarmerAdapter;
import com.example.loginwithdaggermvvm.viewmodel.AppViewModel;
import com.example.loginwithdaggermvvm.viewmodel.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private AppViewModel appViewModel;
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    CompositeDisposable compositeDisposable;
    @Inject
    LinearLayoutManager linearLayoutManager;

    private FarmerAdapter farmerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerAppComponent.builder().appModule(new AppModule()).build()
                .appComponent(new NetworkModule(this)).inject(this);

        appViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AppViewModel.class);

        setAdapter();

    }

    @Override
    protected void onStart() {
        super.onStart();
//        compositeDisposable.add(
//                appViewModel.getDataFromDatabase()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Consumer<List<FarmersBean>>() {
//                            @Override
//                            public void accept(List<FarmersBean> farmersBeans) throws Exception {
//
//                                if (farmersBeans.size()>1){
//                                    farmerAdapter.setList(farmersBeans);
//                                    farmerAdapter.notifyDataSetChanged();
//                                }else{
//
//                                    appViewModel.insert();
//                                }
//
//
//                            }
//                        }, new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable throwable) throws Exception {
//
//
//                            }
//                        }));
    }

    public void setAdapter() {
        farmerAdapter = new FarmerAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(farmerAdapter);
    }

    @Override
    protected void onStop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
        super.onStop();
    }
}
