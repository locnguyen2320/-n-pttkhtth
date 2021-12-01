package vn.edu.stu.appquanlynhanvien.adapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import vn.edu.stu.appquanlynhanvien.R;
import vn.edu.stu.appquanlynhanvien.models.PhongBan;

public class PhongBanAdapter extends ArrayAdapter<PhongBan> {
    Activity contex;
    int resource;
    List<PhongBan> objects;
    public PhongBanAdapter(Activity contex, int resource, List<PhongBan> objects){
        super (contex, resource, objects);
        this.contex = contex;
        this.resource = resource;
        this.objects = objects;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = this.contex.getLayoutInflater();
        View item = inflater.inflate(this.resource,null);
        TextView txtmapb = item.findViewById(R.id.txtMaPB);
        TextView txttenpb = item.findViewById(R.id.txtTenPB);
        final PhongBan pb = this.objects.get(position);
        txtmapb.setText("Mã phòng ban:"+pb.getMaPB());
        txttenpb.setText("Tên phòng ban"+ pb.getTenPB());
        return  item;
    }
}
