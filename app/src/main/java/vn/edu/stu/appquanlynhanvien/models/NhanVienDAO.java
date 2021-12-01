package vn.edu.stu.appquanlynhanvien.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.stu.appquanlynhanvien.database.DatabaseHelper;

public class NhanVienDAO {
    private SQLiteDatabase db;
    private DatabaseHelper helper;
    private static final String TABLE_NAME="NhanViens";

    public NhanVienDAO(Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }
    public List<NhanVien> getAll(){
        List<NhanVien> ls = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            NhanVien nv = new NhanVien();
            nv.setMaNV(c.getString(0));
            nv.setTenNV(c.getString(1));
            nv.setLuongCB(c.getString(2));
            nv.setMaPB(c.getString(3));
            ls.add(nv);
            Log.d("//=====", nv.toString());
            c.moveToNext();
        }
        return ls;
    }
    public int add(NhanVien nv){
        ContentValues values = new ContentValues();
        values.put("maPB", nv.getMaPB());
        values.put("MaNV",nv.getMaNV() );
        values.put("TenNV", nv.getTenNV());
        values.put("LuongCB", nv.getLuongCB());
        try {
            if (db.insert(TABLE_NAME,null, values)==-1){
                return -1;
            }
        }catch (Exception ex){
            Log.e("Loi", ex.toString());
        }
        return 1;
    }
    public int update(NhanVien nv){
        ContentValues values = new ContentValues();
        values.put("maPB", nv.getMaPB());
        values.put("MaNV",nv.getMaNV() );
        values.put("TenNV", nv.getTenNV());
        values.put("LuongCB", nv.getLuongCB());
        int res = db.update(TABLE_NAME,values,"MaNV=?", new String[]{nv.getMaNV()});
        try{
            if (res == 0)
                return -1;
        }catch (Exception ex){
            Log.e("LOI DATABASE", ex.toString());
        }
        return 1;
    }
    public int xoa(String MaNV){
        int res = db.delete(TABLE_NAME,"MaNV=?",new String[]{MaNV});
        if (res == 0)
            return -1;
        return 1;
    }
}
