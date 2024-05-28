package vn.gvt.QLTB.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.gvt.QLTB.Models.NguoiMuon;
import vn.gvt.QLTB.Repositories.NguoiMuonRepository;


@Service
public class NguoiMuonServiceImpl implements NguoiMuonService {

    @Autowired
    private NguoiMuonRepository NguoiMuonRepository;

    @Override
    public NguoiMuon getNMByID(String maNM) {
        return NguoiMuonRepository.findByMaNguoiMuon(maNM);
    }

    @Override
    public NguoiMuon addNM(NguoiMuon nm) {
        return NguoiMuonRepository.save(nm);
    }
}