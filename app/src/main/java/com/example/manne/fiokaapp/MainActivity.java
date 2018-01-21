package com.example.manne.fiokaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.manne.fiokaapp.Fragments.Fragment_Home;
import com.example.manne.fiokaapp.Fragments.Fragment_Movies;
import com.example.manne.fiokaapp.Fragments.Fragment_Notification;
import com.example.manne.fiokaapp.Fragments.Fragment_Photos;
import com.example.manne.fiokaapp.Fragments.Fragment_Settings;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.pager)
    ViewPager pager;
//    @BindView(R.id.imageView)
//    ImageView imageView;
//    @BindView(R.id.ime)
//    TextView ime;
//    @BindView(R.id.textView)
//    TextView textView;
//    @BindView(R.id.header)
//    LinearLayout header;

    TextView ime;
    TextView textView;
    ImageView imageView;
    LinearLayout header;

    FragmentAdapter fragmentAdapter;

    DrawerLayout drawer;

    String [] activityTitles;

    int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityTitles=getResources().getStringArray(R.array.string_array);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        ime = (TextView) header.findViewById(R.id.ime);
        textView = (TextView) header.findViewById(R.id.textView);
        imageView = (ImageView) header.findViewById(R.id.imageView);
        header = (LinearLayout) header.findViewById(R.id.header);

        ime.setText("Marjan Andonov");
        textView.setText("marjan.adnonov@gmail.com");

//        imageView.setImageResource(R.mipmap.ic_launcher_round);

        Picasso.with(this)
                .load(R.mipmap.ic_launcher_round)
//                .fit()
//                .centerInside()
                .into(imageView);



        setUpViewPager(pager);

    }

    public void setUpViewPager(ViewPager upViewPager){
        fragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager());
        fragmentAdapter.addFragment(new Fragment_Home());
        fragmentAdapter.addFragment(new Fragment_Photos());
        fragmentAdapter.addFragment(new Fragment_Movies());
        fragmentAdapter.addFragment(new Fragment_Notification());
        fragmentAdapter.addFragment(new Fragment_Settings());
        pager.setAdapter(fragmentAdapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Tri tocki desno sto se
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        if(index==0){
//            getMenuInflater().inflate(R.menu.main, menu);
//        }
//
//        if(index==3){
//            getMenuInflater().inflate(R.menu.notifications, menu);
//        }

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setToolbarTitle(){
        getSupportActionBar().setTitle(activityTitles[index]);
        invalidateOptionsMenu();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            index=0;
            pager.setCurrentItem(index);


        } else if (id == R.id.nav_gallery) {
            index=1;
            pager.setCurrentItem(index);

        } else if (id == R.id.nav_movies) {
            index=2;
            pager.setCurrentItem(index);

        } else if (id == R.id.nav_notifications) {
            index=3;
            pager.setCurrentItem(index);

        } else if (id == R.id.nav_settings) {
            index=4;
            pager.setCurrentItem(index);

        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);


        }
        setToolbarTitle();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
