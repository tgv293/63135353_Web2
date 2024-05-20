package vn.gvt.ENote.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.gvt.ENote.Models.PasswordResetToken;
import vn.gvt.ENote.Models.User;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser(User user);
    PasswordResetToken findByUserAndIsActive(User user, boolean active);
}