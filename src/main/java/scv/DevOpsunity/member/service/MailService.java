package scv.DevOpsunity.member.service;

import java.util.Map;

public interface MailService {
    Map<String, Object> send(String email, String title, String body);

    public boolean verifyCode(String email, String code) throws Exception;
}