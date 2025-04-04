package ntu.quanndm.thigk2nguyendinhminhquan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Chucnang3Activity extends AppCompatActivity {
    ListView lvMH;
    ArrayList<String> dsMH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chucnang3);
        lvMH = findViewById(R.id.lvMH);

        dsMH = new ArrayList<String>();
        dsMH.add("Tin học đại cương");
        dsMH.add("Lập trình Java");
        dsMH.add(("Phát triển ứng dụng web"));
        dsMH.add(("Khai phá dữ liệu lớn"));
        dsMH.add(("Kinh tế chính trị Mác - Lê nin"));
        dsMH.add(("..."));

        ArrayAdapter<String> adapterNNLT;
        adapterNNLT = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dsMH);

        lvMH.setAdapter(adapterNNLT);

        lvMH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String giaTriDuocChon = dsMH.get(position);
                Toast.makeText(Chucnang3Activity.this, giaTriDuocChon, Toast.LENGTH_SHORT).show();
            }
        });
    }
}