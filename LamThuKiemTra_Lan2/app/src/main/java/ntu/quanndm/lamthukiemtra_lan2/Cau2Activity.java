package ntu.quanndm.lamthukiemtra_lan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cau2Activity extends AppCompatActivity {
    ListView lvNNLT;
    ArrayList<String> dsNNLT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau2);
        lvNNLT = findViewById(R.id.lvNNLT);
//B1.Chuan bi du lieu, hard-core
        dsNNLT = new ArrayList<String>();
        dsNNLT.add("Python");
        dsNNLT.add("Php");
        dsNNLT.add(("Java"));
        //B2
        ArrayAdapter<String> adapterNNLT;
        adapterNNLT = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dsNNLT);
        //B3. Gan adapter
        lvNNLT.setAdapter(adapterNNLT);
        //B4. Gan bo lang nghe va xu su kien
        lvNNLT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String giaTriDuocChon = dsNNLT.get(position);
                Toast.makeText(Cau2Activity.this, "Ban da chon " + giaTriDuocChon, Toast.LENGTH_SHORT).show();
                Intent text = new Intent(Cau2Activity.this, ItemActivity.class);
                text.putExtra("ngon_ngu_lap_trinh", giaTriDuocChon);
                startActivity(text);
            }
        });
    }
}