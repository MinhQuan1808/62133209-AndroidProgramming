package ntu.quanndm.bottomnavigationview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bot_nav);
        loadFragment(new HomeFragment());
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment  fragmentduocchon = null;
                int mnuItemDuocChon = item.getItemId();
                if(mnuItemDuocChon == R.id.mnu_home){
                    fragmentduocchon = new HomeFragment();
                    Toast.makeText(MainActivity.this, "Thay HOME", Toast.LENGTH_SHORT).show();
                } else if(mnuItemDuocChon == R.id.mnu_search) {
                    fragmentduocchon = new SearchFragment();
                    Toast.makeText(MainActivity.this, "Thay SEARCH", Toast.LENGTH_SHORT).show();
                } else if (mnuItemDuocChon == R.id.mnu_profile) {
                    fragmentduocchon = new ProfileFragment();
                    Toast.makeText(MainActivity.this, "Thay PROFILE", Toast.LENGTH_SHORT).show();
                }
                if (fragmentduocchon != null) {
                    loadFragment(fragmentduocchon);
                    return true;
                }
                return false;

            }
        });
    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}

