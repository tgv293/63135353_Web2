package vn.gvt.QLTB.Services;

import vn.gvt.QLTB.Models.TaiKhoan;

public interface TaiKhoanService {
	public TaiKhoan getTKByID(String ID);

	public boolean checkLogin(String user, String pass);

	public boolean checkTKByID(String ID);

	public Boolean changePassword(String username, String oldPassword, String newPassword);
}
