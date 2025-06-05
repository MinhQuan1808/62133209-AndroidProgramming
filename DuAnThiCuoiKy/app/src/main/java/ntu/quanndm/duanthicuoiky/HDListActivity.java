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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HDListActivity extends AppCompatActivity {
    EditText edtTKi;
    Button btnTKi;
    ListView lvInvoices;
    ArrayList<String> invoiceList;
    ArrayAdapter<String> invoiceAdapter;
    SQLiteDatabase hdDatabase;
    SQLiteDatabase khDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdlist);
        edtTKi = findViewById(R.id.edtTKi);
        btnTKi = findViewById(R.id.btnTKi);

        lvInvoices = findViewById(R.id.lvInvoice);
        invoiceList = new ArrayList<>();
        invoiceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, invoiceList);
        lvInvoices.setAdapter(invoiceAdapter);

        hdDatabase = openOrCreateDatabase("hd.db", MODE_PRIVATE, null);
        hdDatabase.execSQL("CREATE TABLE IF NOT EXISTS hd (" +
                "mskh TEXT," +
                "mhd TEXT PRIMARY KEY," +
                "date TEXT," +
                "dk INTEGER," +
                "ck INTEGER," +
                "tien INTEGER)");
        khDatabase = openOrCreateDatabase("kh.db", MODE_PRIVATE, null);
        displayInvoices();

        lvInvoices.setOnItemClickListener((parent, view, position, id) -> {
            String selectedInvoice = invoiceList.get(position);
            String[] data = selectedInvoice.split(" - ");
            String selectedMHD = data[1];
            String mskh = data[0];
            Cursor invoiceCursor = hdDatabase.query("hd", null, "mhd = ?", new String[]{selectedMHD}, null, null, null);
            String date = "";
            int dk = 0, ck = 0, tien = 0;
            if (invoiceCursor.moveToFirst()) {
                date = invoiceCursor.getString(2);
                dk = invoiceCursor.getInt(3);
                ck = invoiceCursor.getInt(4);
                tien = invoiceCursor.getInt(5);
            }
            invoiceCursor.close();
            Cursor customerCursor = khDatabase.query("kh", null, "mskh = ?", new String[]{mskh}, null, null, null);
            String htkh = "", diachi = "", sdt = "";
            if (customerCursor.moveToFirst()) {
                htkh = customerCursor.getString(1);
                diachi = customerCursor.getString(2);
                sdt = customerCursor.getString(3);
            }
            customerCursor.close();
            String formattedTien = NumberFormat.getInstance(Locale.GERMANY).format(tien);
            int totalKWh = ck - dk;
            int level1 = Math.min(50, totalKWh);
            int level2 = Math.min(50, Math.max(0, totalKWh - 50));
            int level3 = Math.min(100, Math.max(0, totalKWh - 100));
            int level4 = Math.min(100, Math.max(0, totalKWh - 200));
            int level5 = Math.min(100, Math.max(0, totalKWh - 300));
            int level6 = Math.max(0, totalKWh - 400);

            int costLevel1 = level1 * 1893;
            int costLevel2 = level2 * 1956;
            int costLevel3 = level3 * 2271;
            int costLevel4 = level4 * 2860;
            int costLevel5 = level5 * 3197;
            int costLevel6 = level6 * 3302;
            String message = "Mã khách hàng: " + mskh + "\n" +
                    "Tên khách hàng: " + htkh + "\n" +
                    "Địa chỉ: " + diachi + "\n" +
                    "Số điện thoại: " + sdt + "\n" +
                    "Mã hóa đơn: " + selectedMHD + "\n" +
                    "Tháng/Năm sử dụng: " + date + "\n" +
                    "Chỉ số đầu kỳ: " + dk + "\n" +
                    "Chỉ số cuối kỳ: " + ck + "\n" +
                    "Tổng kWh: " + totalKWh + " kWh" + "\n" +
                    "- Mức 1: " + level1 + " kWh. Thành tiền: " + NumberFormat.getInstance(Locale.GERMANY).format(costLevel1) + " VNĐ\n" +
                    "- Mức 2: " + level2 + " kWh. Thành tiền: " + NumberFormat.getInstance(Locale.GERMANY).format(costLevel2) + " VNĐ\n" +
                    "- Mức 3: " + level3 + " kWh. Thành tiền: " + NumberFormat.getInstance(Locale.GERMANY).format(costLevel3) + " VNĐ\n" +
                    "- Mức 4: " + level4 + " kWh. Thành tiền: " + NumberFormat.getInstance(Locale.GERMANY).format(costLevel4) + " VNĐ\n" +
                    "- Mức 5: " + level5 + " kWh. Thành tiền: " + NumberFormat.getInstance(Locale.GERMANY).format(costLevel5) + " VNĐ\n" +
                    "- Mức 6: " + level6 + " kWh. Thành tiền: " +NumberFormat.getInstance(Locale.GERMANY).format(costLevel6) + " VNĐ\n"+
                    "Tổng tiền: " + formattedTien + " VNĐ";

            String finalDate = date;
            int finalDk = dk;
            int finalCk = ck;
            int finalTien = tien;
            new AlertDialog.Builder(HDListActivity.this)
                    .setTitle("Chi tiết hóa đơn")
                    .setMessage(message)
                    .setPositiveButton("Sửa", (dialog, which) -> {
                        // Chuyển dữ liệu về HDUpdateActivity
                        Intent intent = new Intent(HDListActivity.this, HDUpdateActivity.class);
                        intent.putExtra("mskh", mskh);
                        intent.putExtra("mhd", selectedMHD);
                        intent.putExtra("date", finalDate);
                        intent.putExtra("dk", finalDk);
                        intent.putExtra("ck", finalCk);
                        intent.putExtra("tien", finalTien);
                        startActivity(intent);
                    })
                    .setNegativeButton("Xóa", (dialog, which) -> {
                        new AlertDialog.Builder(HDListActivity.this)
                                .setTitle("Xác nhận xóa")
                                .setMessage("Bạn có chắc chắn muốn xóa hóa đơn này không?")
                                .setPositiveButton("Có", (confirmDialog, confirmWhich) -> {
                                    int rowsDeleted = hdDatabase.delete("hd", "mhd = ?", new String[]{selectedMHD});
                                    if (rowsDeleted > 0) {
                                        Toast.makeText(HDListActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                                        displayInvoices();  // Cập nhật danh sách
                                    } else {
                                        Toast.makeText(HDListActivity.this, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Không", null)  // Đóng hộp thoại nếu chọn "Không"
                                .show();
                    })
                    .setNeutralButton("Đóng", null)
                    .show();
        });
        btnTKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tkMSKH = edtTKi.getText().toString();
                invoiceList.clear();
                Cursor c;
                if (tkMSKH.isEmpty()) {
                    c = hdDatabase.query("hd", null, null, null, null, null, "date DESC, mskh DESC");
                    Toast.makeText(HDListActivity.this, "Vui lòng nhập mã khách hàng để tìm kiếm", Toast.LENGTH_SHORT).show();
                } else {
                    c = hdDatabase.query("hd", null, "mskh = ?", new String[]{tkMSKH}, null, null, "date DESC, mskh DESC");
                }
                if (c.getCount() == 0) {
                    Toast.makeText(HDListActivity.this, "Không tìm thấy dữ liệu", Toast.LENGTH_SHORT).show();
                } else {
                    while (c.moveToNext()) {
                        String data = c.getString(0) + " - " +
                                c.getString(1) + " - " +
                                c.getString(2) + " - " +
                                c.getInt(3) + " - " +
                                c.getInt(4) + " - " +
                                c.getInt(5);
                        invoiceList.add(data);
                    }
                }
                c.close();
                invoiceAdapter.notifyDataSetChanged();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Hiển thị mũi tên
            getSupportActionBar().setTitle("Danh Sách Hóa Đơn");    // Tùy chỉnh tiêu đề
            getSupportActionBar().setDisplayShowHomeEnabled(true); // Bật hiển thị biểu tượng Home
            getSupportActionBar().setDisplayUseLogoEnabled(true); // Cho phép sử dụng logo ứng dụng thay cho biểu tượng mặc định
        }

    }

    private void displayInvoices() {
        invoiceList.clear();
        Cursor c = hdDatabase.query("hd", null, null, null, null, null, "date DESC, mskh DESC");

        while (c.moveToNext()) {
            String data = c.getString(0) + " - " +
                    c.getString(1) + " - " +
                    c.getString(2) + " - " +
                    c.getInt(3) + " - " +
                    c.getInt(4) + " - " +
                    c.getInt(5);
            invoiceList.add(data);
        }
        c.close();
        invoiceAdapter.notifyDataSetChanged();
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
            Intent intent = new Intent(this, HDActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == android.R.id.home) {  // Xử lý mũi tên
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

