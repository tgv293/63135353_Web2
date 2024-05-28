package vn.gvt.QLTB.Services;

import vn.gvt.QLTB.Models.TaiKhoan;

public interface LoginService {

    public Boolean checkLogin(String user, String pass);

    public TaiKhoan getTKByID(String ID);
}
