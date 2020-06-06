package com.example.mycoffeetry;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AcceuilActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView featuredRecycler, viewedRecycler;
    RecyclerView.Adapter adapter;

    ImageView menuIcon;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.acceuil);

        featuredRecycler = findViewById(R.id.featured_recycler);
        viewedRecycler = findViewById(R.id.viewed_recycler);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);


        navigationDrawer();

        featuredRecycler();
        viewedRecycler();

    }

    private void navigationDrawer() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {



        switch (menuItem.getItemId()){

            case R.id.nav_popular :
                startActivity(new Intent(getApplicationContext(),PopularPlaces.class));
                break;

            case R.id.nav_home :
                startActivity(new Intent(getApplicationContext(),AcceuilActivity.class));
                break;

            case R.id.nav_profile :
                startActivity(new Intent(getApplicationContext(),UserProfile.class));
                break;

            case R.id.nav_logout :

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;


        }


        return true;
    }

    private void featuredRecycler(){

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedClass> featuredLocation = new ArrayList<>();

        featuredLocation.add(new FeaturedClass(R.drawable.starbucks,"StarBucks","kjfkhfj sdfnskdjfhnsjf ksjfhnsgir"));
        featuredLocation.add(new FeaturedClass(R.drawable.veniziaice,"Venizia Ice","kjfkhfj sdfnskdjfhnsjf ksjfhnsgir"));
        featuredLocation.add(new FeaturedClass(R.drawable.dipndip,"Dip N Dip","kjfkhfj sdfnskdjfhnsjf ksjfhnsgir"));
        featuredLocation.add(new FeaturedClass(R.drawable.segafredo,"SegaFredo","kjfkhfj sdfnskdjfhnsjf ksjfhnsgir"));


        adapter = new FeaturedAdapter(featuredLocation);
        featuredRecycler.setAdapter(adapter);

    }

    private void viewedRecycler(){

        viewedRecycler.setHasFixedSize(true);
        viewedRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<ViewedClass> viewedLocation = new ArrayList<>();

        viewedLocation.add(new ViewedClass(R.drawable.arabica,"Arabica Coffee","kjfkhfj sdfnskdjfhnsjf ksjfhnsgir"));
        viewedLocation.add(new ViewedClass(R.drawable.costacoffee,"Costa Coffee","kjfkhfj sdfnskdjfhnsjf ksjfhnsgir"));
        viewedLocation.add(new ViewedClass(R.drawable.starbucks,"StarBucks Coffee","kjfkhfj sdfnskdjfhnsjf ksjfhnsgir"));
        viewedLocation.add(new ViewedClass(R.drawable.mcafe,"McCafé","kjfkhfj sdfnskdjfhnsjf ksjfhnsgir"));



        adapter = new ViewedAdapter(viewedLocation);
        viewedRecycler.setAdapter(adapter);

    }

}
