package com.example.admin1.firstlogin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class navigationbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigationbar);

        BottomNavigationView bottomNav = findViewById(R.id.navigation_bar);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new activity_customer_home2())
                    .commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedPage = null;

            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedPage = new activity_customer_home2();
                    break;
                case R.id.nav_search:
                    selectedPage = new activity_customer_search();
                    break;
                case R.id.nav_messages:
                    selectedPage = new activity_customer_home2();
                    break;
                case R.id.nav_settings:
                    selectedPage = new activity_customer_search();
                    break;
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, selectedPage)
                    .commit();


            return true;
        }
    };
}
