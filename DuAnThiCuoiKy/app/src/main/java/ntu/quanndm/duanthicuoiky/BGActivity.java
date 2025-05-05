package ntu.quanndm.duanthicuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BGActivity extends AppCompatActivity {
    EditText edtM, edtB, edtK, edtG;
    Button btnT;
    SQLiteDatabase bgDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bgactivity);
        edtM = findViewById(R.id.edtM);
        edtB = findViewById(R.id.edtB);
        edtK = findViewById(R.id.edtK);
        edtG = findViewById(R.id.edtG);
        btnT = findViewById(R.id.btnT);

        bgDatabase = openOrCreateDatabase("bg.db",MODE_PRIVATE,null);
        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String muc = edtM.getText().toString();
                String chisobatdau = edtB.getText().toString();
                String chisoketthuc = edtK.getText().toString();
                String gia = edtG.getText().toString();
                ContentValues myvalue = new ContentValues();
                myvalue.put("muc",muc);
                myvalue.put("chisobatdau",chisobatdau);
                myvalue.put("chisoketthuc",chisoketthuc);
                myvalue.put("gia",gia);
                String msg = "";
                if (bgDatabase.insert("bg",null, myvalue) == -1){
                    msg = "Thêm mới thất bại!";
                }
                else {
                    msg = "Thêm mới thành công!";
                }
                Intent intent = new Intent(BGActivity.this, BGLIstActivity.class);
                startActivity(intent);
                Toast.makeText(BGActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Hiển thị mũi tên ngược
            getSupportActionBar().setTitle("Thêm Mới Biểu Giá");    // Tùy chỉnh tiêu đề
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Xử lý sự kiện khi nhấn vào mũi tên ngược
            Intent intent = new Intent(BGActivity.this, BGLIstActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}