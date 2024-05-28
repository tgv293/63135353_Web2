package vn.gvt.QLTB.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import vn.gvt.QLTB.Models.TaiKhoan;

@Repository
public class TaiKhoanRepository {
    @Autowired
    private EntityManager entityManager;

    public Boolean checkLogin(String user, String pass) {
    	StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_kiemTraDangNhap");
        query.registerStoredProcedureParameter("UserName", String.class, ParameterMode.IN).setParameter("UserName", user);
        query.registerStoredProcedureParameter("Pass", String.class, ParameterMode.IN).setParameter("Pass", pass);
        query.registerStoredProcedureParameter("Result", Boolean.class, ParameterMode.OUT);
        Boolean check = (Boolean) query.getOutputParameterValue("Result");
        return check;
    }

    public TaiKhoan getTKByID(String ID) {
    	return entityManager.find(TaiKhoan.class, ID);
    }
}
