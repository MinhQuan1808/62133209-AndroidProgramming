package ntu.quanndm.duanthicuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KHActivity extends AppCompatActivity {
    EditText edtMKH, edtTKH, edtDC, edtSDT;
    Button btnTh;
    SQLiteDatabase khDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khactivity);
        edtMKH = findViewById(R.id.edtMKH);
        edtTKH = findViewById(R.id.edtTKH);
        edtDC = findViewById(R.id.edtDC);
        edtSDT = findViewById(R.id.edtSDT);
        btnTh = findViewById(R.id.btnTh);
        khDatabase = openOrCreateDatabase("kh.db", MODE_PRIVATE, null);
        
        autoGenerateMKH();
        btnTh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mskh = edtMKH.getText().toString();
                String htkh = edtTKH.getText().toString();
                String diachi = edtDC.getText().toString();
                String sdt = edtSDT.getText().toString();
                ContentValues myvalue = new ContentValues();
                myvalue.put("mskh",mskh);
                myvalue.put("htkh",htkh);
                myvalue.put("diachi",diachi);
                myvalue.put("sdt",sdt);
                String msg = "";
                if (khDatabase.insert("kh",null, myvalue) == -1){
                    msg = "Thêm mới thất bại!";
                }
                else {
                    msg = "Thêm mới thành công!";
                }
                Intent intent = new Intent(KHActivity.this, KHListActivity.class);
                startActivity(intent);
                Toast.makeText(KHActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Hiển thị mũi tên ngược
            getSupportActionBar().setTitle("Thêm Mới Khách Hàng");    // Tùy chỉnh tiêu đề
        }
    }
    // Xử lý sự kiện khi nhấn vào mũi tên ngược
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Xử lý sự kiện khi nhấn vào mũi tên ngược
            Intent intent = new Intent(KHActivity.this, KHListActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void autoGenerateMKH() {
        Cursor cursor = khDatabase.rawQuery("SELECT mskh FROM kh ORDER BY mskh DESC LIMIT 1", null);
        if (cursor.moveToFirst()) {
            String lastMHD = cursor.getString(0);
            int number = Integer.parseInt(lastMHD.replace("KH", "")) + 1;
            edtMKH.setText("KH" + number);
        } else {
            edtMKH.setText("KH1");
        }
        cursor.close();
    }
}

