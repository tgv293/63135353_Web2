package vn.gvt.QLTB.Services;

import java.io.IOException;

public interface RecaptchaVerification {
    boolean verify(String gRecaptchaResponse) throws IOException;
}