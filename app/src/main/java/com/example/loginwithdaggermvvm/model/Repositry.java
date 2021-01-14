package com.example.loginwithdaggermvvm.model;

import android.util.Log;

import com.example.loginwithdaggermvvm.model.db.FarmersBean;
import com.example.loginwithdaggermvvm.model.db.FarmersRecordDao;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Singleton;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class Repositry {

    private Executor executor;
    private FarmersRecordDao farmersRecordDao;
    private ApiEndPoint apiEndPoint;

    public Repositry(FarmersRecordDao farmersRecordDao, Executor executor, ApiEndPoint apiEndPoint) {
        this.executor = executor;
        this.farmersRecordDao = farmersRecordDao;
        this.apiEndPoint = apiEndPoint;
    }

    public void insertData() {

        apiEndPoint.getPostCall()
                .enqueue(new Callback<Records>() {
                    @Override
                    public void onResponse(Call<Records> call, final Response<Records> response) {

                        executor.execute(new Runnable() {
                            @Override
                            public void run() {

                                Records.DataBean dataBean = response.body().getData();

                                for (int i = 0; i <= 9; i++) {

                                    farmersRecordDao.insert(dataBean.getFarmers().get(i));

                                }

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Records> call, Throwable t) {
                        Log.i("ERROR", "onFailure: " + t.getMessage());
                    }
                });
    }

    public Flowable<List<FarmersBean>> getDataFromDatabase() {
        return farmersRecordDao.getDataFromDatabase();
    }

    public Flowable<FarmersBean> getSingleFarmer(int userid) {
        return farmersRecordDao.getSingleFarmer(userid);
    }


    public void updateFarmerData(final String firstName, final String middleName,
                                 final String address, final int id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {

                farmersRecordDao.updateNote(firstName, middleName, address, id);

            }
        });
    }
}
