package vn.gvt.QLTB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.gvt.QLTB.Models.TaiKhoan;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TaiKhoanService taiKhoanService;

    @Override
    public Boolean checkLogin(String user, String pass) {
        return taiKhoanService.checkLogin(user, pass);
    }

    @Override
    public TaiKhoan getTKByID(String ID) {
        return taiKhoanService.getTKByID(ID);
    }
}
