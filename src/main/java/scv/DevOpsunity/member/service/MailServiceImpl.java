package scv.DevOpsunity.member.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender sender;

    // 인증 코드와 만료 시간을 함께 저장
    private Map<String, VerificationCode> verificationCodes = new ConcurrentHashMap<>();

    @Override
    public Map<String, Object> send(String email, String title, String body) {
        Map<String, Object> result = new HashMap<>();
        try {
            String verificationCode = generateVerificationCode();
            body += "\n인증 코드: " + verificationCode;

            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("edward3784@gmail.com", "DevOpsUnity");
            helper.setTo(email);
            helper.setSubject(title);
            helper.setText(body, true);
            sender.send(message);

            // 인증 코드 저장 (5분 후 만료)
            verificationCodes.put(email, new VerificationCode(verificationCode, System.currentTimeMillis() + 300000));

            result.put("success", true);
            result.put("msg", "메일이 성공적으로 발송되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", "메일 발송에 실패했습니다: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean verifyCode(String email, String code) {
        VerificationCode storedCode = verificationCodes.get(email);
        if (storedCode != null && storedCode.isValid() && storedCode.getCode().equals(code)) {
            verificationCodes.remove(email);  // 사용된 코드 제거
            return true;
        }
        return false;
    }

    private String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    private static class VerificationCode {
        private final String code;
        private final long expirationTime;

        VerificationCode(String code, long expirationTime) {
            this.code = code;
            this.expirationTime = expirationTime;
        }

        String getCode() {
            return code;
        }

        boolean isValid() {
            return System.currentTimeMillis() < expirationTime;
        }
    }
}