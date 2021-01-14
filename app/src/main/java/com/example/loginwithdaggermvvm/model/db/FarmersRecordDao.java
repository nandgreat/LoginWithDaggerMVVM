package com.example.loginwithdaggermvvm.model.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface FarmersRecordDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FarmersBean farmersBean);

    @Query("SELECT * FROM FarmersBean")
    Flowable<List<FarmersBean>> getDataFromDatabase();


    @Query("SELECT * FROM farmersbean WHERE id=:userid")
    Flowable<FarmersBean> getSingleFarmer(int userid);

    @Query("UPDATE farmersbean SET firstName=:firstName,middleName=:middleName,address=:address WHERE id=:id")
    void updateNote(String firstName, String middleName, String address, int id);

}
