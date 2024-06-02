package vn.gvt.QLTB.Services;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.gvt.QLTB.Models.Phong;
import vn.gvt.QLTB.Models.ThietBi;
import vn.gvt.QLTB.Repositories.PhongRepository;


@Service
public class PhongServiceImpl implements PhongService{

    private PhongRepository phongRepository;
    private ThietBiService thietBiService;
    
    @Autowired
	public PhongServiceImpl(PhongRepository phongRepository, ThietBiService thietBiService) {
		this.phongRepository = phongRepository;
		this.thietBiService = thietBiService;
	}

    @Override
    public List<Phong> getPhong() {
        return phongRepository.findAll();
    }
    
    @Override
    public Phong getPhongByID(String maPhong) {
        return phongRepository.findByMaPhong(maPhong);
    }
    
    @Override
    public Map<String, Integer> dsTBChoPhepMuon(String maPhong) {
    	Phong phong = Optional.ofNullable(phongRepository.findByMaPhong(maPhong))
    		    .orElseThrow(() -> new NoSuchElementException("No Phong found with maPhong: " + maPhong));
        
        int count = 1;
        Map<String, Integer> map = new HashMap<>();
        Set<String> set1 = new LinkedHashSet<String>();
        for (ThietBi ctThietBi : phong.getDstb()) {
            if(ctThietBi.getTinhTrangTB().getMaTinhTrang()==1) {
                if (!set1.add(ctThietBi.getLoaiThietBi().getTenLoai())) {
                    count = map.get(ctThietBi.getLoaiThietBi().getTenLoai()) + 1;
                }
                map.put(ctThietBi.getLoaiThietBi().getTenLoai(), count);
                count = 1;
            }
        }
        return map;
    }
    
    @Override
    public Map<String, Map<String, Integer>> getDsTBChoPhepMuonCuaTatCaPhong() {
        Map<String, Map<String, Integer>> dsTBChoPhepMuonCuaTatCaPhong = new HashMap<>();
        for (Phong phong : this.getPhong()) {
            dsTBChoPhepMuonCuaTatCaPhong.put(phong.getMaPhong(), this.dsTBChoPhepMuon(phong.getMaPhong()));
        }
        return dsTBChoPhepMuonCuaTatCaPhong;
    }
    
    @Override
    public Phong addRoom(Phong phong) {
        return phongRepository.save(phong);
    }
    
    @Override
    public void callSpBaoTriPhong(String maPhong) {
        phongRepository.callSpBaoTriPhong(maPhong);
    }
    
    @Override
    public void callSpHoanThanhBaoTriPhong(String maPhong) {
        phongRepository.callSpHoanThanhBaoTriPhong(maPhong);
    }
    
    @Override
    public Integer deletePhong(String maPhong) {
        // Find all devices related to the room
        List<ThietBi> thietBis = thietBiService.findByMaPhong(maPhong);

        // Set maPhong to null for all related devices
        for (ThietBi thietBi : thietBis) {
            thietBi.setPhong(null);
            thietBiService.update(thietBi);
        }

        // Delete the room
        return phongRepository.deleteByMaPhong(maPhong);
    }
    
    @Override
    public boolean isRoomInUse(String maPhong) {
        // Tìm phòng bằng mã phòng
        Phong phong = phongRepository.findByMaPhong(maPhong);
        System.out.println("Phòng" + phong);
        // Kiểm tra xem phòng có tồn tại và có thiết bị đang được mượn hay không
        return phong != null && phong.getDstb().stream().anyMatch(thietBi -> thietBi.getTinhTrangTB().getMaTinhTrang() == 2);
    }
}
