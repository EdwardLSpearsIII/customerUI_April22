package com.example.admin1.firstlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LearnMore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_more);
    }

    public void goBack (View view){
        startActivity (new Intent(LearnMore.this, Login.class));
    }
}
