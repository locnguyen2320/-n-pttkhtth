package vn.edu.stu.appquanlynhanvien;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.stu.appquanlynhanvien.adapter.NhanVienAdapter;
import vn.edu.stu.appquanlynhanvien.models.NhanVien;
import vn.edu.stu.appquanlynhanvien.models.NhanVienDAO;
import vn.edu.stu.appquanlynhanvien.models.PhongBan;
import vn.edu.stu.appquanlynhanvien.models.PhongBanDAO;

public class QuanLyNhanVienActivity extends AppCompatActivity {
    Spinner spnpb;
    PhongBanDAO pbdao = null;
    NhanVienDAO nvdao = null;
    EditText edMaNV, edTenNV, edLuongCB, edmaPB;
    List<PhongBan> lsPB = new ArrayList<>();
    String strmapb;
    ArrayAdapter adapter;
    NhanVienAdapter nvadapter;
    ListView lvnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhan_vien);
        edMaNV = findViewById(R.id.edmanv);
        edTenNV = findViewById(R.id.edtennv);
        edLuongCB = findViewById(R.id.edluongnv);
        edmaPB = findViewById(R.id.edmapb);
        spnpb = findViewById(R.id.spnPB);
        lvnv = findViewById(R.id.lvNV);
        //
        nvdao = new NhanVienDAO(QuanLyNhanVienActivity.this);
        nvadapter = new NhanVienAdapter(this,R.layout.item_lvnv,nvdao.getAll());
        lvnv.setAdapter(nvadapter);

        lvnv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien nv = nvdao.getAll().get(position);
                edMaNV.setText(nv.getMaNV());
                edTenNV.setText(nv.getTenNV());
                edLuongCB.setText(nv.getLuongCB());
            }
        });
        lvnv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien nv = nvdao.getAll().get(position);
                edMaNV.setText(nv.getMaNV());
                edTenNV.setText(nv.getTenNV());
                edLuongCB.setText(nv.getLuongCB());
                if (position >= 0 && position < nvadapter.getCount()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(QuanLyNhanVienActivity.this);
                    builder.setTitle("Xác nhận xóa");
                    builder.setMessage("Bạn có chắc là muốn xóa nhân viên này không ?");
                    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    //hiển thị button để điều khiển
                    builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            xoanv(view);
                            nvadapter.notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                }
                return true;
            }
        });
        getpbs();
        spnpb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               strmapb = lsPB.get(spnpb.getSelectedItemPosition()).getMaPB();
               edmaPB.setText(strmapb);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void ThemNV(View view){
        NhanVien nv = new NhanVien(strmapb,edMaNV.getText().toString(), edTenNV.getText().toString(),edLuongCB.getText().toString());
        int rs = nvdao.add(nv);
        if(rs > 0){
            Toast.makeText(getApplicationContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
            nvadapter = new NhanVienAdapter(this, R.layout.item_lvnv,nvdao.getAll());
            lvnv.setAdapter(nvadapter);
            reset(view);
        }else{
            Toast.makeText(getApplicationContext(), "CO LOI", Toast.LENGTH_SHORT).show();
        }
    }
    public void xoanv(View view){
        int rs = nvdao.xoa(edMaNV.getText().toString());
        if(rs >= 0){
            Toast.makeText(getApplicationContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
            nvadapter = new NhanVienAdapter(this,R.layout.item_lvnv,nvdao.getAll());
            lvnv.setAdapter(nvadapter);
            reset(view);
        }else {
            Toast.makeText(getApplicationContext(), "Khong tim thay", Toast.LENGTH_SHORT).show();
        }
    }
    public void suanv(View view){
        NhanVien nv = new NhanVien(strmapb,edMaNV.getText().toString(),edTenNV.getText().toString(),edLuongCB.getText().toString());
        int rs = nvdao.update(nv);
        if(rs >= 0){
            Toast.makeText(getApplicationContext(), "Sua thanh cong", Toast.LENGTH_SHORT).show();
            nvadapter = new NhanVienAdapter(this,R.layout.item_lvnv,nvdao.getAll());
            lvnv.setAdapter(nvadapter);
            reset(view);
        }else {
            Toast.makeText(getApplicationContext(), "Khong tim thay", Toast.LENGTH_SHORT).show();
        }
    }
    public void reset(View view){
        edMaNV.setText("");
        edTenNV.setText("");
        edLuongCB.setText("");
    }
    public void getpbs(){
        pbdao = new PhongBanDAO(QuanLyNhanVienActivity.this);
        lsPB = pbdao.getAll();
        ArrayAdapter<PhongBan> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_dropdown_item,pbdao.getAll());
        spnpb.setAdapter(adapter);
    }
}