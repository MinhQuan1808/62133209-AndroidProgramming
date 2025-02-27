package ntu.quanndm.ex4_addsubmuldiv_onclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtA, edtB, edtKQ;
    Button btnCong, btnTru, btnNhan, btnChia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
    }
    void TimDieuKhien(){
        edtA = (EditText)findViewById(R.id.edtA);
        edtB = (EditText)findViewById(R.id.edtB);
        edtKQ = (EditText)findViewById(R.id.edtKQ);
        btnCong = (Button)findViewById(R.id.btnCong);
        btnTru = (Button)findViewById(R.id.btnTru);
        btnNhan = (Button)findViewById(R.id.btnNhan);
        btnChia = (Button)findViewById(R.id.btnChia);
    }
    //Xu ly Cong
    public void XuLyCong (View view){
        String so1 = edtA.getText().toString();
        String so2 = edtB.getText().toString();
        float soA = Float.parseFloat(so1);
        float soB = Float.parseFloat(so2);
        float tong = soA + soB;
        String chuoiKQ = String.valueOf(tong);
        edtKQ.setText(chuoiKQ);
    }
    //Xu ly Tru
    public void XuLyTru (View view){
        String so1 = edtA.getText().toString();
        String so2 = edtB.getText().toString();
        float soA = Float.parseFloat(so1);
        float soB = Float.parseFloat(so2);
        float hieu = soA - soB;
        String chuoiKQ = String.valueOf(hieu);
        edtKQ.setText(chuoiKQ);
    }
    //Xu ly Nhan
    public void XuLyNhan (View view){
        String so1 = edtA.getText().toString();
        String so2 = edtB.getText().toString();
        float soA = Float.parseFloat(so1);
        float soB = Float.parseFloat(so2);
        float tich = soA * soB;
        String chuoiKQ = String.valueOf(tich);
        edtKQ.setText(chuoiKQ);
    }
    //Xu ly Chia
    public void XuLyChia (View view){
        String so1 = edtA.getText().toString();
        String so2 = edtB.getText().toString();
        float soA = Float.parseFloat(so1);
        float soB = Float.parseFloat(so2);
        float thuong = soA / soB;
        String chuoiKQ = String.valueOf(thuong);
        edtKQ.setText(chuoiKQ);
    }
}