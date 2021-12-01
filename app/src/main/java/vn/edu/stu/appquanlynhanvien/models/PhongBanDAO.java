package vn.edu.stu.appquanlynhanvien.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.stu.appquanlynhanvien.database.DatabaseHelper;

public class PhongBanDAO {
    private SQLiteDatabase db;
    private static final String TABLE_NAME = "PhongBans";

    public static List<PhongBan> lspb = new ArrayList<>();

    public PhongBanDAO(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int add(PhongBan pb){
        ContentValues values = new ContentValues();
        values.put("MaPB",pb.getMaPB() );
        values.put("TenPB", pb.getTenPB());
        try{
            if (db.insert(TABLE_NAME,null,values)==-1){
                return -1;
            }
        }catch (Exception ex){
            Log.e("LOI DATABASE", ex.toString());
        }
        return 1;
    }
    public List<PhongBan> getAll(){
        List<PhongBan> ls = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            PhongBan pb = new PhongBan();
            pb.setMaPB(c.getString(0));
            pb.setTenPB(c.getString(1));
            ls.add(pb);
            c.moveToNext();
        }
        return ls;
    }
    public int update(PhongBan pb){
        ContentValues values = new ContentValues();
        values.put("MaPB",pb.getMaPB() );
        values.put("TenPB", pb.getTenPB());
        try{
            if (db.update(TABLE_NAME,values,"MaPB=?", new String[]{pb.getMaPB()})==-1){
                return -1;
            }
        }catch (Exception ex){
            Log.e("LOI DATABASE", ex.toString());
        }
        return 1;
    }
    public int xoa(String id){
        int res = db.delete(TABLE_NAME, "MaPb=?", new String[]{id});
        if(res == 0 )
            return -1;
        return 1;
    }
    public int timid(String id){
        for(int i=0; i<getAll().size();i++){
            PhongBan pb = lspb.get(i);
            if (pb.getMaPB().equals(id)){
                return i;
            }
        }
        return -1;
    }

}
