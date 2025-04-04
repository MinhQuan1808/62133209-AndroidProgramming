package ntu.quanndm.lamthukiemtra_lan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnC1, btnC2, btnC3, btnC4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnC1 = findViewById(R.id.btnC1);
        btnC2 = findViewById(R.id.btnC2);
        btnC3 = findViewById(R.id.btnC3);
        btnC4 = findViewById(R.id.btnC4);

        btnC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, Cau1Activity.class);
                startActivity(intent1);
            }
        });
        btnC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, Cau2Activity.class);
                startActivity(intent2);
            }
        });
        btnC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, Cau3Activity.class);
                startActivity(intent3);
            }
        });
    }
}