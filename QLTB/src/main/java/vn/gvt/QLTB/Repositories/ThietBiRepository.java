package vn.gvt.QLTB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import vn.gvt.QLTB.Models.ThietBi;

import java.util.List;

public interface ThietBiRepository extends JpaRepository<ThietBi, Integer> {
    @Query("select t from ThietBi t where (t.phong.maPhong = :id or t.phong.maPhong = 'CSVC') and t.tinhTrangTB.maTinhTrang = 1")
    List<ThietBi> findChoPhepMuon(String id);
    
    @Query("FROM ThietBi WHERE (phong.maPhong = :phong OR phong.maPhong= 'CSVC') AND loaiThietBi.maLoai= :tb AND tinhTrangTB.maTinhTrang= 1")
    ThietBi findThietBiChuaSD(@Param("phong") String phong, @Param("tb") Integer loaiTB);
    
    @Query("FROM ThietBi where phong is null")
    List<ThietBi> findByPhongIsNull();

    @Transactional
    @Modifying
    @Query("UPDATE ThietBi set phong.maPhong= :maPhong where maThietBi= :matb")
    int updatePhongForThietBi(String maPhong, Integer matb);
    
    @Procedure("sp_baotrithietbi")
    void callSpBaoTriThietBi(Integer maTB);
    
    @Procedure("sp_baomatthietbi")
    void callSpBaoMatThietBi(Integer maTB);
    
    @Procedure("sp_hoantatbaotrithietbi")
    void callSpHoanThanhBTThietBi(Integer maTB);
    
    List<ThietBi> findByPhong_MaPhong(String maPhong);

    @Transactional
    @Modifying
    @Query("delete from ThietBi s where s.maThietBi = :id")
    int deleteByMaTBi(Integer id);
}