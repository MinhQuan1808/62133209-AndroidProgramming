package ntu.quanndm.ex5_addsubmuldiv_var;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimView();
        btnCong.setOnClickListener(boLangNghe_XuLyCong);
        btnTru.setOnClickListener(boLangNghe_XuLyTru);
        btnNhan.setOnClickListener(boLangNghe_XuLyNhan);
        //btnChia.setOnClickListener(boLangNghe_XuLyChia);
        //Vi du su dung bo lang nghe an danh

    }
    //Tao cac bo lang nghe va xu ly su kien
    View.OnClickListener boLangNghe_XuLyCong = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String so1 = edtA.getText().toString();
            String so2 = edtB.getText().toString();
            double soA = Double.parseDouble(so1);
            double soB = Double.parseDouble(so2);
            double tong = soA + soB;
            String chuoiKQ = String.valueOf(tong);
            tvKQ.setText(chuoiKQ);
        }
    };
    View.OnClickListener boLangNghe_XuLyTru = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String so1 = edtA.getText().toString();
            String so2 = edtB.getText().toString();
            double soA = Double.parseDouble(so1);
            double soB = Double.parseDouble(so2);
            double hieu = soA - soB;
            String chuoiKQ = String.valueOf(hieu);
            tvKQ.setText(chuoiKQ);
        }
    };
    View.OnClickListener boLangNghe_XuLyNhan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String so1 = edtA.getText().toString();
            String so2 = edtB.getText().toString();
            double soA = Double.parseDouble(so1);
            double soB = Double.parseDouble(so2);
            double tich = soA * soB;
            String chuoiKQ = String.valueOf(tich);
            tvKQ.setText(chuoiKQ);
        }
    };
    View.OnClickListener boLangNghe_XuLyChia = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String so1 = edtA.getText().toString();
            String so2 = edtB.getText().toString();
            double soA = Double.parseDouble(so1);
            double soB = Double.parseDouble(so2);
            double thuong = soA * soB;
            String chuoiKQ = String.valueOf(thuong);
            tvKQ.setText(chuoiKQ);
        }
    };
    //----------------------------------------------------------------------
    void TimView(){
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnNhan = findViewById(R.id.btnNhan);
        btnChia = findViewById(R.id.btnChia);
        tvKQ = findViewById(R.id.tvKQ);
    }
    EditText edtA, edtB;
    Button btnCong, btnTru, btnNhan, btnChia;
    TextView tvKQ;
}