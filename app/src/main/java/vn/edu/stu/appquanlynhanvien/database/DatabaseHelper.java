package vn.edu.stu.appquanlynhanvien.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbQuanLyNhanVien";
    public static final int VERSION_DB = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_DB);
    }

    public void onCreate(SQLiteDatabase db){
        String sSQL_PhongBan = "CREATE TABLE PhongBans(MaPB TEXT PRIMARY KEY, TenPB TEXT)";
        String sSQL_NhanViens = " CREATE TABLE NhanViens(MaNV TEXT PRIMARY KEY, TenNV TEXT, LuongCB TEXT, maPB text)";
        db.execSQL(sSQL_PhongBan);
        db.execSQL(sSQL_NhanViens);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("Drop table if exists PhongBans");
        db.execSQL("Drop table if exists NhanViens");
        onCreate(db);
    }
}
