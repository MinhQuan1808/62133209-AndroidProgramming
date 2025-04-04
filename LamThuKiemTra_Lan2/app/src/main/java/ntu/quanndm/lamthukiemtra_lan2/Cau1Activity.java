package ntu.quanndm.lamthukiemtra_lan2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Cau1Activity extends AppCompatActivity {
    EditText edtA, edtB, edtKQ;
    Button btnTong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau1);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtKQ = findViewById(R.id.edtKQ);
        btnTong = findViewById(R.id.btnTong);

        btnTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String so1 = edtA.getText().toString();
                String so2 = edtB.getText().toString();
                float soA = Float.parseFloat(so1);
                float soB = Float.parseFloat(so2);
                float tong = soA + soB;
                String chuoiKQ = String.valueOf(tong);
                edtKQ.setText(chuoiKQ);
            }
        });
    }
}