package vn.gvt.QLTB.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import vn.gvt.QLTB.Models.CTPhieuMuon;
import vn.gvt.QLTB.Models.NguoiMuon;
import vn.gvt.QLTB.Models.PhieuMuon;
import vn.gvt.QLTB.Repositories.PhieuMuonRepository;

@Service
public class EmailService {
    @Autowired
    private PhieuMuonRepository phieuMuonRepository;

    @Autowired
    private EmailSender emailSender;

    @Scheduled(cron = "0 31 11 * * *") // Tự động gửi mail lúc 11h10
    public void sendOverdueEmails() throws MessagingException {
        List<PhieuMuon> allPhieuMuon = phieuMuonRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
        
        for (PhieuMuon phieuMuon : allPhieuMuon) {
            if (phieuMuon.getHanTra().isBefore(now) && phieuMuon.getThoiDiemTra() == null) {
                String ctPhieuMuonContent = "";
                int i = 0;
                for (CTPhieuMuon ctpm : phieuMuon.getListCTPhieuMuon()) {
                    if (ctpm.getTrangThai().getMaTinhTrang() == 4) {
                        ctPhieuMuonContent += "<p>" + ++i + ": " + ctpm.getThietBi().getLoaiThietBi().getTenLoai() + " - "
                                + ctpm.getPhieuMuon().getMaPhong().getMaPhong() + "</p>";
                    }
                }
                if (!ctPhieuMuonContent.isEmpty()) {
                	List<CTPhieuMuon> listCTPhieuMuon = new ArrayList<>(phieuMuon.getListCTPhieuMuon());
                	NguoiMuon nguoiMuon = listCTPhieuMuon.get(0).getNguoiMuon();
                    String email = nguoiMuon.getEmail();
                    String subject = "[ PCSVC! PHIẾU MƯỢN QUÁ HẠN! ]";
                    String message = "Cảnh báo!! Quá Hạn Trả</p></td></tr><tr style=\"border-bottom:solid 1px #e5e5e5\"><td height=\"15\" style=\"line-height:15px\" colspan=\"3\">&nbsp;</td></tr></tbody></table></td><td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td></tr><tr><td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td><td><table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\"><tbody><tr><td height=\"28\" style=\"line-height:28px\">&nbsp;</td></tr><tr><td><span style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;font-size:16px;line-height:21px;font-weight:bold;color:#141823\">"
                            + "Xin chào! " + nguoiMuon.getTenNguoiMuon() + ","
                            + "</span></td></tr><tr><td height=\"28\" style=\"line-height:28px\">&nbsp;</td></tr><tr><td><span style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;font-size:16px;line-height:21px;color:#141823\">"
                            + "Các thiết bị bạn mượn tại phòng Cơ Sở Vật Chất đã quá hạn trả! Vui lòng hoàn trả lại phòng cơ sở vật chất!</span></td></tr><tr><td height=\"28\" style=\"line-height:28px\">&nbsp;</td></tr><tr><td style=\"text-align: center;\"><span style=\"font-size:16px;font-weight:bold;color:#702fba\">"
                            + "Phiếu Mượn " + phieuMuon.getMaPhieuMuon()
                            + "</span></td></tr><tr><td height=\"28\" style=\"line-height:28px\">&nbsp;</td></tr><tr><td></td></tr><tr style=\"border-top:solid 1px #e5e5e5\"><td height=\"0\" style=\"line-height:0px\">&nbsp;</td></tr><tr><td></td></tr> <tr><td><table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"border-collapse:collapse;min-width:420px\"><tbody><tr><td valign=\"top\" style=\"padding:10px;font-size:0px\"><img src=\"https://c2nhien-nt.khanhhoa.edu.vn/upload/56243/20190513/Logo_Nguyen_Hien67.jpg\" width=\"16px\" height=\"16px\" style=\"border:0\"></td><td width=\"100%\" valign=\"top\" style=\"padding:10px\"><span style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;font-size:16px;line-height:21px;color:#141823\">"
                            + " Phòng: " + phieuMuon.getMaPhong().getMaPhong() + " &nbsp; Lớp: "
                            + phieuMuon.getLop().getFullName()
                            + "</span></td></tr></tbody></table></td></tr> <tr><td></td></tr><tr style=\"border-top:solid 1px #e5e5e5;\"><td height=\"0\" style=\"line-height:0px\">&nbsp;</td></tr><tr><td><table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"border-collapse:collapse;min-width:320px\"><tbody><tr><td valign=\"top\" style=\"padding:10px;font-size:0px\"><img src=\"https://c2nhien-nt.khanhhoa.edu.vn/upload/56243/20190513/Logo_Nguyen_Hien67.jpg\" width=\"16px\" height=\"16px\" style=\"border:0\"></td><td width=\"100%\" valign=\"top\" style=\"padding:10px\"><span style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;font-size:16px;line-height:21px;color:#141823\">"
                            + "Thiết Bị Quá Hạn Trả:" + ctPhieuMuonContent
                            + "</span></td></tr></tbody></table></td></tr><tr style=\"border-top:solid 1px #e5e5e5\"><td height=\"0\" style=\"line-height:0px\">&nbsp;</td></tr></tbody></table></td><td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td></tr></tbody></table></td></tr><tr><td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td></tr></tbody></table><table style=\"max-width:100%;padding:16px 8px\"><tbody><tr style=\"border-top:solid 1px #e5e5e5\"><td height=\"19\" style=\"line-height:19px\">&nbsp;</td></tr></tbody></table></div>";

                    emailSender.sendEmail(email, subject, message);
                }
            }
        }
    }
    
}