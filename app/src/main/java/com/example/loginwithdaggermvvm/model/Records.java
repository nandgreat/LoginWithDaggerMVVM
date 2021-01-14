package com.example.loginwithdaggermvvm.model;

import com.example.loginwithdaggermvvm.model.db.FarmersBean;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Records {


    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("totalRec")
        private int totalRec;
        @SerializedName("imageBaseUrl")
        private String imageBaseUrl;
        @SerializedName("farmers")
        private List<FarmersBean> farmers;

        public int getTotalRec() {
            return totalRec;
        }

        public void setTotalRec(int totalRec) {
            this.totalRec = totalRec;
        }

        public String getImageBaseUrl() {
            return imageBaseUrl;
        }

        public void setImageBaseUrl(String imageBaseUrl) {
            this.imageBaseUrl = imageBaseUrl;
        }

        public List<FarmersBean> getFarmers() {
            return farmers;
        }

        public void setFarmers(List<FarmersBean> farmers) {
            this.farmers = farmers;
        }

    }
}
