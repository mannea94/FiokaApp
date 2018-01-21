package com.example.manne.fiokaapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manne.fiokaapp.PhotoAdapter;
import com.example.manne.fiokaapp.R;
import com.example.manne.fiokaapp.api.ApiService;
import com.example.manne.fiokaapp.api.RestApi;
import com.example.manne.fiokaapp.model.PhotoData;
import com.example.manne.fiokaapp.model.PhotoModel;
import com.example.manne.fiokaapp.model2.UserPhoto;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manne on 11.1.2018.
 */

public class Fragment_Photos extends Fragment {
    public Unbinder mUnBinder;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    PhotoAdapter photoAdapter;
    PhotoModel photoModel;
    UserPhoto userPhoto;

    RestApi api;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.search_field)
    EditText search;

    @BindView(R.id.tipText)
    TextView tipText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photos, null);
        mUnBinder = ButterKnife.bind(this, view);
        photoModel = new PhotoModel();
        progressBar.setVisibility(View.VISIBLE);
        userPhoto = new UserPhoto();
        api = new RestApi(getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            if(search.getText().length()>=3){
                progressBar.setVisibility(View.VISIBLE);
                searchPhotos(search.getText().toString());
            }

            }
        });



        api.checkInternet(new Runnable() {
            @Override
            public void run() {
                Call<PhotoModel> call = api.getPhotos("fresh_today");
                call.enqueue(new Callback<PhotoModel>() {
                    @Override
                    public void onResponse(Call<PhotoModel> call, Response<PhotoModel> response) {
                        if (response.code() == 200) {
                            photoModel = response.body();
                            photoAdapter = new PhotoAdapter(getActivity(), photoModel);
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setAdapter(photoAdapter);
                            tipText.setVisibility(View.GONE);
                        } else if (response.code() == 401) {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<PhotoModel> call, Throwable t) {
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        tipText.setVisibility(View.VISIBLE);
                    }
                });

//                Call<UserPhoto> call = api.getPhotos("fresh_today");
//                call.enqueue(new Callback<UserPhoto>() {
//                    @Override
//                    public void onResponse(Call<UserPhoto> call, Response<UserPhoto> response) {
//                        if (response.code() == 200) {
//                            userPhoto = response.body();
//                            photoAdapter = new PhotoAdapter(getActivity(), userPhoto);
//                            progressBar.setVisibility(View.GONE);
//                            recyclerView.setAdapter(photoAdapter);
//                            tipText.setVisibility(View.GONE);
//                        } else if (response.code() == 401) {
//                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.GONE);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<UserPhoto> call, Throwable t) {
//                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//                        progressBar.setVisibility(View.GONE);
//                        tipText.setVisibility(View.VISIBLE);
//                    }
//                });

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
                refreshRecyclerView();
            }

        });




//        Call<PhotoModel> call = api.getPhotos("fresh_today");
//        call.enqueue(new Callback<PhotoModel>() {
//            @Override
//            public void onResponse(Call<PhotoModel> call, Response<PhotoModel> response) {
//                if (response.code() == 200) {
//                    photoModel = response.body();
//                    photoAdapter = new PhotoAdapter(getActivity(), photoModel);
//                    recyclerView.setAdapter(photoAdapter);
//                } else if (response.code() == 401) {
//                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PhotoModel> call, Throwable t) {
//                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//            }
//        });

//        Call<UserPhoto> call = api.getPhotos("fresh_today");
//        call.enqueue(new Callback<UserPhoto>() {
//            @Override
//            public void onResponse(Call<UserPhoto> call, Response<UserPhoto> response) {
//                if (response.code() == 200) {
//                    userPhoto = response.body();
//                    photoAdapter = new PhotoAdapter(getActivity(), userPhoto);
//                    progressBar.setVisibility(View.GONE);
//                    recyclerView.setAdapter(photoAdapter);
//                } else if (response.code() == 401) {
//                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserPhoto> call, Throwable t) {
//                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//            }
//        });


        return view;
    }

    public void refreshRecyclerView(){

        api.checkInternet(new Runnable() {
            @Override
            public void run() {
                Call<PhotoModel> call = api.getPhotos("fresh_today");
                call.enqueue(new Callback<PhotoModel>() {
                    @Override
                    public void onResponse(Call<PhotoModel> call, Response<PhotoModel> response) {
                        if (response.code() == 200) {
                            photoModel = response.body();
                            photoAdapter = new PhotoAdapter(getActivity(), photoModel);
                            recyclerView.setAdapter(photoAdapter);

                        } else if (response.code() == 401) {
                            photoAdapter.setItems(new PhotoModel().photos);
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            tipText.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<PhotoModel> call, Throwable t) {
                        photoAdapter.setItems(new PhotoModel().photos);
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        tipText.setVisibility(View.VISIBLE);
                    }
                });

//        Call<UserPhoto> call = api.getPhotos("fresh_today");
//        call.enqueue(new Callback<UserPhoto>() {
//            @Override
//            public void onResponse(Call<UserPhoto> call, Response<UserPhoto> response) {
//                if (response.code() == 200) {
//                    userPhoto = response.body();
//                    photoAdapter = new PhotoAdapter(getActivity(), userPhoto);
//                    recyclerView.setAdapter(photoAdapter);
//                } else if (response.code() == 401) {
//                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//                    tipText.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserPhoto> call, Throwable t) {
//                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//                tipText.setVisibility(View.VISIBLE);
//            }
//        });

            }
        });




    }

    public void searchPhotos(final String string){
        api.checkInternet(new Runnable() {
            @Override
            public void run() {

                Call<PhotoModel> call = api.getPhotosSearch(string);
                call.enqueue(new Callback<PhotoModel>() {
                    @Override
                    public void onResponse(Call<PhotoModel> call, Response<PhotoModel> response) {
                        if (response.code() == 200) {
                            photoModel = response.body();
//                            photoAdapter.setItems(new PhotoModel().photos);
                            photoAdapter = new PhotoAdapter(getActivity(), photoModel);
                            recyclerView.setAdapter(photoAdapter);
                            tipText.setVisibility(View.INVISIBLE);
                            progressBar.setVisibility(View.GONE);
                            if(photoModel.photos == null || photoModel.photos.size()==0){
                                tipText.setVisibility(View.VISIBLE);
                                tipText.setText("No such photos");
                            }
                        } else if (response.code() == 401) {
//                            photoAdapter.setItems(new UserPhoto().photos);
                            photoAdapter = new PhotoAdapter(getActivity(), photoModel);
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            tipText.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<PhotoModel> call, Throwable t) {
//                        photoAdapter.setItems(new UserPhoto().photos);
                        photoAdapter = new PhotoAdapter(getActivity(), photoModel);
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        tipText.setVisibility(View.VISIBLE);
                    }
                });

//                Call<UserPhoto> call = api.getPhotosSearch(string);
//                call.enqueue(new Callback<UserPhoto>() {
//                    @Override
//                    public void onResponse(Call<UserPhoto> call, Response<UserPhoto> response) {
//                        if (response.code() == 200) {
//                            userPhoto = response.body();
////                            photoAdapter.setItems(new UserPhoto().photos);
//                            photoAdapter = new PhotoAdapter(getActivity(), userPhoto);
//                            recyclerView.setAdapter(photoAdapter);
//                            tipText.setVisibility(View.INVISIBLE);
//                            progressBar.setVisibility(View.GONE);
//                            if(userPhoto.photos == null || userPhoto.photos.size()==0){
//                                tipText.setVisibility(View.VISIBLE);
//                                tipText.setText("No such photos");
//                            }
//                        } else if (response.code() == 401) {
////                            photoAdapter.setItems(new UserPhoto().photos);
//                            photoAdapter = new PhotoAdapter(getActivity(), userPhoto);
//                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.GONE);
//                            tipText.setVisibility(View.VISIBLE);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<UserPhoto> call, Throwable t) {
////                        photoAdapter.setItems(new UserPhoto().photos);
//                        photoAdapter = new PhotoAdapter(getActivity(), userPhoto);
//                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//                        progressBar.setVisibility(View.GONE);
//                        tipText.setVisibility(View.VISIBLE);
//                    }
//                });

            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
}
