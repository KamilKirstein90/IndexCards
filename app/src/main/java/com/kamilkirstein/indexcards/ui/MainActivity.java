package com.kamilkirstein.indexcards.ui;

import android.app.Application;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.kamilkirstein.indexcards.R;
import com.kamilkirstein.indexcards.Repository;
import com.kamilkirstein.indexcards.dto.IndexCardCategory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavController.OnDestinationChangedListener{

    private FloatingActionButton fab;
    private Fragment hostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the host fragment to later get the nav controler
        hostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        View viewFromHostFragment = hostFragment.getView();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(viewFromHostFragment).navigate(R.id.action_global_editIndexCard);
            }
        });

    }



    @Override
    public void onDestinationChanged(@NonNull NavController controller,
                                     @NonNull NavDestination destination,
                                     @Nullable Bundle arguments) {
        //TODO herer set a new menue and fab icon when the destination is changed
    }
}