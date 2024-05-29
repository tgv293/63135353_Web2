package vn.gvt.QLTB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import vn.gvt.QLTB.Models.NhanVien;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
	@Procedure("sp_resetPass")
    void spResetPass(String maNV);

}
