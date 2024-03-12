package vn.gvt.DisplayListObjectPagination.services;

import org.springframework.stereotype.Service;

import vn.gvt.DisplayListObjectPagination.models.SinhVien;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class SinhVienServiceImpl implements SinhvienService {

	static List<SinhVien> dsSinhVien = new ArrayList<>();
	static {
		dsSinhVien.add(new SinhVien("0001", "Nguyễn Thị An"));
		dsSinhVien.add(new SinhVien("0002", "Lê Văn Bình"));
		dsSinhVien.add(new SinhVien("0003", "Trần Thị Cẩm"));
		dsSinhVien.add(new SinhVien("0004", "Đỗ Văn Dương"));
		dsSinhVien.add(new SinhVien("0005", "Phạm Thị Mai"));
		dsSinhVien.add(new SinhVien("0006", "Nguyễn Văn Nam"));
		dsSinhVien.add(new SinhVien("0007", "Lê Thị Oanh"));
		dsSinhVien.add(new SinhVien("0008", "Nguyễn Văn Phúc"));
		dsSinhVien.add(new SinhVien("0009", "Trần Thị Quỳnh"));
		dsSinhVien.add(new SinhVien("0010", "Vũ Văn Sơn"));
		dsSinhVien.add(new SinhVien("0011", "Lê Thị Thanh"));
		dsSinhVien.add(new SinhVien("0012", "Phạm Văn Tùng"));
	}

	// ----------hết phần hard-code dữ liệu ---------------------
	@Override
	public Page<SinhVien> findPaginated(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<SinhVien> list;

		if (dsSinhVien.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, dsSinhVien.size());
			list = dsSinhVien.subList(startItem, toIndex);
		}
		return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), dsSinhVien.size());
	}

}
