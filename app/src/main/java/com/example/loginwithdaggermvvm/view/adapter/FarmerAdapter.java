package com.example.loginwithdaggermvvm.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginwithdaggermvvm.R;
import com.example.loginwithdaggermvvm.model.db.FarmersBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FarmerAdapter extends RecyclerView.Adapter<FarmerAdapter.ViewHolder> {

    private List<FarmersBean> farmersBeanList;
    private Context context;

    public FarmerAdapter(Context context) {
        this.context = context;
    }


    public void setList(List<FarmersBean> farmersBeanList) {
        this.farmersBeanList = farmersBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflate, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        FarmersBean farmersBean = farmersBeanList.get(position);

        Picasso.with(context)
                .load("https://s3-eu-west-1.amazonaws.com/agromall-storage/" + farmersBean.getPassportPhoto())
                .noFade()
                .into(holder.farmers_image);
        holder.farmers_name_txt.setText(farmersBean.getSurname() + " " + farmersBean.getFirstName() + " " + farmersBean.getMiddleName());
        holder.farmers_gender_txt.setText(farmersBean.getGender());
        holder.farmers_nationality_txt.setText(farmersBean.getNationality());
        holder.farmers_number_txt.setText(farmersBean.getMobileNo());

        holder.main_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FarmersBean farmersBean = farmersBeanList.get(position);
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("userid", farmersBean.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (farmersBeanList != null && farmersBeanList.size() != 0) {
            return farmersBeanList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView farmers_name_txt, farmers_gender_txt, farmers_number_txt, farmers_nationality_txt;
        private ImageView farmers_image;
        private ConstraintLayout main_lyt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            main_lyt = itemView.findViewById(R.id.main_lyt);
            farmers_image = itemView.findViewById(R.id.farmers_image);
            farmers_name_txt = itemView.findViewById(R.id.farmers_name);
            farmers_gender_txt = itemView.findViewById(R.id.farmers_gender);
            farmers_number_txt = itemView.findViewById(R.id.farmers_number);
            farmers_nationality_txt = itemView.findViewById(R.id.farmers_nationality);
        }
    }
}
