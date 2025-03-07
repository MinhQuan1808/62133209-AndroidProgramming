package ntu.quanndm.ex6_intentdongian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnMH2, btnMH3;
    void TimDieuKhien(){
        btnMH2 = findViewById(R.id.btnMH2);
        btnMH3 = findViewById(R.id.btnMH3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
    }
}