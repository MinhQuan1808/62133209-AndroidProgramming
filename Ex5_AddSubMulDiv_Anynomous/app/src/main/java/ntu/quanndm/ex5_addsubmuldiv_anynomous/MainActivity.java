package ntu.quanndm.ex5_addsubmuldiv_anynomous;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtA, edtB, edtKQ;
    Button btnCong, btnTru, btnNhan, btnChia;
    void TimDieuKhien(){
        edtA = (EditText)findViewById(R.id.edtA);
        edtB = (EditText)findViewById(R.id.edtB);
        edtKQ = (EditText)findViewById(R.id.edtKQ);
        btnCong = (Button)findViewById(R.id.btnCong);
        btnTru = (Button)findViewById(R.id.btnTru);
        btnNhan = (Button)findViewById(R.id.btnNhan);
        btnChia = (Button)findViewById(R.id.btnChia);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
        // Gan bo lang nghe su kien va xu ly cho tung nut

        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xu ly Cong
                XuLyCong();
            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xu ly Tru
                XuLyTru();
            }
        });
        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xu ly Nhan
                XuLyNhan();
            }
        });
        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xu ly chia
                XuLyChia();
            }
        });
    }
    void XuLyCong (){
        String so1 = edtA.getText().toString();
        String so2 = edtB.getText().toString();
        float soA = Float.parseFloat(so1);
        float soB = Float.parseFloat(so2);
        float tong = soA + soB;
        String chuoiKQ = String.valueOf(tong);
        edtKQ.setText(chuoiKQ);
    }
    void XuLyTru (){
        String so1 = edtA.getText().toString();
        String so2 = edtB.getText().toString();
        float soA = Float.parseFloat(so1);
        float soB = Float.parseFloat(so2);
        float hieu = soA - soB;
        String chuoiKQ = String.valueOf(hieu);
        edtKQ.setText(chuoiKQ);
    }
    void XuLyNhan (){
        String so1 = edtA.getText().toString();
        String so2 = edtB.getText().toString();
        float soA = Float.parseFloat(so1);
        float soB = Float.parseFloat(so2);
        float tich = soA * soB;
        String chuoiKQ = String.valueOf(tich);
        edtKQ.setText(chuoiKQ);
    }
    void XuLyChia (){
        String so1 = edtA.getText().toString();
        String so2 = edtB.getText().toString();
        float soA = Float.parseFloat(so1);
        float soB = Float.parseFloat(so2);
        float thuong = soA / soB;
        String chuoiKQ = String.valueOf(thuong);
        edtKQ.setText(chuoiKQ);
    }
}
