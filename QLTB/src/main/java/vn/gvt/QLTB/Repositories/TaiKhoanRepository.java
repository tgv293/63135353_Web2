package vn.gvt.QLTB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import vn.gvt.QLTB.Models.TaiKhoan;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, String> {

    default Boolean checkLogin(EntityManager entityManager, String user, String pass) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_kiemTraDangNhap");
        query.registerStoredProcedureParameter("UserName", String.class, ParameterMode.IN).setParameter("UserName", user);
        query.registerStoredProcedureParameter("Pass", String.class, ParameterMode.IN).setParameter("Pass", pass);
        query.registerStoredProcedureParameter("Result", Boolean.class, ParameterMode.OUT);
        Boolean check = (Boolean) query.getOutputParameterValue("Result");
        System.out.println(check);
        return check;
    }

    default TaiKhoan getTKByID(EntityManager entityManager, String ID) {
    	System.out.println(ID);
    	System.out.println(entityManager.find(TaiKhoan.class, ID));
        return entityManager.find(TaiKhoan.class, ID);
    }
    
    @Procedure("sp_changepass")
    Boolean changePass(String username, String passold, String newPass);
}
