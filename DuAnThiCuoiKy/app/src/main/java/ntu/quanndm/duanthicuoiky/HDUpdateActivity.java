package ntu.quanndm.duanthicuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class HDUpdateActivity extends AppCompatActivity {
    AutoCompleteTextView edtKHU;
    EditText edtMHDU, edtDATEU, edtDKU, edtCKU, edtTIENU;
    Button btnSUA, btnTINHU;
    SQLiteDatabase hdDatabase;

    SQLiteDatabase khDatabase;
    ArrayList<String> listMSKH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdupdate);
        edtKHU = findViewById(R.id.edtKHU);
        edtMHDU = findViewById(R.id.edtMHDU);
        edtDATEU = findViewById(R.id.edtDATEU);
        edtDKU = findViewById(R.id.edtDKU);
        edtCKU = findViewById(R.id.edtCKU);
        edtTIENU = findViewById(R.id.edtTIENU);
        btnTINHU = findViewById(R.id.btnTINHU);
        btnSUA = findViewById(R.id.btnSUA);


        hdDatabase = openOrCreateDatabase("hd.db",MODE_PRIVATE,null);
        khDatabase = openOrCreateDatabase("kh.db", MODE_PRIVATE, null);
        // Lấy danh sách MSKH từ bảng kh
        loadMSKH();

        btnTINHU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dk = Integer.parseInt(edtDKU.getText().toString());
                int ck = Integer.parseInt(edtCKU.getText().toString());
                int sodien = ck - dk;
                int tongtien;
                String msg = "";
                if (sodien < 0){
                    msg = "Chỉ số cuối kỳ phải lớn hơn chỉ số đầu kỳ!";
                }
                else {
                    if (sodien < 51){
                        tongtien = sodien*1893;
                    }
                    else {
                        if (sodien < 101){
                            tongtien = 50*1893 + (sodien-50)*1956;
                        }
                        else {
                            if (sodien < 201){
                                tongtien = 50*1893 + 50*1956 + (sodien -100)*2271;
                            }
                            else {
                                if (sodien < 301){
                                    tongtien = 50*1893 + 50*1956 + 100*2271 + (sodien - 200)*2860;
                                }
                                else {
                                    if (sodien < 401){
                                        tongtien = 50*1893 + 50*1956 + 100*2271 + 100*2860 + (sodien - 300)*3197;
                                    }
                                    else {
                                        tongtien = 50*1893 + 50*1956 + 100*2271 + 100*2860 + 100*3197 + (sodien - 400)*3302;
                                    }
                                }
                            }
                        }
                    }
                    edtTIENU.setText(tongtien+"");
                    msg = "Success";
                }
                Toast.makeText(HDUpdateActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        btnSUA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mskh = edtKHU.getText().toString();
                String mhd = edtMHDU.getText().toString();
                String date = edtDATEU.getText().toString();
                int dk = Integer.parseInt(edtDKU.getText().toString());
                int ck = Integer.parseInt(edtCKU.getText().toString());
                int tien = Integer.parseInt(edtTIENU.getText().toString());
                ContentValues myvalue = new ContentValues();
                myvalue.put("mskh",mskh);
                myvalue.put("date",date);
                myvalue.put("dk",dk);
                myvalue.put("ck",ck);
                myvalue.put("tien",tien);
                int n = hdDatabase.update("hd",myvalue,"mhd = ?",new String[]{mhd});
                String msg = "";
                if (n==0){
                    msg = "Sửa thất bại!";
                }
                else {
                    msg = "Sửa thành công!";
                    edtKHU.setText("");
                    edtDATEU.setText("");
                    edtDKU.setText("");
                    edtCKU.setText("");
                    edtTIENU.setText("");
                }
                Intent intent = new Intent(HDUpdateActivity.this, HDListActivity.class);
                startActivity(intent);
                Toast.makeText(HDUpdateActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Hiển thị mũi tên ngược
            getSupportActionBar().setTitle("Cập Nhật Hóa Đơn");    // Tùy chỉnh tiêu đề
        }
    }
    // Xử lý sự kiện khi nhấn vào mũi tên ngược
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Xử lý sự kiện khi nhấn vào mũi tên ngược
            Intent intent = new Intent(HDUpdateActivity.this, HDListActivity.class);
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
        if (intent.hasExtra("mhd")) {
            edtKHU.setText(intent.getStringExtra("mskh"));
            edtMHDU.setText(intent.getStringExtra("mhd"));
            edtDATEU.setText(intent.getStringExtra("date"));
            int dk = intent.getIntExtra("dk", 0);
            edtDKU.setText(String.valueOf(dk));
            int ck = intent.getIntExtra("ck", 0);
            edtCKU.setText(String.valueOf(ck));
            int tien = intent.getIntExtra("tien", 0);
            edtTIENU.setText(String.valueOf(tien));
        }
    }
    private void loadMSKH() {
        listMSKH = new ArrayList<>();
        Cursor cursor = khDatabase.rawQuery("SELECT mskh FROM kh", null);
        while (cursor.moveToNext()) {
            listMSKH.add(cursor.getString(0));
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, listMSKH);
        edtKHU.setAdapter(adapter);
    }
}