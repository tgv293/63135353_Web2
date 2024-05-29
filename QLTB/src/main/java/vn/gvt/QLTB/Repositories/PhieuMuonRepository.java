package vn.gvt.QLTB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.gvt.QLTB.Models.PhieuMuon;

import java.time.LocalDateTime;
import java.util.List;

public interface PhieuMuonRepository extends JpaRepository<PhieuMuon, Integer> {
	@Query("select p from PhieuMuon p join p.listCTPhieuMuon c where c.nguoiMuon.maNguoiMuon = :id and p.thoiDiemTra is null")
	List<PhieuMuon> findPhieuMuonByNguoiMuonIDAndThoiDiemTraIsNull(Integer id);
	
	@Query("FROM PhieuMuon where maPhieuMuon = :id")
    PhieuMuon findPhieuMuonByID(@Param("id") Integer ID);
	
	@Query("FROM PhieuMuon WHERE thoiDiemMuon > :fromDate AND thoiDiemMuon < :toDate")
    List<PhieuMuon> findAllByThoidiemmuonBetween(@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate);

	@Query("FROM PhieuMuon WHERE hanTra < current_timestamp() and thoiDiemTra is null")
    List<PhieuMuon> findPhieuMuonsQuaHan();
	
}
