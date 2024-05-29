package vn.gvt.QLTB.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.gvt.QLTB.Models.Lop;

public interface LopRepository extends JpaRepository<Lop, String> {
    Lop findByMaLop(String maLop);
    
    @Query("select l from Lop l where l.maLop not in (select distinct p.lop.maLop from PhieuMuon p where p.thoiDiemTra is null)")
    List<Lop> findLopNotInPhieuMuon();
}
