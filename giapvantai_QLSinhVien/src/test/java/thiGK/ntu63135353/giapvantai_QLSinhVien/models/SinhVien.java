package thiGK.ntu63135353.giapvantai_QLSinhVien.models;

public class SinhVien {
    private String maSoSV;
    private String hoVaTen;
    private int diemTichLuy;

    public SinhVien(String maSoSV, String hoVaTen, int diemTichLuy) {
        this.maSoSV = maSoSV;
        this.hoVaTen = hoVaTen;
        this.diemTichLuy = diemTichLuy;
    }

    public String getMaSoSV() {
        return maSoSV;
    }

    public void setMaSoSV(String maSoSV) {
        this.maSoSV = maSoSV;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public int getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(int diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }
}

