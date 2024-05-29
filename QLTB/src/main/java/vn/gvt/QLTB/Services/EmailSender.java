package vn.gvt.QLTB.Services;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;



@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true, StandardCharsets.UTF_8.name());
        helper.setFrom("tai.gv.63cntt@ntu.edu.vn");
        helper.setTo(to);
        helper.setReplyTo("tai.gv.63cntt@ntu.edu.vn");
        helper.setSubject(subject);
        helper.setText(text, true);
        mailSender.send(mail);
    }
}