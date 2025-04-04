package ntu.quanndm.thigk2nguyendinhminhquan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnCN2, btnCN3, btnCN4, btnAbout, btnLT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCN2 = findViewById(R.id.btnCN2);
        btnCN3 = findViewById(R.id.btnCN3);
        btnCN4 = findViewById(R.id.btnCN4);
        btnAbout = findViewById(R.id.btnAbout);
        btnLT = findViewById(R.id.btnLT);
        btnCN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cn2 = new Intent(MainActivity.this, Chucnang2Activity.class);
                startActivity(cn2);
            }
        });
        btnCN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cn3 = new Intent(MainActivity.this, Chucnang3Activity.class);
                startActivity(cn3);
            }
        });
        btnCN4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cn4 = new Intent(MainActivity.this, Chucnang4Activity.class);
                startActivity(cn4);
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(MainActivity.this, AboutmeActivity.class);
                startActivity(about);
            }
        });
        btnLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lamthem = new Intent(MainActivity.this, LamthemActivity.class);
                startActivity(lamthem);
            }
        });
    }
}