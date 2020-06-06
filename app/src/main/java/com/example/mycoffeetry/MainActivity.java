package com.example.mycoffeetry;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    ViewPager slideViewPager;
    LinearLayout dotLayout;

    TextView[] dots;

    SliderAdapter sliderAdapter;

    Button getStarted;

    Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        slideViewPager = (ViewPager) findViewById(R.id.slideViewer);
        dotLayout = (LinearLayout) findViewById(R.id.dotsLayout);
        getStarted = findViewById(R.id.started_btn);

        sliderAdapter = new SliderAdapter(this);

        slideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        slideViewPager.addOnPageChangeListener(viewListener);

    }

    public void addDotsIndicator(int position){

        dots = new TextView[4];
        dotLayout.removeAllViews();
        for (int i=0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30);
            dots[i].setTextColor(getResources().getColor(R.color.colorGrey));

            dotLayout.addView(dots[i]);
        }

        if (dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }

    }

    public void goToLogin (View view){

        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(findViewById(R.id.started_btn),"transition_started");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else{
            startActivity(intent);
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);

            /*if(i==0){
                getStarted.setVisibility(View.INVISIBLE);
            }
            else if (i==1){
                getStarted.setVisibility(View.INVISIBLE);
            }
            else if (i==2){
                getStarted.setVisibility(View.INVISIBLE);
            }
            else  {
                animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.bottom_anim);
                getStarted.setAnimation(animation);
                getStarted.setVisibility(View.VISIBLE);
            }*/


        }

        @Override
        public void onPageScrollStateChanged(int state) {


        }
    };

}
