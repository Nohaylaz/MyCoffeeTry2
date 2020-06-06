package com.example.mycoffeetry;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SignUpActivity3 extends AppCompatActivity {


    ImageView backBtn;
    Button next, previous;
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup3);

        backBtn = findViewById(R.id.signback_btn);
        next = findViewById(R.id.signUp);
        previous = findViewById(R.id.previous_btn);
        title = findViewById(R.id.createAcc);

    }

    public void goNextSignUp2(View view){

        Intent intent = new Intent(getApplicationContext(),AcceuilActivity.class);

        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View,String>(backBtn,"transition_back");
        pairs[1] = new Pair<View,String>(title,"transition_create_account");
        pairs[2] = new Pair<View,String>(next,"transition_next");
        pairs[3] = new Pair<View,String>(next,"transition_previous");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpActivity3.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else{
            startActivity(intent);
        }

    }
}
