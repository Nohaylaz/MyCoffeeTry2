package com.example.mycoffeetry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    ImageView backBtn;
    ProgressBar progressBar;
    Button signup_btn;
    TextView title;

    UserBDD userBdd;

    TextInputEditText fullName, userName, email, password, phoneNum;
    RadioButton female,male;


    int i = 0;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.sign_up);

        backBtn = findViewById(R.id.signback_btn);
        signup_btn = findViewById(R.id.signUp);
        title = findViewById(R.id.createAcc);

        userBdd = new UserBDD();

        fullName = findViewById(R.id.fullName);
        userName = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phoneNum = findViewById(R.id.phoneNum);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        progressBar = findViewById(R.id.progressBar);

        reference = database.getInstance().getReference().child("users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    i = (int)dataSnapshot.getChildrenCount();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get the values
                String fullname = fullName.getText().toString();
                String username = userName.getText().toString();
                String mail = email.getText().toString();
                String passcode = password.getText().toString();
                String phonenum = phoneNum.getText().toString();
                String ma = male.getText().toString();
                String fe = female.getText().toString();

                if(TextUtils.isEmpty(mail)){
                    email.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(passcode)){
                    password.setError("Password is required");
                    return;
                }

                if(passcode.length() < 6){
                    password.setError("Password must be more than 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                userBdd.setFullName(fullname);
                userBdd.setUserName(username);
                userBdd.setEmail(mail);
                userBdd.setPassword(passcode);
                userBdd.setPhoneNum(phonenum);

                reference.child(String.valueOf(i+1)).setValue(userBdd);

                if(male.isChecked()){
                    userBdd.setGender(ma);
                    reference.child(String.valueOf(i+1)).setValue(userBdd);
                }
                else{
                    userBdd.setGender(fe);
                    reference.child(String.valueOf(i+1)).setValue(userBdd);
                }


            }
        });

    }

    public void goNextSignUp(View view){

        Intent intent = new Intent(getApplicationContext(),AcceuilActivity.class);


        Pair[] pairs = new Pair[3];
        pairs[0] = new Pair<View,String>(backBtn,"transition_back");
        pairs[1] = new Pair<View,String>(title,"transition_create_account");
        pairs[2] = new Pair<View,String>(signup_btn,"transition_next");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpActivity.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else{
            startActivity(intent);
        }

    }

}
