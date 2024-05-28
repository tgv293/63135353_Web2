package vn.gvt.QLTB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.gvt.QLTB.Models.TaiKhoan;
import vn.gvt.QLTB.Repositories.TaiKhoanRepository;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public Boolean checkLogin(String user, String pass) {
        return taiKhoanRepository.checkLogin(user, pass);
    }

    @Override
    public TaiKhoan getTKByID(String ID) {
        return taiKhoanRepository.getTKByID(ID);
    }
}
