package ntu.quanndm.lamthukiemtra_lan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Intent textReceive = getIntent();
        String ten_Nhan = textReceive.getStringExtra("ngon_ngu_lap_trinh");
        TextView tvNNLT = findViewById(R.id.tvNNLT);
        tvNNLT.setText(ten_Nhan);
    }
}