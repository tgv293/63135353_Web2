
package thiGK.ntu63135353.giapvantai_QLSinhVien.services;

import org.springframework.stereotype.Service;

import thiGK.ntu63135353.giapvantai_QLSinhVien.models.SinhVien;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public interface SinhvienService {
	public Page<SinhVien> findPaginated(Pageable pageable);

}
