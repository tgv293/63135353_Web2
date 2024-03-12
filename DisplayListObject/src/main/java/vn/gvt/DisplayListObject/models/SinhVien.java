package vn.gvt.DisplayListObject.models;

public class SinhVien {
	private String maSoSV;
	private String hoVaTen;
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
	
	public SinhVien() {
		super();
	}
	public SinhVien(String maSoSV, String hoVaTen) {
		this.maSoSV = maSoSV;
		this.hoVaTen = hoVaTen;
	}
}
