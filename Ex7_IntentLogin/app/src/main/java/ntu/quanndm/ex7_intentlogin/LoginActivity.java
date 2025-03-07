package ntu.quanndm.ex7_intentlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnOK = findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtUserName = findViewById(R.id.edtUserName);
                EditText edtPass = findViewById(R.id.edtPass);
                EditText edtMail = findViewById(R.id.edtMail);
                String tenDangNhap = edtUserName.getText().toString();
                String mk = edtPass.getText().toString();
                String mail = edtMail.getText().toString();
                if (tenDangNhap.equals("nguyendinhminhquan") && mk.equals("62133209"))
                {
                    Intent iQuiz = new Intent(LoginActivity.this, HomeActivity.class);
                    iQuiz.putExtra("ten_dang_nhap",tenDangNhap);
                    iQuiz.putExtra("mk_dang_nhap", mk);
                    startActivity(iQuiz);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Bạn nhập sai thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}