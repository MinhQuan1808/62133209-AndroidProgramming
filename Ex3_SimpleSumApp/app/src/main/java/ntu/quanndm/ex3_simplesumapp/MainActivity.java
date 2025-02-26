package ntu.quanndm.ex3_simplesumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtA, edtB, edtKQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //Lang nghe va xu ly su kien click
    public void XuLyCong(View view){
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtKQ = findViewById(R.id.edtKQ);
        //Lay du lieu
        String strA = edtA.getText().toString();
        String strB = edtB.getText().toString();
        //Chuyen du lieu sang dang so
        int soA = Integer.parseInt(strA);
        int soB = Integer.parseInt(strB);
        //Tinh tong
        int tong = soA + soB;
        String strTong = String.valueOf(tong);
        //Hien ra man hinh
        edtKQ.setText(strTong);
    }
}