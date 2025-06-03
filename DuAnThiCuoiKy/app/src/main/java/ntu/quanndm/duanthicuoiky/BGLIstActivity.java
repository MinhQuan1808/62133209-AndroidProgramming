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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BGLIstActivity extends AppCompatActivity {
    ListView lvBG;
    ArrayList<String> bgList;
    ArrayAdapter<String> bgAdapter;
    SQLiteDatabase bgDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bglist);
        lvBG = findViewById(R.id.lvBG);
        bgList = new ArrayList<>();
        bgAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bgList);
        lvBG.setAdapter(bgAdapter);
        bgDatabase = openOrCreateDatabase("bg.db", MODE_PRIVATE, null);
        bgDatabase.execSQL("CREATE TABLE IF NOT EXISTS bg (" +
                "muc TEXT PRIMARY KEY," +
                "chisobatdau TEXT," +
                "chisoketthuc TEXT," +
                "gia TEXT)");
        displayBG();
        lvBG.setOnItemClickListener((parent, view, position, id) -> {
            String selectedInvoice = bgList.get(position);
            String[] data = selectedInvoice.split(" - ");
            String muc = data[0];
            Cursor customerCursor = bgDatabase.query("bg", null, "muc = ?", new String[]{muc}, null, null, null);
            String chisobatdau = "", chisoketthuc = "", gia = "";

            if (customerCursor.moveToFirst()) {
                chisobatdau = customerCursor.getString(1);
                chisoketthuc = customerCursor.getString(2);
                gia = customerCursor.getString(3);
            }
            customerCursor.close();
            String message = muc + "\n" +
                    "Chỉ số bắt đầu: " + chisobatdau + "\n" +
                    "Chỉ số kết thúc: " + chisoketthuc + "\n" +
                    "Giá: " + gia;
            String finalChisobatdau = chisobatdau;
            String finalChisoketthuc = chisoketthuc;
            String finalGia = gia;
            new AlertDialog.Builder(BGLIstActivity.this)
                    .setTitle("Chi tiết biểu giá")
                    .setMessage(message)
                    .setPositiveButton("Sửa", (dialog, which) -> {
                        Intent intent = new Intent(BGLIstActivity.this, BGUpdateActivity.class);
                        intent.putExtra("muc", muc);
                        intent.putExtra("chisobatdau", finalChisobatdau);
                        intent.putExtra("chisoketthuc", finalChisoketthuc);
                        intent.putExtra("gia", finalGia);
                        startActivity(intent);
                    })
                    .setNegativeButton("Xóa", (dialog, which) -> {
                        new AlertDialog.Builder(BGLIstActivity.this)
                                .setTitle("Xác nhận xóa")
                                .setMessage("Bạn có chắc chắn muốn xóa không?")
                                .setPositiveButton("Có", (confirmDialog, confirmWhich) -> {
                                    int rowsDeleted = bgDatabase.delete("bg", "muc = ?", new String[]{muc});
                                    if (rowsDeleted > 0) {
                                        Toast.makeText(BGLIstActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                                        displayBG();  // Cập nhật danh sách
                                    } else {
                                        Toast.makeText(BGLIstActivity.this, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Không", null)
                                .show();
                    })
                    .setNeutralButton("Đóng", null)
                    .show();
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Hiển thị mũi tên ngược
            getSupportActionBar().setTitle("Biểu Giá");    // Tùy chỉnh tiêu đề
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }
    }
    private void displayBG() {
        bgList.clear();
        Cursor c = bgDatabase.query("bg", null, null, null, null, null, null);

        while (c.moveToNext()) {
            String data = c.getString(0) + " - " +
                    c.getString(1) + " - " +
                    c.getString(2) + " - " +
                    c.getString(3);
            bgList.add(data);
        }
        c.close();
        bgAdapter.notifyDataSetChanged();
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
            Intent intent = new Intent(this, BGActivity.class);
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