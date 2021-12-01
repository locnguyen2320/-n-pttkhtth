package vn.edu.stu.appquanlynhanvien.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import vn.edu.stu.appquanlynhanvien.R;
import vn.edu.stu.appquanlynhanvien.models.NhanVien;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {
    Activity contex;
    int resource;
    List<NhanVien> objects;

    public NhanVienAdapter(Activity contex, int resource, List<NhanVien> objects){
        super (contex, resource, objects);
        this.contex = contex;
        this.resource = resource;
        this.objects = objects;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = this.contex.getLayoutInflater();
        View item = inflater.inflate(this.resource,null);
        TextView txtmapb = item.findViewById(R.id.txtMaPB);
        TextView txtmanv = item.findViewById(R.id.txtMaNV);
        TextView txttennv = item.findViewById(R.id.txtTenNV);
        TextView txtluongcb= item.findViewById(R.id.txtLuongCB);
        final NhanVien nv = this.objects.get(position);
        txtmapb.setText("Mã phòng ban:"+nv.getMaPB());
        txtmanv.setText("Mã nhân viên:"+nv.getMaNV());
        txttennv.setText("Tên nhân viên"+ nv.getTenNV());
        txtluongcb.setText("Lương cơ bản:"+nv.getLuongCB());
        return  item;
    }
}
