package vn.gvt.QLTB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.gvt.QLTB.Models.PhieuMuon;
import vn.gvt.QLTB.Repositories.PhieuMuonRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PhieuMuonServiceImpl implements PhieuMuonService {

    @Autowired
    private PhieuMuonRepository phieuMuonRepository;

    @Override
    public List<PhieuMuon> getLichSuMuon(Integer ID) {
        return phieuMuonRepository.findPhieuMuonByNguoiMuonIDAndThoiDiemTraIsNull(ID);
    }
    
    public PhieuMuon getPhieuMuonByID(Integer ID) {
        return phieuMuonRepository.findPhieuMuonByID(ID);
    }
    
    @Override
    public PhieuMuon save(PhieuMuon phieuMuon) {
        return phieuMuonRepository.save(phieuMuon);
    }
    
    public String getNow() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return time.format(formatter);
    }

    @Override
    public List<PhieuMuon> getAllPM(String fromDate, String toDate) {
        fromDate += " 00:00";
        toDate += " 23:59";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fromDateTime = LocalDateTime.parse(fromDate, formatter);
        LocalDateTime toDateTime = LocalDateTime.parse(toDate, formatter);
        return phieuMuonRepository.findAllByThoidiemmuonBetween(fromDateTime, toDateTime);
    }
    
    @Override
    public List<PhieuMuon> getPMQuaHan() {
        return phieuMuonRepository.findPhieuMuonsQuaHan();
    }
}