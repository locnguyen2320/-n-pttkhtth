package vn.edu.stu.appquanlynhanvien.models;

public class NhanVien {
    private String maPB;
    private String maNV;
    private String tenNV;
    private String luongCB;

    public NhanVien() {
    }

    public NhanVien( String maPB ,String maNV, String tenNV, String luongCB) {
        this.maPB = maPB;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.luongCB = luongCB;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getLuongCB() {
        return luongCB;
    }

    public void setLuongCB(String luongCB) {
        this.luongCB = luongCB;
    }

    public String getMaPB() {
        return maPB;
    }

    public void setMaPB(String maPB) {
        this.maPB = maPB;
    }

    public String toString(){
        return "Mã phòng ban:"+getMaPB()+" | " +"Mã nhân viên:"+ getMaNV()+" | "+"Tên nhân viên:"+getTenNV()+" | "+"Lương cơ bản:"+getLuongCB();
    }
}
