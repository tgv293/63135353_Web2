//package thiGK.ntu63135353.giapvantai_QLSinhVien.services;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import thiGK.ntu63135353.giapvantai_QLSinhVien.models.SinhVien;
//
//@Service
//public class SinhVienServiceImpl implements SinhVienService {
//
//    @Override
//    public List<SinhVien> searchStudents(String keyword) {
//        // Thực hiện tìm kiếm sinh viên trong danh sách dựa trên từ khoá
//        List<SinhVien> dsSV = // Lấy danh sách sinh viên từ cơ sở dữ liệu hoặc nguồn dữ liệu khác
//        List<SinhVien> result = new ArrayList<>();
//        for (SinhVien sv : dsSV) {
//            if (sv.getMaSoSV().contains(keyword) || sv.getHoVaTen().contains(keyword)) {
//                result.add(sv);
//            }
//        }
//        return result;
//    }
//}
