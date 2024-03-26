package thiGK.ntu63135353.giapvantai_QLSinhVien.services;

import java.util.List;

import thiGK.ntu63135353.giapvantai_QLSinhVien.models.SinhVien;

public interface SinhVienService {
    List<SinhVien> searchStudents(String keyword);
}
