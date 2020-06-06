package com.example.mycoffeetry;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    Button signUp,login;
    TextInputEditText userName,password;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);

        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.signup_btn);
        login = findViewById(R.id.login_btn);

        /*if(FirebaseAuth.getInstance().getCurrentUser() == null){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        if (FirebaseAuth.getInstance().getCurrentUser() != null){

        }*/


    }

    private Boolean validateUserName(){

        String val = userName.getText().toString();

        if(val.isEmpty()){
            userName.setError("UserName cannot be empty");
            return false;
        }
        else {
            userName.setError(null);
            userName.setEnabled(false);
            return true;
        }

    }

    private Boolean validatePassword(){

        String val = password.getText().toString();

        if(val.isEmpty()){
            password.setError("Password cannot be empty");
            return false;
        }
        else {
            password.setError(null);
            password.setEnabled(false);
            return true;
        }

    }

    public void loginUser(View view){

        if(!validateUserName() | !validatePassword()){
            return;
        }
        else{
            isUser();
        }
    }

    private void isUser() {

        final String userNameEntered = userName.getText().toString().trim();
        final String passwordEntered = password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("userName").equalTo(userNameEntered);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    userName.setError(null);
                    userName.setEnabled(false);

                    String passwordFromDB = dataSnapshot.child(userNameEntered).child("password").getValue(String.class);

                    if(passwordFromDB.equals(passwordEntered)){

                        userName.setError(null);
                        userName.setEnabled(false);

                        String usernameFromDB = dataSnapshot.child(userNameEntered).child("userName").getValue(String.class);
                        String fullNameFromDB = dataSnapshot.child(userNameEntered).child("fullName").getValue(String.class);
                        String genderFromDB = dataSnapshot.child(userNameEntered).child("gender").getValue(String.class);
                        String phoneFromDB = dataSnapshot.child(userNameEntered).child("phoneNum").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userNameEntered).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),AcceuilActivity.class);

                        intent.putExtra("fullName",fullNameFromDB);
                        intent.putExtra("userName",usernameFromDB);
                        intent.putExtra("gender",genderFromDB);
                        intent.putExtra("phoneNum",phoneFromDB);
                        intent.putExtra("email",emailFromDB);

                        startActivity(intent);

                    }
                    else{
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }

                else{
                    userName.setError("Invalid UserName");
                    userName.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

  /*  public void goAcceuil(View view){

        Intent intent = new Intent(getApplicationContext(),AcceuilActivity.class);


        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(login,"transition_login");


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else{
            startActivity(intent);
        }

    }*/

    public void goSignUp(View view){

        Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);


        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(signUp,"transition_signUp");


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else{
            startActivity(intent);
        }

    }

}
