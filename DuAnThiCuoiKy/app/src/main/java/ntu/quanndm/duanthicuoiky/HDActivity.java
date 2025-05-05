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

public class HDActivity extends AppCompatActivity {
    AutoCompleteTextView edtMSKH;
    EditText edtMHD, edtDATE, edtDK, edtCK, edtTIEN;
    Button btnTHEM, btnTINH;
    SQLiteDatabase hdDatabase;
    SQLiteDatabase khDatabase;
    ArrayList<String> listMSKH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdactivity);
        edtMSKH = findViewById(R.id.edtMSKH);
        edtMHD = findViewById(R.id.edtMHD);
        edtDATE = findViewById(R.id.edtDATE);
        edtDK = findViewById(R.id.edtDK);
        edtCK = findViewById(R.id.edtCK);
        edtTIEN = findViewById(R.id.edtTIEN);
        btnTHEM = findViewById(R.id.btnTHEM);
        btnTINH = findViewById(R.id.btnTINH);

        hdDatabase = openOrCreateDatabase("hd.db",MODE_PRIVATE,null);
        khDatabase = openOrCreateDatabase("kh.db", MODE_PRIVATE, null);
        // Lấy danh sách MSKH từ bảng kh
        loadMSKH();

        btnTINH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dk = Integer.parseInt(edtDK.getText().toString());
                int ck = Integer.parseInt(edtCK.getText().toString());
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
                    edtTIEN.setText(tongtien+"");
                    msg = "Success";
                }
                Toast.makeText(HDActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        edtMSKH.setOnItemClickListener((parent, view, position, id) -> autoGenerateMHD());
        btnTHEM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mskh = edtMSKH.getText().toString();
                String mhd = edtMHD.getText().toString();
                String date = edtDATE.getText().toString();
                int dk = Integer.parseInt(edtDK.getText().toString());
                int ck = Integer.parseInt(edtCK.getText().toString());
                int tien = Integer.parseInt(edtTIEN.getText().toString());
                ContentValues myvalue = new ContentValues();
                myvalue.put("mskh",mskh);
                myvalue.put("mhd",mhd);
                myvalue.put("date",date);
                myvalue.put("dk",dk);
                myvalue.put("ck",ck);
                myvalue.put("tien",tien);
                String msg = "";
                if (hdDatabase.insert("hd",null, myvalue) == -1){
                    msg = "Thêm mới thất bại!";
                }
                else {
                    msg = "Thêm mới thành công!";
                    edtMHD.setText("");
                    edtDATE.setText("");
                    edtDK.setText("");
                    edtCK.setText("");
                    edtTIEN.setText("");
                }
                Intent intent = new Intent(HDActivity.this, HDListActivity.class);
                startActivity(intent);
                Toast.makeText(HDActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Hiển thị mũi tên
            getSupportActionBar().setTitle("Thêm Mới Hóa Đơn");    // Tùy chỉnh tiêu đề
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Xử lý sự kiện khi nhấn vào mũi tên
            Intent intent = new Intent(HDActivity.this, HDListActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void autoGenerateMHD() {
        String mskh = edtMSKH.getText().toString();
        if (mskh.isEmpty()) {
            edtMHD.setText("HD1");
            edtDATE.setText("");
            edtDK.setText("");
            return;
        }
        Cursor cursor = hdDatabase.rawQuery(
                "SELECT mhd, date, ck FROM hd WHERE mskh = ? ORDER BY date DESC LIMIT 1",
                new String[]{mskh});
        if (cursor.moveToFirst()) {
            String lastMHD = cursor.getString(0);
            String lastDate = cursor.getString(1);
            int lastCK = cursor.getInt(2);
            int number = Integer.parseInt(lastMHD.replace(mskh + "HD", "")) + 1;
            edtMHD.setText(mskh + "HD" + number);
            // Hỗ trợ cả định dạng yyyy-MM hoặc MM/yyyy
            int month = 1, year = 2024; // giá trị mặc định để tránh lỗi
            if (lastDate.contains("-")) {
                String[] dateParts = lastDate.split("-");
                year = Integer.parseInt(dateParts[0]);
                month = Integer.parseInt(dateParts[1]);
            } else if (lastDate.contains("/")) {
                String[] dateParts = lastDate.split("/");
                month = Integer.parseInt(dateParts[0]);
                year = Integer.parseInt(dateParts[1]);
            }
            if (month == 12) {
                month = 1;
                year++;
            } else {
                month++;
            }
            String nextDate = String.format("%02d/%04d", month, year);
            edtDATE.setText(nextDate);
            edtDK.setText(String.valueOf(lastCK));
        } else {
            edtMHD.setText(mskh + "HD1");
            edtDATE.setText("");
            edtDK.setText("0");
        }
        cursor.close();
    }

    private void loadMSKH() {
        listMSKH = new ArrayList<>();
        Cursor cursor = khDatabase.rawQuery("SELECT mskh FROM kh", null);
        while (cursor.moveToNext()) {
            listMSKH.add(cursor.getString(0));
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, listMSKH);
        edtMSKH.setAdapter(adapter);
    }
}