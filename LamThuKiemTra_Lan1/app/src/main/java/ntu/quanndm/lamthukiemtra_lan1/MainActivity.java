package ntu.quanndm.lamthukiemtra_lan1;

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

        btnC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentC1 = new Intent(MainActivity.this, Cau1Activity.class);
                startActivity(intentC1);
            }
        });
    }
}