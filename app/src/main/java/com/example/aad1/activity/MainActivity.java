package com.example.aad1.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.aad1.R;
import com.example.aad1.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);

        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);
        if (navHostFragment == null)
            return;

        NavController navController = ((NavHostFragment) navHostFragment).getNavController();

        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setDrawerLayout(binding.drawerLayout)
                .build();

        setActionBar(navController, appBarConfiguration);

        setupNavigationMenu(navController);

        setupBottomNavMenu(navController);
    }

    private void setActionBar(NavController navController, AppBarConfiguration appBarConfiguration) {
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp();
    }

    private void setupNavigationMenu(NavController navController) {
        NavigationView sideNavView = binding.navView;
        NavigationUI.setupWithNavController(sideNavView, navController);
    }

    private void setupBottomNavMenu(NavController navController) {
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
    }

}
