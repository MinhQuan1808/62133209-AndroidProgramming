package ntu.quanndm.ex7_intentlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intentTuLogin = getIntent();
        String tenDN_Nhan = intentTuLogin.getStringExtra("ten_dang_nhap");
        TextView tvUserName = findViewById(R.id.tvUserName);
        tvUserName.setText(tenDN_Nhan);
    }
}