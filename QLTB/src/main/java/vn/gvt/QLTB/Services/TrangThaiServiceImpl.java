package vn.gvt.QLTB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.gvt.QLTB.Models.TrangThai;
import vn.gvt.QLTB.Repositories.TrangThaiRepository;

@Service
public class TrangThaiServiceImpl implements TrangThaiService {

	private final TrangThaiRepository trangThaiRepository;

    @Autowired
    public TrangThaiServiceImpl(TrangThaiRepository trangThaiRepository) {
        this.trangThaiRepository = trangThaiRepository;
    }

    public TrangThai findByMaTinhTrang(Integer maTinhTrang) {
        return trangThaiRepository.findByMaTinhTrang(maTinhTrang);
    }
	
}
