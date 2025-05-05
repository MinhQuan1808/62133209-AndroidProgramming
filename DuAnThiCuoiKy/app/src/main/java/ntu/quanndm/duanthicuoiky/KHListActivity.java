package ntu.quanndm.duanthicuoiky;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class KHListActivity extends AppCompatActivity {
    EditText edtTKC;
    Button btnTKC;
    ListView lvcustomer;
    ArrayList<String> customerList;
    ArrayAdapter<String> customerAdapter;
    SQLiteDatabase khDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khlist);
        edtTKC = findViewById(R.id.edtTKC);
        btnTKC = findViewById(R.id.btnTKC);

        lvcustomer = findViewById(R.id.lvcustomer);
        customerList = new ArrayList<>();
        customerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, customerList);
        lvcustomer.setAdapter(customerAdapter);

        khDatabase = openOrCreateDatabase("kh.db", MODE_PRIVATE, null);

        khDatabase.execSQL("CREATE TABLE IF NOT EXISTS kh (" +
                "mskh TEXT PRIMARY KEY," +
                "htkh TEXT," +
                "diachi TEXT," +
                "sdt TEXT)");

        displayCustomer();

        lvcustomer.setOnItemClickListener((parent, view, position, id) -> {
            String selectedInvoice = customerList.get(position);
            String[] data = selectedInvoice.split(" - ");
            String mskh = data[0];

            Cursor customerCursor = khDatabase.query("kh", null, "mskh = ?", new String[]{mskh}, null, null, null);
            String htkh = "", diachi = "", sdt = "";

            if (customerCursor.moveToFirst()) {
                htkh = customerCursor.getString(1);
                diachi = customerCursor.getString(2);
                sdt = customerCursor.getString(3);
            }
            customerCursor.close();

            String message = "Mã khách hàng: " + mskh + "\n" +
                    "Tên khách hàng: " + htkh + "\n" +
                    "Địa chỉ: " + diachi + "\n" +
                    "Số điện thoại: " + sdt;

            String finalHtkh = htkh;
            String finalDiachi = diachi;
            String finalSdt = sdt;
            new AlertDialog.Builder(KHListActivity.this)
                    .setTitle("Chi tiết khách hàng")
                    .setMessage(message)
                    .setPositiveButton("Sửa", (dialog, which) -> {
                        Intent intent = new Intent(KHListActivity.this, KHUpdateActivity.class);
                        intent.putExtra("mskh", mskh);
                        intent.putExtra("htkh", finalHtkh);
                        intent.putExtra("diachi", finalDiachi);
                        intent.putExtra("sdt", finalSdt);
                        startActivity(intent);
                    })
                    .setNegativeButton("Xóa", (dialog, which) -> {
                        new AlertDialog.Builder(KHListActivity.this)
                                .setTitle("Xác nhận xóa")
                                .setMessage("Bạn có chắc chắn muốn xóa không?")
                                .setPositiveButton("Có", (confirmDialog, confirmWhich) -> {
                                    int rowsDeleted = khDatabase.delete("kh", "mskh = ?", new String[]{mskh});
                                    if (rowsDeleted > 0) {
                                        Toast.makeText(KHListActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                                        displayCustomer();  // Cập nhật danh sách
                                    } else {
                                        Toast.makeText(KHListActivity.this, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Không", null)
                                .show();
                    })
                    .setNeutralButton("Đóng", null)
                    .show();
        });
        btnTKC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = edtTKC.getText().toString().trim();
                customerList.clear();
                Cursor c;
                if (keyword.isEmpty()) {
                    // Truy vấn toàn bộ dữ liệu khi MSKH trống
                    c = khDatabase.query("kh", null, null, null, null, null, "mskh DESC");
                    Toast.makeText(KHListActivity.this, "Vui lòng nhập để tìm kiếm", Toast.LENGTH_SHORT).show();
                } else {
                    // Truy vấn theo MSKH nếu người dùng nhập
                    c = khDatabase.query(
                            "kh",
                            null,
                            "mskh LIKE ? OR htkh LIKE ? OR sdt LIKE ?",
                            new String[]{"%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%"},
                            null, null, null
                    );
                }
                if (c.getCount() == 0) {
                    Toast.makeText(KHListActivity.this, "Không tìm thấy dữ liệu", Toast.LENGTH_SHORT).show();
                } else {
                    while (c.moveToNext()) {
                        String data = c.getString(0) + " - " +
                                c.getString(1) + " - " +
                                c.getString(2) + " - " +
                                c.getString(3);
                        customerList.add(data);
                    }
                }
                c.close();
                customerAdapter.notifyDataSetChanged();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Hiển thị mũi tên ngược
            getSupportActionBar().setTitle("Danh Sách Khách Hàng");    // Tùy chỉnh tiêu đề
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }
    }
    private void displayCustomer() {
        customerList.clear();
        Cursor c = khDatabase.query("kh", null, null, null, null, null, "mskh DESC");

        while (c.moveToNext()) {
            String data = c.getString(0) + " - " +
                    c.getString(1) + " - " +
                    c.getString(2) + " - " +
                    c.getString(3);
            customerList.add(data);
        }
        c.close();
        customerAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem addItem = menu.add(0, 1, 0, "Thêm");

        // Tạo vòng tròn trắng bao quanh
        GradientDrawable circleDrawable = new GradientDrawable();
        circleDrawable.setShape(GradientDrawable.OVAL);
        circleDrawable.setColor(Color.BLACK);  // Màu nền
        circleDrawable.setSize(100, 100);  // Kích thước vòng tròn

        // Tạo icon dấu cộng
        Drawable icon = getDrawable(android.R.drawable.ic_input_add);
        Drawable wrappedIcon = new LayerDrawable(new Drawable[]{circleDrawable, icon});

        addItem.setIcon(wrappedIcon);
        addItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);  // Hiển thị trên thanh tiêu đề
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == 1) {  // Xử lý khi nhấn nút thêm
            Intent intent = new Intent(this, KHActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == android.R.id.home) {  // Xử lý mũi tên ngược
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

