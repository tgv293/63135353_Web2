package vn.gvt.QLTB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.transaction.annotation.Transactional;

import vn.gvt.QLTB.Models.Phong;

public interface PhongRepository extends JpaRepository<Phong, String> {
    Phong findByMaPhong(String maPhong);
    
    @Procedure("sp_baotriphong")
    void callSpBaoTriPhong(String maPhong);

    @Procedure("sp_hoantatbaotriphong")
    void callSpHoanThanhBaoTriPhong(String maPhong);
    
    @Modifying
    @Transactional
    @Query("delete from Phong s where s.maPhong = :id")
    Integer deleteByMaPhong(String id);

}