package ntu.quanndm.lamlai_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvGV;
    ArrayList<String> dsGV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvGV = findViewById(R.id.lvGV);
        dsGV = new ArrayList<String>();
        dsGV.add("Mai Cường Thọ");
        dsGV.add("Phạm Văn Nam");
        dsGV.add("Nguyễn Huỳnh Huy");

        ArrayAdapter adapterGV;
        adapterGV = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dsGV);
        lvGV.setAdapter(adapterGV);

        lvGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });
    }
}