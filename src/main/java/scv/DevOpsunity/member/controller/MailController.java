package scv.DevOpsunity.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import scv.DevOpsunity.member.service.MailService;

import java.util.Map;
import java.util.Random;

@Controller
public class MailController {
    @Autowired
    private MailService mailService;

    @GetMapping("/mail/send.do")
    public String showSend(){
        return "mail/send";
    }

    @PostMapping("/mail/doSend.do")
    @ResponseBody
    public String doSend(String email, String title, String body){
        Map<String, Object> sendRs = mailService.send(email, title, body);
        return (String) sendRs.get("msg");
    }

    @PostMapping("/mail/verifyEmail.do")
    @ResponseBody
    public ResponseEntity<String> verifyEmail(@RequestParam String email) {
        try {
            Map<String, Object> sendRs = mailService.send(email, "이메일 인증", "아래의 인증 코드를 입력해주세요:");
            if ((boolean) sendRs.get("success")) {
                return ResponseEntity.ok("success");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((String) sendRs.get("msg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    private String generateVerificationCode() {
        // 간단한 6자리 인증 코드 생성
        return String.format("%06d", new Random().nextInt(999999));
    }

    @PostMapping("/mail/verifyCode.do")
    @ResponseBody
    public ResponseEntity<String> verifyCode(@RequestParam String email, @RequestParam String code) {
        try {
            // 여기에 실제 인증 코드 확인 로직을 구현해야 한다.
            // 예를 들어, 데이터베이스에서 해당 이메일의 인증 코드를 조회하고 비교하는 등의 작업을 수행한다.
            boolean isValid = mailService.verifyCode(email, code);

            if (isValid) {
                return ResponseEntity.ok("success");
            } else {
                return ResponseEntity.ok("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

}