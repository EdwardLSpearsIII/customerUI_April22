package com.example.admin1.firstlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText userMail, userPassword;
    private Button btnLogin;
    private FirebaseAuth mAuth;
    private Intent activity_customer_home2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_login);


        userMail = findViewById(R.id.Lmail);
        userPassword = findViewById(R.id.Lpass);
        btnLogin = findViewById(R.id.loginbtn);
        mAuth = FirebaseAuth.getInstance();

        activity_customer_home2 = new Intent(this, com.example.admin1.firstlogin.activity_customer_home2.class);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mail = userMail.getText().toString();
                final String password = userPassword.getText().toString();

                if (mail.isEmpty() || password.isEmpty()) {
                    showMessage("Please Verify All Fields");
                }
                else {
                    signIn(mail, password);
                }
            }
        });


    }

    private void signIn(String mail, String password) {

      mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {

              if (task.isSuccessful()) {

                  updateUI();

              }
              else
                  showMessage(task.getException().getMessage());

          }
      });
    }

    private void updateUI() {

        startActivity(activity_customer_home2);
        finish();
    }


    private void showMessage(String text) {

        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }

    @Override
    protected  void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            updateUI();
        }
    }

    public void signUp(View view){
        Intent startNewActivity = new Intent (this, signUpActivity.class);
        startActivity(startNewActivity);
    }

    public void learnMore(View view){
        Intent startNewActivity = new Intent (this, LearnMore.class);
        startActivity(startNewActivity);
    }

}
