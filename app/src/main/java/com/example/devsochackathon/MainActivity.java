package com.example.devsochackathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =MainActivity.class.getSimpleName();
    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_nav);

        if(savedInstanceState==null){
            bottomNav.setItemSelected(R.id.about, true);
            fragmentManager=getSupportFragmentManager();
            AboutFragment aboutFragment= new AboutFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, aboutFragment)
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;

                switch (id){
                    case R.id.about:
                        fragment = new AboutFragment();
                        break;

                    case R.id.maze:
                        fragment = new MazeFragment();
                        break;
                    case R.id.terminal:
                        fragment = new TerminalFragment();
                        break;
                }


                if(fragment != null)
                {
                    fragmentManager =getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container,fragment)
                            .commit();
                }
                else{
                    Log.e(TAG, "Error in creating fragment");
                }
            }
        });

//        Button but1 = findViewById(R.id.button1);
//        but1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, MazeActivity.class);
//                view.getContext().startActivity(intent);
//            }
//        });
    }

    public void setMaze(View view) {
        Intent intent = new Intent(MainActivity.this, MazeActivity.class);
        startActivity(intent);
    }

    public void setHack(View view) {
        Intent intent = new Intent(MainActivity.this, MainMenu.class);
        startActivity(intent);
    }
}