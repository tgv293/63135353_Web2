package vn.gvt.QLTB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.gvt.QLTB.Models.CTPhieuMuon;
import vn.gvt.QLTB.Models.PhieuMuon;
import vn.gvt.QLTB.Models.ThietBi;
import vn.gvt.QLTB.Models.NguoiMuon;
import vn.gvt.QLTB.Repositories.CTPhieuMuonRepository;
import vn.gvt.QLTB.Repositories.TrangThaiRepository;

import java.util.List;

@Service
public class CTPhieuMuonServiceImpl implements CTPhieuMuonService {

    @Autowired
    private CTPhieuMuonRepository ctPhieuMuonRepository;
    
    @Autowired
    private TrangThaiRepository trangThaiRepository;

    @Override
    @Transactional
    public Integer addPM_CT(PhieuMuon pm, List<ThietBi> dstb, NguoiMuon nm) {
        try {
            for (ThietBi thietBi : dstb) {
                CTPhieuMuon ctpm = new CTPhieuMuon();
                ctpm.setPhieuMuon(pm);
                ctpm.setThietBi(thietBi);
                ctpm.setNguoiMuon(nm);
                ctpm.setTrangThai(trangThaiRepository.findByMaTinhTrang(4));
                ctPhieuMuonRepository.save(ctpm);
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    @Transactional
    public Integer addCTPM(PhieuMuon pm, List<ThietBi> dstb, NguoiMuon nm) {
        try {
            for (ThietBi thietBi : dstb) {
                CTPhieuMuon ctpm = new CTPhieuMuon();
                ctpm.setPhieuMuon(pm);
                ctpm.setThietBi(thietBi);
                ctpm.setNguoiMuon(nm);
                ctpm.setTrangThai(trangThaiRepository.findByMaTinhTrang(4));
                ctPhieuMuonRepository.save(ctpm);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            return 0;
        }

        return 1;
    }
    
    @Override
    public List<NguoiMuon> getNguoiMuon_PM(Integer ID) {
        return ctPhieuMuonRepository.getNguoiMuon_PM(ID);
    }
    
    @Override
    public int traTB(Integer maPM, Integer maTB, Integer maCTPM) {
        return ctPhieuMuonRepository.updateTrangThai(maPM, maTB, maCTPM);
    }
    
    @Override
    public int baoMatTB(Integer maPM, Integer maTB) {
        return ctPhieuMuonRepository.updateTrangThaiBaoMat(maPM, maTB);
    }
    
    
}
