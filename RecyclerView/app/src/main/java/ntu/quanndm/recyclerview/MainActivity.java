package ntu.quanndm.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LandScapeAdapter landScapeAdapter;
    ArrayList<LandScape> recyclerViewDatas;
    RecyclerView recyclerViewLandScape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewDatas = getDataForRecyclerView();
        recyclerViewLandScape = findViewById(R.id.RecyclerLand);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewLandScape.setLayoutManager(layoutManager);
        landScapeAdapter = new LandScapeAdapter(this, recyclerViewDatas);
        recyclerViewLandScape.setAdapter(landScapeAdapter);
    }

    ArrayList<LandScape> getDataForRecyclerView() {
        ArrayList<LandScape> dsDuLieu = new ArrayList<LandScape>();
        LandScape landScape1 = new LandScape("effiel", "Thap Effiel");
        dsDuLieu.add(landScape1);
        dsDuLieu.add(new LandScape("pisa", "Thap nghien Pisa"));
        dsDuLieu.add(new LandScape("tokyo", "Thap Tokyo"));
        dsDuLieu.add(new LandScape("tramhuong", "Thap Tram Huong"));
        return dsDuLieu;
    }
}