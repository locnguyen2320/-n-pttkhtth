package vn.edu.stu.appquanlynhanvien;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import vn.edu.stu.appquanlynhanvien.adapter.PhongBanAdapter;
import vn.edu.stu.appquanlynhanvien.models.NhanVien;
import vn.edu.stu.appquanlynhanvien.models.PhongBan;
import vn.edu.stu.appquanlynhanvien.models.PhongBanDAO;

public class QuanLyPhongBanActivity extends AppCompatActivity {
    Button btnThem, btnCancel;
    EditText edmaPB, edtenPB;
    PhongBanDAO dao ;
    ListView lvPB;
    ArrayAdapter adapter;
    PhongBanAdapter pbadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_phong_ban);
        edmaPB = findViewById(R.id.edmapb);
        edtenPB = findViewById(R.id.edtenpb);
        btnThem = findViewById(R.id.btnThem);
        btnCancel = findViewById(R.id.btnCancelPB);
        lvPB = findViewById(R.id.lvPB);
        //
        dao = new PhongBanDAO(QuanLyPhongBanActivity.this);
        pbadapter
                = new PhongBanAdapter(this, R.layout.item_lvpb,dao.getAll());
        lvPB.setAdapter(pbadapter);
        lvPB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhongBan pb = dao.getAll().get(position);
                edmaPB.setText(pb.getMaPB());
                edtenPB.setText(pb.getTenPB());
            }
        });
        lvPB.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PhongBan pb = dao.getAll().get(position);
                edmaPB.setText(pb.getMaPB());
                edtenPB.setText(pb.getTenPB());

                if (position >= 0 && position < pbadapter.getCount()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(QuanLyPhongBanActivity.this);
                    builder.setTitle("Xác nhận xóa");
                    builder.setMessage("Bạn có chắc là muốn xóa phòng ban này không ?");
                    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    //hiển thị button để điều khiển
                    builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            xoapb(view);
                            pbadapter.notifyDataSetChanged();
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
    }
    public void reset(View view){
        edtenPB.setText("");
        edmaPB.setText("");
    }
    public void ThemPB(View view){
        PhongBan pb = new PhongBan(edmaPB.getText().toString(),edtenPB.getText().toString());
        int rs = dao.add(pb);
        if(rs > 0){
            Toast.makeText(getApplicationContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
            pbadapter
                    = new PhongBanAdapter(this,R.layout.item_lvpb,dao.getAll());
            lvPB.setAdapter(pbadapter);
            reset(view);
        }else{
            Toast.makeText(getApplicationContext(), "CO LOI", Toast.LENGTH_SHORT).show();
        }
    }
    public void suapb(View view){
        PhongBan pb = new PhongBan(edmaPB.getText().toString(),edtenPB.getText().toString());
        int rs = dao.update(pb);
        if(rs >= 0){
            Toast.makeText(getApplicationContext(), "Sua thanh cong", Toast.LENGTH_SHORT).show();
            pbadapter
                    = new PhongBanAdapter(this, R.layout.item_lvpb,dao.getAll());
            lvPB.setAdapter(pbadapter);
            reset(view);
        }else {
            Toast.makeText(getApplicationContext(), "Khong tim thay", Toast.LENGTH_SHORT).show();
        }
    }
    public void xoapb(View view){
        int rs = dao.xoa(edmaPB.getText().toString());
        if(rs >= 0){
            Toast.makeText(getApplicationContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
            pbadapter
                    = new PhongBanAdapter(this, R.layout.item_lvpb,dao.getAll());
            lvPB.setAdapter(pbadapter);
            reset(view);
        }else {
            Toast.makeText(getApplicationContext(), "Khong tim thay", Toast.LENGTH_SHORT).show();
        }
    }
}