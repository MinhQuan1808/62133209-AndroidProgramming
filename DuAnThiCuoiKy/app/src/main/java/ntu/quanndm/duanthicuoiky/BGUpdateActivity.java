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

public class BGUpdateActivity extends AppCompatActivity {
    EditText edtMU, edtBU, edtKU, edtGU;
    Button btnUU;
    SQLiteDatabase bgDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bgupdate);
        edtMU = findViewById(R.id.edtMU);
        edtBU = findViewById(R.id.edtBU);
        edtKU = findViewById(R.id.edtKU);
        edtGU = findViewById(R.id.edtGU);
        btnUU = findViewById(R.id.btnUU);

        bgDatabase = openOrCreateDatabase("bg.db", MODE_PRIVATE, null);
        btnUU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String muc = edtMU.getText().toString();
                String chisobatdau = edtBU.getText().toString();
                String chisoketthuc = edtKU.getText().toString();
                String gia = edtGU.getText().toString();
                ContentValues myvalue = new ContentValues();
                myvalue.put("chisobatdau",chisobatdau);
                myvalue.put("chisoketthuc",chisoketthuc);
                myvalue.put("gia",gia);
                int n = bgDatabase.update("bg",myvalue,"muc = ?",new String[]{muc});
                String msg = "";
                if (n==0){
                    msg = "Sửa thất bại!";
                }
                else {
                    msg = "Sửa thành công!";
                }
                Intent intent = new Intent(BGUpdateActivity.this, BGLIstActivity.class);
                startActivity(intent);
                Toast.makeText(BGUpdateActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Hiển thị mũi tên ngược
            getSupportActionBar().setTitle("Cập Nhật Biểu Giá");
        }
    }
    // Xử lý sự kiện khi nhấn vào mũi tên ngược
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Xử lý sự kiện khi nhấn vào mũi tên ngược
            Intent intent = new Intent(BGUpdateActivity.this, BGLIstActivity.class);
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
        if (intent.hasExtra("muc")) {
            edtMU.setText(intent.getStringExtra("muc"));
            edtBU.setText(intent.getStringExtra("chisobatdau"));
            edtKU.setText(intent.getStringExtra("chisoketthuc"));
            edtGU.setText(intent.getStringExtra("gia"));
        }
    }
}

