package com.example.manne.fiokaapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manne.fiokaapp.model.PhotoData;
import com.example.manne.fiokaapp.model.PhotoModel;
import com.example.manne.fiokaapp.model2.BlogUser;
import com.example.manne.fiokaapp.model2.UserPhoto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by manne on 18.1.2018.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    Context context;
    List<PhotoData> photoData = new ArrayList<>();
    PhotoModel photoModel = new PhotoModel();
//    List<BlogUser> blogUser = new ArrayList<>();
//    UserPhoto userPhoto = new UserPhoto();

    public void setItems(List<PhotoData> photos){
        photoData=photos;
    }
//    public void setItems(List<BlogUser> blogUsers){
//        blogUser=blogUsers;
//    }
    public PhotoAdapter(Context context_, PhotoModel _photoModel /*UserPhoto _userPhoto*/){
        context=context_;
        photoModel=_photoModel;
//        userPhoto=_userPhoto;
    }


    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //Inflate the custom layout
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        //Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PhotoData photoDatas = photoModel.photos.get(position);
//        final BlogUser blogUsers = userPhoto.photos.get(position);
//        holder.earthDate.setText(blogUsers.getEarth_date());
        Picasso.with(context)
                .load(photoDatas.getImage_url())
                .fit()
                .centerInside()
                .into(holder.imageRaw);

//        Picasso.with(context)
//                .load(blogUsers.getImg_src())
//                .fit()
//                .centerInside()
//                .into(holder.imageRaw);

    }

    @Override
    public int getItemCount() {
        return photoModel.photos.size();
//        return userPhoto.photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imageRaw)
        ImageView imageRaw;

//        @BindView(R.id.earthDate)
//        TextView earthDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
