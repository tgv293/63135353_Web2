package vn.gvt.QLTB.Services;

import java.util.List;

import vn.gvt.QLTB.Models.NguoiMuon;
import vn.gvt.QLTB.Models.PhieuMuon;
import vn.gvt.QLTB.Models.ThietBi;

public interface CTPhieuMuonService {

    public Integer addPM_CT(PhieuMuon pm, List<ThietBi> dstb, NguoiMuon nm);
    
    public Integer addCTPM(PhieuMuon pm, List<ThietBi> dstb, NguoiMuon nm);
    
    public List<NguoiMuon> getNguoiMuon_PM(Integer ID);
    
    public int traTB(Integer maPM, Integer maTB, Integer maCTPM);
    
    public int baoMatTB(Integer maPM, Integer maTB);
}
