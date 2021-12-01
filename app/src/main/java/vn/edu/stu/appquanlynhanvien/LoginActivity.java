package vn.edu.stu.appquanlynhanvien;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edusername, edpassword;
    Button btnCancel, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edusername = findViewById(R.id.edusername);
        edpassword = findViewById(R.id.edpassword);
        btnCancel = findViewById(R.id.btncancel);
        btnLogin = findViewById(R.id.btnlogin);
    }
    public void checkLogin(View view){
        String u = edusername.getText().toString();
        String p = edpassword.getText().toString();
        if(u.equals("stu")&&p.equals("123")){
            Toast.makeText(getApplicationContext(), "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(getApplicationContext(), "Dang nhap that bai", Toast.LENGTH_SHORT).show();
        }

    }
    public void resetform(View view){
        edusername.setText("");
        edpassword.setText("");

    }
}