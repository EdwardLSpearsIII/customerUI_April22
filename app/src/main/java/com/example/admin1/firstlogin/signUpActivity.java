package com.example.admin1.firstlogin;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

public class signUpActivity extends AppCompatActivity {

    private EditText userfName,userlName,userEmail,useruName,userPassword,userNoCollege;
    private Button Signup;

    private FirebaseAuth mAuth;

    DatabaseReference databaseUsers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userfName = findViewById(R.id.f_name);
        userlName = findViewById(R.id.l_name);
        userEmail = findViewById(R.id.email);
        useruName = findViewById(R.id.u_name);
        userPassword = findViewById(R.id.password);
        userNoCollege = findViewById(R.id.school);
        Signup = findViewById(R.id.Signup);

        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");


        mAuth = FirebaseAuth.getInstance();


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String fName = userfName.getText().toString();
                final String lName = userlName.getText().toString();
                final String email = userEmail.getText().toString();
                final String uName = useruName.getText().toString();
                final String password = userPassword.getText().toString();
                final String school = userNoCollege.getText().toString();

                if( email.isEmpty() || fName.isEmpty() || lName.isEmpty() || uName.isEmpty() || password.isEmpty() || school.isEmpty()){

                    //something goes wrong: all fields must be filled
                    // we need to display error message
                    showMessage("Please Verify all fields");
                    Signup.setVisibility(View.VISIBLE);

                }
                else {
                    // everything is ok and all fields are filled now we can start creating user account
                    //CreateUserAccount mehtod will try to create the user if the email is valid

                    CreateUserAccount(email,fName,password);



                    //showMessage("all fields correct");

                }


            }

        });

    }

    private void addUser() {
        String fname = userfName.getText().toString().trim();
        String lName = userlName.getText().toString().trim();
        String email = userEmail.getText().toString().trim();
        String uName = useruName.getText().toString().trim();
        String password = userPassword.getText().toString().trim();
        String school = userNoCollege.getText().toString().trim();

        if(!TextUtils.isEmpty(fname) || !TextUtils.isEmpty(lName) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(uName) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(school)){

            String id = databaseUsers.push().getKey();

            User user = new User(id, fname, lName, email, uName, password, school);

            databaseUsers.child(id).setValue(user);


        }
        else {
            Toast.makeText(this, "Verify all Fields", Toast.LENGTH_LONG).show();
        }

    }

    private void CreateUserAccount(String email, String fName, String password) {

        //this mehtod create user account with specific email and password

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //user account created successfully
                            addUser();
                            updateUI();
                            showMessage("Account Created");
                        }
                        else
                        {
                            showMessage("Account Creation Failed");
                        }
                    }
                });





    }


    private void updateUI() {

        Intent startNewActivity = new Intent (this, BusinessUser.class);
        startActivity(startNewActivity);

    }


    // simple method to show toast message
    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }
}
