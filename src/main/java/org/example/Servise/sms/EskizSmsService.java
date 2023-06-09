package org.example.Servise.sms;

import java.net.URI;
import java.net.http.HttpRequest;

public class EskizSmsService implements SmsService {
    public void send(String phone, String code) {
        // aaaaaaaaasd.sadasdaaaaaaaaaaaadasd.asdasasddddddddddd
        String json = new StringBuilder()
                .append("{")
                .append("\"phone\": \"" + phone + "\",")
                .append("\"code\":\"" + code + "\"")
                .append("}").toString();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("https://api.sms.uz"))
                .header("Content-Type", "application/json")
                .header("Token", "aaaaaaaaasd.sadasdaaaaaaaaaaaadasd.asdasasddddddddddd")
                .build();
    }

    public void validate(String phone, String code) {
        //
    }
}
