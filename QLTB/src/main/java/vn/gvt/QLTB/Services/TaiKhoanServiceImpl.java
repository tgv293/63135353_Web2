package vn.gvt.QLTB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import vn.gvt.QLTB.Models.TaiKhoan;
import vn.gvt.QLTB.Repositories.TaiKhoanRepository;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {

	@Autowired
	private TaiKhoanRepository taiKhoanRepository;

	@Autowired
	private EntityManager entityManager;

	@Override
	public boolean checkLogin(String user, String pass) {
		return taiKhoanRepository.checkLogin(entityManager, user, pass);
	}

	@Override
	public TaiKhoan getTKByID(String ID) {
		return taiKhoanRepository.getTKByID(entityManager, ID);
	}

	@Override
	public boolean checkTKByID(String ID) {
		return taiKhoanRepository.findById(ID).isPresent();
	}

	@Override
	public Boolean changePassword(String username, String oldPassword, String newPassword) {
		return taiKhoanRepository.changePass(username, oldPassword, newPassword);
	}
}
