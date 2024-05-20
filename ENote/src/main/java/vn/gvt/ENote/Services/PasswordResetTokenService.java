package vn.gvt.ENote.Services;

import vn.gvt.ENote.Models.MailStructure;
import vn.gvt.ENote.Models.PasswordResetToken;
import vn.gvt.ENote.Models.User;

public interface PasswordResetTokenService {
    PasswordResetToken createPasswordResetToken(String email);
    PasswordResetToken findByToken(String token);
    void sendMail(String email, MailStructure mailStructure);
    User getUserByPasswordResetToken(String token);
}