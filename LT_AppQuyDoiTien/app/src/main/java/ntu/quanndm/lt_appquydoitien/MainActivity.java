package ntu.quanndm.lt_appquydoitien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtTien, edtKQ;
    Button btnUSD, btnEURO, btnWON, btnYEN, btnNDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
    }
    void TimDieuKhien(){
        edtTien = (EditText) findViewById(R.id.edtTien);
        edtKQ = (EditText) findViewById(R.id.edtKQ);
        btnUSD = (Button) findViewById(R.id.btnUSD);
        btnEURO = (Button) findViewById(R.id.btnEURO);
        btnWON = (Button) findViewById(R.id.btnWON);
        btnYEN = (Button) findViewById(R.id.btnYEN);
        btnNDT = (Button) findViewById(R.id.btnNDT);
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