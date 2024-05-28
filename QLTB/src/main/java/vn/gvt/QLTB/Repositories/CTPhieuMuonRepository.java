package vn.gvt.QLTB.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import vn.gvt.QLTB.Models.CTPhieuMuon;
import vn.gvt.QLTB.Models.NguoiMuon;

public interface CTPhieuMuonRepository extends JpaRepository<CTPhieuMuon, Integer> {
	@Query("select distinct c.nguoiMuon from CTPhieuMuon c where c.phieuMuon.maPhieuMuon = :id")
	List<NguoiMuon> getNguoiMuon_PM(Integer id);
	
	@Modifying
	@Transactional
	@Query("UPDATE CTPhieuMuon s SET s.trangThai.maTinhTrang = 5 WHERE s.phieuMuon.maPhieuMuon = :maPM AND s.thietBi.maThietBi = :maTB AND s.maCTPhieuMuon = :maCTPM")
	int updateTrangThai(@Param("maPM") Integer maPM, @Param("maTB") Integer maTB, @Param("maCTPM") Integer maCTPM);

	@Modifying
	@Transactional
	@Query("UPDATE CTPhieuMuon s SET s.trangThai.maTinhTrang = 6 WHERE s.phieuMuon.maPhieuMuon = :maPM AND s.thietBi.maThietBi = :maTB")
	int updateTrangThaiBaoMat(@Param("maPM") Integer maPM, @Param("maTB") Integer maTB);

}