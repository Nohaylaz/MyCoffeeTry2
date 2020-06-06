package com.example.mycoffeetry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    //hooks
    TextInputEditText fullName, email, phone, password;
    TextView gender, userName;

    String user_username, user_fullname, user_email, user_phone, user_password, user_gender;

    ImageView profilePic;

    int i;

    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");

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

        //Hooks
        fullName = findViewById(R.id.fullname_inp);
        email = findViewById(R.id.email_inp);
        phone = findViewById(R.id.phone_inp);
        password = findViewById(R.id.password_inp);
        gender = findViewById(R.id.gender_inp);
        userName = findViewById(R.id.username_inp);

        profilePic = findViewById(R.id.profile_image);

        //Show All data
        showAllUserData();

        if(gender.getText().equals("female")){
            profilePic.setImageResource(R.drawable.man);
        }
        else {
            profilePic.setImageResource(R.drawable.man);
        }


    }

    private void showAllUserData() {

        Intent intent = getIntent();
        user_username = intent.getStringExtra("userName");
        user_fullname = intent.getStringExtra("fullName");
        user_email = intent.getStringExtra("email");
        user_phone = intent.getStringExtra("phoneNum");
        user_password = intent.getStringExtra("password");
        user_gender = intent.getStringExtra("gender");

        userName.setText(user_username);
        gender.setText(user_gender);
        email.setText(user_email);
        phone.setText(user_phone);
        password.setText(user_password);
        fullName.setText(user_fullname);




    }

     public void update(View view){

        if (isNameChanged() || isPasswordChanged()){
            Toast.makeText(this,"Data has been updated",Toast.LENGTH_LONG).show();
        }


     }

    private boolean isNameChanged() {

        if(!user_fullname.equals(fullName.getText().toString())){
            user_fullname = fullName.getText().toString();
            reference.child("userName").child("fullName").setValue(fullName.getText().toString());
            return true;

        }else{
            return false;
        }

    }

    private boolean isPasswordChanged() {

        if(!user_password.equals(password.getText().toString())){
            user_password = password.getText().toString();
            reference.child("userName").child("password").setValue(password.getText().toString());
            return true;

        }else{
            return false;
        }

    }
}
