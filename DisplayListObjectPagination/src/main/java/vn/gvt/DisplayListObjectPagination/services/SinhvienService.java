package vn.gvt.DisplayListObjectPagination.services;

import org.springframework.stereotype.Service;

import vn.gvt.DisplayListObjectPagination.models.SinhVien;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public interface SinhvienService {
	public Page<SinhVien> findPaginated(Pageable pageable);

}
