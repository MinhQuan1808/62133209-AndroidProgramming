package ntu.quanndm.lamlai_onclick2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edtTen = findViewById(R.id.edtTen);
        Button btnChao = findViewById(R.id.btnChao);
        btnChao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Xin chao" + edtTen.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}