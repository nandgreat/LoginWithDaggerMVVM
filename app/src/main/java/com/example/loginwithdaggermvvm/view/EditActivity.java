package com.example.loginwithdaggermvvm.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.loginwithdaggermvvm.R;
import com.example.loginwithdaggermvvm.di.componet.DaggerAppComponent;
import com.example.loginwithdaggermvvm.di.module.AppModule;
import com.example.loginwithdaggermvvm.di.module.NetworkModule;
import com.example.loginwithdaggermvvm.model.db.FarmersBean;
import com.example.loginwithdaggermvvm.viewmodel.AppViewModel;
import com.example.loginwithdaggermvvm.viewmodel.ViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EditActivity extends AppCompatActivity {

    private AppViewModel appViewModel;
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    CompositeDisposable compositeDisposable;
    private TextView reg_number, bvn, dob, occupation, city;
    private EditText fname, mname, address;
    private int userid;
    private FloatingActionButton updateData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        DaggerAppComponent.builder().appModule(new AppModule()).build()
                .appComponent(new NetworkModule(this)).inject(this);

        appViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AppViewModel.class);

        setTitle("Update");
        reg_number = findViewById(R.id.reg_val);
        city = findViewById(R.id.city_val);
        updateData = findViewById(R.id.btn_update);
        occupation = findViewById(R.id.ocup_val);
        bvn = findViewById(R.id.bvn_val);
        dob = findViewById(R.id.dob_val);
        fname = findViewById(R.id.edit_fname);
        mname = findViewById(R.id.mname_edit);
        address = findViewById(R.id.address_edit);

        userid = getIntent().getIntExtra("userid", 0);


        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                appViewModel.updateFarmerData(fname.getText().toString(),
//                        mname.getText().toString(),
//                        address.getText().toString(),userid);
//                finish();
            }
        });
    }

    public void setValue(String registrationNumber, String bvn_num, String do_b,
                         String f_name, String m_name, String address_loc, String occupa_tion, String cityVal) {
        reg_number.setText(registrationNumber);
        bvn.setText(bvn_num);
        city.setText(cityVal);
        dob.setText(do_b);
        occupation.setText(occupa_tion);
        fname.setText(f_name);
        mname.setText(m_name);
        address.setText(address_loc);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        compositeDisposable.add(appViewModel.getSingleFarmer(userid)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<FarmersBean>() {
//                    @Override
//                    public void accept(FarmersBean farmersBean) throws Exception {
//                        setValue(farmersBean.getRegNo(),farmersBean.getBvn(),
//                                farmersBean.getDob(),farmersBean.getFirstName(),farmersBean.getMiddleName(),farmersBean.getAddress(),farmersBean.getOccupation(),farmersBean.getCity());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                }));

    }


    @Override
    protected void onStop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
        super.onStop();
    }
}
