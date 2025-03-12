package ntu.quanndm.vd_lvngonngulaptrinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView LVNNLT;
    ArrayList<String> dsNgonNguLT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LVNNLT = findViewById(R.id.LVNgonNguLapTrinh);

        //B1.Chuan bi du lieu, hard-core
        dsNgonNguLT = new ArrayList<String>();
        dsNgonNguLT.add("Python");
        dsNgonNguLT.add("Php");
        dsNgonNguLT.add(("Java"));
        //B2
        ArrayAdapter<String> adapterNNLT;
        adapterNNLT = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dsNgonNguLT);
        //B3. Gan adapter
        LVNNLT.setAdapter(adapterNNLT);
        //B4. Gan bo lang nghe va xu su kien
        LVNNLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}