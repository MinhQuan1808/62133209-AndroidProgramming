package ntu.quanndm.duanthicuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnKH, btnHD, btnBG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnKH = findViewById(R.id.btnKH);
        btnHD = findViewById(R.id.btnHD);
        btnBG = findViewById(R.id.btnBG);
        btnKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent KHintent = new Intent(MainActivity.this, KHListActivity.class);
                startActivity(KHintent);
            }
        });
        btnHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HDintent = new Intent(MainActivity.this, HDListActivity.class);
                startActivity(HDintent);
            }
        });
        btnBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BGintent = new Intent(MainActivity.this,BGLIstActivity.class);
                startActivity(BGintent);
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Quản Lý Sử Dụng Điện Của Khách Hàng");
        }
    }
}