package com.kamilkirstein.indexcards.ui;

import android.app.Application;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.kamilkirstein.indexcards.R;
import com.kamilkirstein.indexcards.Repository;
import com.kamilkirstein.indexcards.dto.IndexCard;
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

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavController.OnDestinationChangedListener {

    private FloatingActionButton fab;
    private Fragment hostFragment;
    private NavController navController;
    TextView tvAnswerQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the host fragment to later get the nav controller
        hostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        View viewFromHostFragment = hostFragment.getView();
        navController = Navigation.findNavController(viewFromHostFragment);
        navController.addOnDestinationChangedListener(this::onDestinationChanged);

        tvAnswerQuestion = findViewById(R.id.bottom_app_bar_aq);
        tvAnswerQuestion.setVisibility(View.VISIBLE);

    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller,
                                     @NonNull NavDestination destination,
                                     @Nullable Bundle arguments) {
        switch (destination.getId()) {
            case R.id.showIndexCardCategoriesFrg:
                setUpBottomAppBarMainAct();
                break;

            case R.id.editIndexCard:
                setUpBottomAppBarEditIndexCard();
                break;
        }
    }

    private void setUpBottomAppBarMainAct() {
        fab = findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_baseline_edit_24);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_global_editIndexCard);
            }
        });
    }

    private void setUpBottomAppBarEditIndexCard() {

        fab = findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_baseline_save_24);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get with the getPrimaryNavigationFragment the latest Fragment from
                EditIndexCardFrg frg = (EditIndexCardFrg) hostFragment.getChildFragmentManager().getPrimaryNavigationFragment();

                if (frg != null) {
                    Toast.makeText(getApplicationContext(), "Safe the Fragment", Toast.LENGTH_LONG).show();
                    frg.safeIndexCard(findViewById(R.id.mainActCoordinatorLayout));
                } else {
                    Toast.makeText(getApplicationContext(), "EditIndexCard Fragment konnte nicht gefunden werden!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void setUpTextViewBottomAppbar(EditIndexCardFrg fragment) {
        tvAnswerQuestion.setText(fragment.getLabelBasedOnStateOfCard());
        // set the text and the onClick listener for the bottom app bar
        tvAnswerQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.changeQA();
                tvAnswerQuestion.setText(fragment.getLabelBasedOnStateOfCard());
            }
        });

    }
}