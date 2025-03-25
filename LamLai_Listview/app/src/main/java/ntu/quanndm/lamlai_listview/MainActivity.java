package ntu.quanndm.lamlai_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvHoa;
    ArrayList<String> dsHoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvHoa = findViewById(R.id.lvHoa);
        dsHoa = new ArrayList<String>();
        dsHoa.add("Hồng");
        dsHoa.add("Đào");
        dsHoa.add("Lan");
        dsHoa.add("Cúc");
        ArrayAdapter adapterHoa;
        adapterHoa = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dsHoa);
        lvHoa.setAdapter(adapterHoa);
    }
}