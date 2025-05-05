package ntu.quanndm.duanthicuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KHUpdateActivity extends AppCompatActivity {
    EditText edtMKHU, edtTKHU, edtDCU, edtSDTU;
    Button btnSu;
    SQLiteDatabase khDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khupdate);
        edtMKHU = findViewById(R.id.edtMKHU);
        edtTKHU = findViewById(R.id.edtTKHU);
        edtDCU = findViewById(R.id.edtDCU);
        edtSDTU = findViewById(R.id.edtSDTU);
        btnSu = findViewById(R.id.btnSu);

        khDatabase = openOrCreateDatabase("kh.db", MODE_PRIVATE, null);
        btnSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mskh = edtMKHU.getText().toString();
                String htkh = edtTKHU.getText().toString();
                String diachi = edtDCU.getText().toString();
                String sdt = edtSDTU.getText().toString();
                ContentValues myvalue = new ContentValues();
                myvalue.put("htkh", htkh);
                myvalue.put("diachi", diachi);
                myvalue.put("sdt", sdt);
                int n = khDatabase.update("kh", myvalue, "mskh = ?", new String[]{mskh});
                String msg = "";
                if (n == 0) {
                    msg = "Sửa thất bại!";
                } else {
                    msg = "Sửa thành công!";
                }
                Intent intent = new Intent(KHUpdateActivity.this, KHListActivity.class);
                startActivity(intent);
                Toast.makeText(KHUpdateActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Hiển thị mũi tên ngược
            getSupportActionBar().setTitle("Cập Nhật Khách Hàng");
        }
    }
    // Xử lý sự kiện khi nhấn vào mũi tên ngược
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Xử lý sự kiện khi nhấn vào mũi tên ngược
            Intent intent = new Intent(KHUpdateActivity.this, KHListActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent.hasExtra("mskh")) {
            edtMKHU.setText(intent.getStringExtra("mskh"));
            edtTKHU.setText(intent.getStringExtra("htkh"));
            edtDCU.setText(intent.getStringExtra("diachi"));
            edtSDTU.setText(intent.getStringExtra("sdt"));
        }
    }
}

