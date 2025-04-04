package ntu.quanndm.thigk2nguyendinhminhquan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Chucnang2Activity extends AppCompatActivity {
    EditText edtGK, edtCK, edtKQTB;
    Button btnTB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chucnang2);
        edtGK = findViewById(R.id.edtGK);
        edtCK = findViewById(R.id.edtCK);
        edtKQTB = findViewById(R.id.edtKQTB);
        btnTB = findViewById(R.id.btnTB);

        btnTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String so1 = edtGK.getText().toString();
                String so2 = edtCK.getText().toString();
                float soA = Float.parseFloat(so1);
                float soB = Float.parseFloat(so2);
                float trungbinh = (soA + soB)/2;
                String chuoiKQ = String.valueOf(trungbinh);
                edtKQTB.setText(chuoiKQ);
            }
        });
    }
}