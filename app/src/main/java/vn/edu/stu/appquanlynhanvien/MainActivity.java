package vn.edu.stu.appquanlynhanvien;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnPB, btnNV;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPB = findViewById(R.id.btnPB);
        btnNV = findViewById(R.id.btnNV);
        intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void quanLyPB(View view){
        intent = new Intent(MainActivity.this, QuanLyPhongBanActivity.class);
        startActivity(intent);
    }
    public void quanLyNV(View view){
        intent = new Intent(MainActivity.this, QuanLyNhanVienActivity.class);
        startActivity(intent);
    }
}