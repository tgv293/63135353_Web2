package vn.gvt.QLTB.Services;

import vn.gvt.QLTB.Models.Lop;
import vn.gvt.QLTB.Repositories.LopRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LopServiceImpl implements LopService {

    @Autowired
    private LopRepository lopRepository;

    @Override
    public Lop getLopByID(String maLop) {
        return lopRepository.findByMaLop(maLop);
    }
    
    @Override
    public List<Lop> getListLop() {
        return lopRepository.findLopNotInPhieuMuon();
    }
    
    @Override
    public List<Lop> getAllListLop() {
        return lopRepository.findAll();
    }
    
    @Override
    @Transactional
    public boolean add(Lop lop) {
        try {
            lopRepository.save(lop);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
