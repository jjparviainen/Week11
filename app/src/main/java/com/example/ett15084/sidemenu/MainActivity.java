package com.example.ett15084.sidemenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    String selectedColor = "";
    String selectedFont = "";
    String selectedWidth = "";
    String selectedHeight = "";
    String textWritten = "";
    Boolean switchPosition = false;
    SettingsFragment settingsFragment = new SettingsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        Intent intent = getIntent();

        selectedColor = intent.getStringExtra("color");
        selectedFont = intent.getStringExtra("font");
        selectedHeight = intent.getStringExtra("height");
        selectedWidth = intent.getStringExtra("width");
        switchPosition = intent.getBooleanExtra("switch", switchPosition);
        textWritten = intent.getStringExtra("text");

        System.out.println("#######################################################################           Saatu väri asetuksista on: " + selectedColor );
        System.out.println("#######################################################################           Saatu fontti asetuksista on: " + selectedFont);
        System.out.println("#######################################################################           Saatu width asetuksista on: " + selectedWidth );
        System.out.println("#######################################################################           Saatu height asetuksista on: " + selectedHeight);
        System.out.println("#######################################################################           Saatu switch asetuksista on: " + switchPosition);
        System.out.println("#######################################################################           Saatu teksti asetuksista on: " + textWritten);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_settings:
                        System.out.println("MORO " + WriteFragment.getText());
                        textWritten = WriteFragment.getText();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, settingsFragment).commit();
                        Bundle bundleToSettings = new Bundle();
                        bundleToSettings.putBoolean("switch", switchPosition);
                        bundleToSettings.putString("text", textWritten);
                        settingsFragment.setArguments(bundleToSettings);
                        break;
                    case R.id.nav_write:
                        WriteFragment writeFragment = new WriteFragment();
                        Bundle bundle = new Bundle();
                        if(switchPosition == false){
                            bundle.putString("text", textWritten);
                            System.out.println("TEKSTIII" + textWritten);
                        }


                        // Tarkastetaan kaikki valintakentät ja jos jokin on tyhjä asetetaan oletusarvo
                        if(selectedWidth == null){
                            selectedWidth = "200";
                        }
                        if(selectedColor == null){
                            selectedColor = "BLACK";
                        }
                        if(selectedFont == null){
                            selectedFont = "14";
                        }
                        if(selectedHeight == null){
                            selectedHeight = "50";
                        }
                        else {
                            System.out.println("Mit vit tapahtuu");
                        }

                        bundle.putString("color", selectedColor);
                        bundle.putString("font", selectedFont);
                        bundle.putString("width", selectedWidth);
                        bundle.putString("height", selectedHeight);
                        bundle.putBoolean("switch", switchPosition);
                        writeFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, writeFragment).commit();
                        break;
                }

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /*if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WriteFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_write);
        }*/
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);

        }
        else {
            super.onBackPressed();
        }
    }
}
