package ntu.quanndm.thigk2nguyendinhminhquan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LamthemActivity extends AppCompatActivity {
    EditText edtTien, edtKQ;
    Button btnUSD, btnEURO, btnWON, btnYEN, btnNDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamthem);
        TimDieuKhien();
    }
    void TimDieuKhien(){
        edtTien = findViewById(R.id.edtTien);
        edtKQ = findViewById(R.id.edtKQ);
        btnUSD = findViewById(R.id.btnUSD);
        btnEURO = findViewById(R.id.btnEURO);
        btnWON = findViewById(R.id.btnWON);
        btnYEN = findViewById(R.id.btnYEN);
        btnNDT = findViewById(R.id.btnNDT);
    }
    public void DoiUSD(View view){
        String soTien = edtTien.getText().toString();
        double tien = Double.parseDouble(soTien);
        double VND = tien * 25545;
        String chuoiKQ = String.valueOf(VND);
        edtKQ.setText(chuoiKQ+ " VND");
    }
    public void DoiEURO(View view){
        String soTien = edtTien.getText().toString();
        double tien = Double.parseDouble(soTien);
        double VND = tien * 26801;
        String chuoiKQ = String.valueOf(VND);
        edtKQ.setText(chuoiKQ+ " VND");
    }
    public void DoiWON(View view){
        String soTien = edtTien.getText().toString();
        double tien = Double.parseDouble(soTien);
        double VND = tien * 17.78;
        String chuoiKQ = String.valueOf(VND);
        edtKQ.setText(chuoiKQ+ " VND");
    }
    public void DoiYEN(View view){
        String soTien = edtTien.getText().toString();
        double tien = Double.parseDouble(soTien);
        double VND = tien * 171.3;
        String chuoiKQ = String.valueOf(VND);
        edtKQ.setText(chuoiKQ+ " VND");
    }
    public void DoiNDT(View view){
        String soTien = edtTien.getText().toString();
        double tien = Double.parseDouble(soTien);
        double VND = tien * 3510.79;
        String chuoiKQ = String.valueOf(VND);
        edtKQ.setText(chuoiKQ + " VND");
    }
}