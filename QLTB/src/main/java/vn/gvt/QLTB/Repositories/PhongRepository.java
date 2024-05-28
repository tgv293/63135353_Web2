package vn.gvt.QLTB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import vn.gvt.QLTB.Models.Phong;

public interface PhongRepository extends JpaRepository<Phong, String> {
	Phong findByMaPhong(String maPhong);
	
	@Procedure("sp_baotriphong")
    void callSpBaoTriPhong(String maPhong);

}
