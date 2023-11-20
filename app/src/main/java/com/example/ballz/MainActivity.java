package com.example.ballz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    FrameLayout fragMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        events();
        loadFragment(new MainFrag());
    }

    private void events() {
        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.page_1:

                        loadFragment(new MainFrag());
                        return true;

                    case R.id.page_2:

                        return true;
                    case R.id.page_3:

                        loadFragment(new StandingsFragment());
                        return true;
                    case R.id.page_4:

                        return true;
                }
                return true;
            }
        });
    }

    private void addControls() {
        fragMain = (FrameLayout) findViewById(R.id.fragMain);
        bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
    }
    public void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragMain, fragment);
        fragmentTransaction.commit();
    }
}